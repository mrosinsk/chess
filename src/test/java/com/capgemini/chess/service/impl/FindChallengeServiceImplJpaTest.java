package com.capgemini.chess.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.FindChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FindChallengeServiceImplJpaTest {

	@Autowired
	private FindChallengeService findChallengeService;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ChallengeDao challengeDao;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	// FindChallengeSuggestions - positive
	@Test
	public void testShouldFindChallengeSuggestions() throws PlayerValidationException {
		// given
		UserTO user = new UserTO();
		user.setId(2L);
		// when
		List<PlayerTO> resultList = findChallengeService.findChallengeSuggestions(user);
		// then
		assertNotNull(resultList);
		assertEquals(4, resultList.size());
		assertTrue(resultList.get(0).getLevel() >= resultList.get(resultList.size() - 1).getLevel());

	}

	// FindChallengeSuggestions - negative
	@Test
	public void testShouldThrowsPlayerValidationExceptionDuringFindChallengeSuggestions() throws Exception {
		// given
		UserTO user = new UserTO();
		user.setId(20L);
		// except
		exception.expect(PlayerValidationException.class);
		exception.expectMessage("Can't find player!");
		// when
		findChallengeService.findChallengeSuggestions(user);

	}

	// FindExistingChallengeForUser - positive
	@Test
	public void testShouldFindExistingChallengeForUser() throws PlayerValidationException {
		// given
		UserTO user = userDao.findById(2L);
		// when
		List<ChallengeTO> resultList = findChallengeService.findExistingChallengeForUser(user);
		// then
		assertFalse(resultList.isEmpty());
		assertEquals(2, resultList.size());
	}
	
	//showInformationAboutPlayer - positive
	@Test
	public void testShouldShowInformationAboutPlayer (){
		//given
		ChallengeTO challenge = challengeDao.serchChallenge(3L);
		//when
		PlayerTO result = findChallengeService.showInformationAboutPlayer(challenge);
		//then
		assertEquals("mmaja", result.getNick());
		assertEquals(10, result.getPoints());
	}

}
