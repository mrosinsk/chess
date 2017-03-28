package com.capgemini.chess.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.exception.BusinessEexception;
import com.capgemini.chess.exception.ChallengeValidationException;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChallengeServiceImplJpaTest {

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private ChallengeDao challengeDao;

	@PersistenceContext
	protected EntityManager entityManager;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	// SaveChallenge_positive
	@Test
	// @Rollback(false)
	public void testShouldSaveChallenge() {
		// given
		Long idPlayer = new Long(4L);
		Long idOpponent = new Long(2L);
		StatusChallenge challengeStatus = StatusChallenge.RECEIVED;
		// when
		ChallengeTO result = challengeService.saveChallenge(idPlayer, idOpponent, challengeStatus);
		entityManager.flush();
		entityManager.clear();
		// then
		assertNotNull(result);
		assertEquals("RECEIVED", result.getChallengeStatus().toString());
		assertNotNull(result.getId());
	}

	// SaveChallenge_negative
	@Test
	public void testShouldNotSaveChallenge() throws Exception {
		// given
		Long idPlayer = new Long(4L);
		Long idOpponent = new Long(200L);
		StatusChallenge challengeStatus = StatusChallenge.RECEIVED;
		// except
		exception.expect(Exception.class);
		exception.expectMessage("No entity found for query");
		// when
		challengeService.saveChallenge(idPlayer, idOpponent, challengeStatus);
		entityManager.flush();
		entityManager.clear();
	}

	// MakeDecision_positive
	@Test
	// @Rollback(false)
	public void testShouldMakeDecision() throws ChallengeValidationException {
		// given
		StatusChallenge challengeStatus = StatusChallenge.RECEIVED;
		ChallengeTO challengeExist = challengeDao.serchChallenge(3L);
		// when
		ChallengeTO result = challengeService.makeDecision(challengeExist, challengeStatus);
		// entityManager.flush();
		// entityManager.clear();
		// then
		assertNotNull(result);
		assertEquals("RECEIVED", result.getChallengeStatus().toString());
	}

	// MakeDecision_negative
	@Test
	public void testShouldReturnChallengeValidationExceptionDuringMakeDecision() throws Exception {
		// given
		StatusChallenge challengeStatus = StatusChallenge.RECEIVED;
		ChallengeTO challengeExist = challengeDao.serchChallenge(3L);
		challengeExist.setId(30L);
		// except
		exception.expect(ChallengeValidationException.class);
		exception.expectMessage("Challenge doesn't exist!");
		// when
		challengeService.makeDecision(challengeExist, challengeStatus);

	}

	// SendChallenge_positive
	@Test
	// @Rollback(false)
	public void testShouldSendChallenge() throws BusinessEexception {
		// given
		UserTO fromUser = new UserTO();
		fromUser.setId(5L);
		String toUserNick = "zzenek";
		Long countChallengesBefore = challengeDao.countAllChallenges();
		// when
		ChallengeTO result = challengeService.sendChallenge(fromUser, toUserNick);
		Long countChallengesLeater = challengeDao.countAllChallenges();
		// then
		assertEquals((countChallengesBefore.longValue() + 1L), countChallengesLeater.longValue());
	}

	// SendChallenge_negative
	@Test
	// @Rollback(false)
	public void testShouldReturnChallengeValidationExceptionDuringSendChallenge() throws Exception {
		// given
		UserTO fromUser = new UserTO();
		fromUser.setId(1L);
		String toUserNick = "zzenek";
		// expect
		exception.expect(BusinessEexception.class);
		exception.expectMessage("Can't send challenge to yourself!");
		// when
		challengeService.sendChallenge(fromUser, toUserNick);
	}

	// SendChallenge_negative
	@Test
	public void testShouldReturnChallengeValidationExceptionDuringSendChallenge2() throws Exception {
		// given
		UserTO fromUser = new UserTO();
		fromUser.setId(4L);
		String toUserNick = "zzenek";
		// expect
		exception.expect(BusinessEexception.class);
		exception.expectMessage("Can't send incorrect challenge bad level criterias!");
		// when
		challengeService.sendChallenge(fromUser, toUserNick);

	}

	// CreateChallengeSuggestions - positive
	@Test
	public void testShouldCreateChallengeSuggestions() throws PlayerValidationException {
		// given
		UserTO user = new UserTO();
		user.setId(2L);
		// when
		List<PlayerTO> resultList = challengeService.createChallengeSugestions(user);
		// then
		assertNotNull(resultList);
		assertEquals(4, resultList.size());
		assertTrue(resultList.get(0).getLevel() >= resultList.get(resultList.size() - 1).getLevel());

	}

	// CreateChallengeSuggestions - negative
	@Test
	public void testShouldThrowsPlayerValidationExceptionDuringCreateChallengeSuggestions() throws Exception {
		// given
		UserTO user = new UserTO();
		user.setId(20L);
		// expect
		exception.expect(PlayerValidationException.class);
		exception.expectMessage("Can't find player!");
		// when
		challengeService.createChallengeSugestions(user);

	}

	// getReceivedChallenges - positive
	@Test
	public void testShouldGetReceivedChallenges() throws PlayerValidationException {
		// given
		UserTO user = new UserTO();
		user.setId(2L);
		// when
		List<ChallengeTO> resultList = challengeService.getReceivedChallenges(user);
		// then
		assertEquals(1, resultList.size());
	}

	// getReceivedChallenges - negative
	@Test
	public void testShouldThrowsPlayerValidationExceptionDuringGetReceivedChallenges() throws Exception {
		// given
		UserTO user = new UserTO();
		user.setId(20L);
		// expect
		exception.expect(PlayerValidationException.class);
		exception.expectMessage("Player doesn't exist!");
		// when
		challengeService.getReceivedChallenges(user);
	}

}
