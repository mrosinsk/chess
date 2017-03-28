package com.capgemini.chess.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.exception.BusinessEexception;
import com.capgemini.chess.exception.ChallengeValidationException;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.FindChallengeService;
import com.capgemini.chess.service.PlayerService;
import com.capgemini.chess.service.mapper.ChallengeMapper;
import com.capgemini.chess.service.mapper.PlayerMapper;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private FindChallengeService findChallengeService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private ChallengeDao challengeDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	private ChallengeMapper challengeMapper;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<PlayerTO> createChallengeSugestions(UserTO user) throws PlayerValidationException {
		return findChallengeService.findChallengeSuggestions(user);
	}
	
	//PRZETESTOWANA w ChallengeDao --> przelotka
	@Override
	public List<ChallengeTO> getSentChallenges(UserTO user) {
		return challengeDao.serchSentChallengeForUser(user);
	}

	@Override
	public List<ChallengeTO> getReceivedChallenges(UserTO user) throws PlayerValidationException {
		return challengeDao.findReceivedChallengesForPlayer(playerService.getPlayerFromUser(user));
	}

	//PRZETESTOWANA w ChallengeService --> przelotka
	@Override
	public List<ChallengeTO> getAllChallenges(UserTO user) throws PlayerValidationException {
		return findChallengeService.findExistingChallengeForUser(user);
	}

	@Override
	public ChallengeTO sendChallenge(UserTO fromUser, String toUserNick) throws BusinessEexception {
		PlayerTO fromPlayer = playerService.getPlayerFromUser(fromUser);
		PlayerTO toPlayer = playerService.searchPlayer(toUserNick);
		if (fromPlayer.getId() == toPlayer.getId()) {
			throw new ChallengeValidationException("Can't send challenge to yourself!");
		}
		if (toPlayer.getLevel() <= (fromPlayer.getLevel() + 2) && toPlayer.getLevel() >= (fromPlayer.getLevel() - 2)) {
			ChallengeTO sendChallenge = saveChallenge(fromPlayer.getId(), toPlayer.getId(), StatusChallenge.ACCEPT);
			return sendChallenge;
		}
		throw new ChallengeValidationException("Can't send incorrect challenge bad level criterias!");
	}

	@Override
	public ChallengeTO makeDecision(ChallengeTO challenge, StatusChallenge status) throws ChallengeValidationException {
		try {
			challengeDao.serchChallenge(challenge.getId());
		} catch (Exception e) {
			throw new ChallengeValidationException("Challenge doesn't exist!");
		}
		ChallengeTO challengeActual = challengeDao.serchChallenge(challenge.getId());
		challengeActual.setChallengeStatus(status);
		challengeDao.updateChallenge(challengeActual);
		return challengeActual;
	}

	@Override
	public ChallengeTO saveChallenge(Long idPlayer, Long idOpponent, StatusChallenge challengeStatus) {
		ChallengeEntity newChallenge = new ChallengeEntity();
		PlayerTO player = playerDao.findById(idPlayer);
		PlayerTO opponent = playerDao.findById(idOpponent);

		newChallenge.setChallengeStatus(challengeStatus);
		newChallenge.setIdPlayer(playerMapper.map(player));
		newChallenge.setIdOpponent(playerMapper.map(opponent));

		entityManager.persist(newChallenge);
		return challengeMapper.map(newChallenge);
	}
	


}
