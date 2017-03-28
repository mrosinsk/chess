package com.capgemini.chess.dataaccess.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChallengeDaoImplJpaTest {

	@Autowired
	private ChallengeDao challengeDao;
	
	@PersistenceContext
	protected EntityManager entityManager;

	// SerchChallenge_positive
	@Test
	public void testShouldReturnSerchChallenge() {
		// given
		Long idChallenge = new Long(3);
		// when
		ChallengeTO result = challengeDao.serchChallenge(idChallenge);
		// then
		assertNotNull(result);
		assertEquals("DECLINE", result.getChallengeStatus().toString());
	}

	// SerchChallenge_negative
	@Test
	public void testShouldReturnThrowsExceptionDuringSerchChallenge() {
		// given
		Long idChallenge = new Long(500L);
		// when
		try {
			ChallengeTO result = challengeDao.serchChallenge(idChallenge);
			// then
			assertNotNull(result);
		} catch (Exception e) {
			assertFalse(!e.getMessage().contains("No entity found for query"));
		}
	}

	// SerchSentChallengeForUser_positive
	@Test
	public void testShouldReturnSerchSentChallengeForUser() {
		// given
		UserTO userTO = new UserTO();
		PlayerTO playerTO = new PlayerTO();
		playerTO.setId(1L);
		userTO.setPlayer(playerTO);
		// when
		List<ChallengeTO> result = challengeDao.serchSentChallengeForUser(userTO);
		// then
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	// SerchSentChallengeForUser_negative
	@Test
	public void testShouldReturn0SerchSentChallengeForUser() {
		// given
		UserTO userTO = new UserTO();
		PlayerTO playerTO = new PlayerTO();
		playerTO.setId(100L);
		userTO.setPlayer(playerTO);
		// when
		List<ChallengeTO> resultList = challengeDao.serchSentChallengeForUser(userTO);
		// then
		assertNotNull(resultList);
		assertEquals(0, resultList.size());
	}

	// FindReceivedChallengesForPlayer_positive
	@Test
	public void testShouldReturnFindReceivedChallengesForPlayer() {
		// given
		Long idOpponent = new Long(5L);
		PlayerTO player = new PlayerTO();
		player.setId(idOpponent);
		// when
		List<ChallengeTO> resultList = challengeDao.findReceivedChallengesForPlayer(player);
		// then
		assertEquals(3, resultList.size());
	}

	// FindReceivedChallengesForPlayer_negative
	@Test
	public void testShouldReturn0FindReceivedChallengesForPlayer() {
		// given
		Long idOpponent = new Long(1L);
		PlayerTO player = new PlayerTO();
		player.setId(idOpponent);
		// when
		List<ChallengeTO> resultList = challengeDao.findReceivedChallengesForPlayer(player);
		// then
		assertEquals(0, resultList.size());
	}

	// UpdateChallenge_positive
	@Test
	//@Rollback(false)
	public void testShouldUpdateChallenge() {
		// given
		StatusChallenge challengeStatus = StatusChallenge.ACCEPT;
		ChallengeTO challengeTO = challengeDao.serchChallenge(1L);
		challengeTO.setChallengeStatus(challengeStatus);
		// when
		boolean result = challengeDao.updateChallenge(challengeTO);
//		entityManager.flush();
//		entityManager.clear();
		// then
		assertEquals(true, result);
	}
	
	// UpdateChallenge_negative
	@Test
	@Rollback(false)
	public void testShouldNotUpdateChallenge() {
		// given
		StatusChallenge challengeStatus = StatusChallenge.ACCEPT;
		ChallengeTO challengeTO = challengeDao.serchChallenge(6L);
		challengeTO.setChallengeStatus(challengeStatus);
		challengeTO.setId(10L);
		// when
		boolean result = challengeDao.updateChallenge(challengeTO);
		entityManager.flush();
		entityManager.clear();
		// then
		assertEquals(false, result);
	}

	// CountAllChallenges_positive
	@Test
	public void testShouldReturnCountAllChallenges(){
		//given
		Long countChallengesInDB = new Long(6L);
		//when
		Long countChallenges = challengeDao.countAllChallenges();
		//then
		assertNotNull(countChallenges);
		assertEquals(countChallengesInDB, countChallenges);
	}
}
