package com.capgemini.chess.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.FindChallengeService;
import com.capgemini.chess.service.PlayerService;
import com.capgemini.chess.service.mapper.PlayerMapper;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class FindChallengeServiceImpl implements FindChallengeService {

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private ChallengeDao challengeDao;

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerMapper playerMapper;

	@Override
	public List<PlayerTO> findChallengeSuggestions(UserTO user) throws PlayerValidationException {
		PlayerTO player;
		try {
			player = playerService.getPlayerFromUser(user);
		} catch (Exception e) {
			throw new PlayerValidationException("Can't find player!");
		}
		List<PlayerTO> oponentsList = playerDao.getOponents((player.getLevel() - 2), player.getLevel() + 2);
		PlayerTO removePlayer = null;
		for (PlayerTO playerTO : oponentsList) {
			if (playerTO.getId().equals(player.getId())) {
				removePlayer = playerTO;
				break;
			}
		}
		oponentsList.remove(removePlayer);
		List<PlayerTO> oponentListChoosenRandom = new ArrayList<PlayerTO>();
		int numberRandom;
		Random generator = new Random();
		for (int i = 1; i <= 5 && !(oponentsList.isEmpty()); i++) {
			numberRandom = generator.nextInt(oponentsList.size());
			oponentListChoosenRandom.add(oponentsList.get(numberRandom));
			oponentsList.remove(numberRandom);
		}
		Collections.sort(oponentListChoosenRandom);
		return oponentListChoosenRandom;
	}

	@Override
	public List<ChallengeTO> findExistingChallengeForUser(UserTO user) throws PlayerValidationException {
		List<ChallengeTO> challengesSentList = challengeDao.serchSentChallengeForUser(user);
		List<ChallengeTO> challengesReceived = challengeDao
				.findReceivedChallengesForPlayer(playerService.getPlayerFromUser(user));
		List<ChallengeTO> allExistingChallenges = new ArrayList<ChallengeTO>();
		allExistingChallenges.addAll(challengesSentList);
		allExistingChallenges.addAll(challengesReceived);
		return allExistingChallenges;
	}

	@Override
	public PlayerTO showInformationAboutPlayer(ChallengeTO challenge) {
		PlayerTO opponent = playerMapper.map(challenge.getIdOpponent());
		PlayerTO selectedOpponent = playerDao.findById(opponent.getId());
		return selectedOpponent;
	}
}
