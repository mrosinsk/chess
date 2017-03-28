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
import com.capgemini.chess.exception.BusinessEexception;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.ChallengeFacade;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChallengeFacadeImplTest {

@Autowired
private ChallengeFacade challengeFacade;

@Autowired
private ChallengeDao challengeDao;

@Rule
public ExpectedException exception = ExpectedException.none();

@PersistenceContext
protected EntityManager entityManager;

	// CreateChallengeSuggestions - positive
	@Test
	public void testShouldCreateChallengeSuggestions() throws PlayerValidationException {
		// given
		UserTO user = new UserTO();
		user.setId(2L);
		// when
		List<PlayerTO> resultList = challengeFacade.createChallengeSugestions(user);
		// then
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
		challengeFacade.createChallengeSugestions(user);
	}
	
	// getReceivedChallenges - positive
	@Test
	public void testShouldGetReceivedChallenges() throws PlayerValidationException {
		// given
		UserTO user = new UserTO();
		user.setId(2L);
		// when
		List<ChallengeTO> resultList = challengeFacade.getReceivedChallenges(user);
		// then
		assertEquals(1, resultList.size());
	}

	// ReceivedChallenges - negative
	@Test
	public void testShouldThrowsPlayerValidationExceptionDuringGetReceivedChallenges() throws Exception {
		// given
		UserTO user = new UserTO();
		user.setId(20L);
		// expect
		exception.expect(PlayerValidationException.class);
		exception.expectMessage("Player doesn't exist!");
		// when
		challengeFacade.getReceivedChallenges(user);
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
		ChallengeTO result = challengeFacade.sendChallenge(fromUser, toUserNick);
		Long countChallengesLeater = challengeDao.countAllChallenges();
		// then
		assertNotNull(result);
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
			challengeFacade.sendChallenge(fromUser, toUserNick);
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
			challengeFacade.sendChallenge(fromUser, toUserNick);

		}
}
