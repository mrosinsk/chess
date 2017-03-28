package com.capgemini.chess.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.exception.BusinessEexception;
import com.capgemini.chess.exception.ChallengeValidationException;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.ChallengeFacade;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.FindChallengeService;
import com.capgemini.chess.service.PlayerService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class ChallengeFacadeImpl implements ChallengeFacade{

	@Autowired
	private ChallengeService challengeService;
	
	@Autowired
	private FindChallengeService findChallengeService;
	
	@PersistenceContext
	protected EntityManager entityManager;

	 @Override
	public List<PlayerTO> createChallengeSugestions(UserTO user) throws PlayerValidationException {
		return challengeService.createChallengeSugestions(user);
	}

	 @Override
	public List<ChallengeTO> getSentChallenges(UserTO user) {
		return challengeService.getSentChallenges(user);
	}

	 @Override
	public List<ChallengeTO> getReceivedChallenges(UserTO user) throws PlayerValidationException {
		return challengeService.getReceivedChallenges(user);
	}

	 @Override
	public List<ChallengeTO> getAllChallenges(UserTO user) throws PlayerValidationException {
		return challengeService.getAllChallenges(user);
	}

	 @Override
	public ChallengeTO sendChallenge(UserTO fromUser, String toUserNick) throws BusinessEexception {
		return challengeService.sendChallenge(fromUser, toUserNick);
	}

	 @Override
	public ChallengeTO makeDecision(ChallengeTO challenge, StatusChallenge status) throws ChallengeValidationException {
		return challengeService.makeDecision(challenge, status);
	}

	@Override
	public PlayerTO showInformationAboutPlayer(ChallengeTO challenge) {
		return findChallengeService.showInformationAboutPlayer(challenge);
	}
	 
	 

}
