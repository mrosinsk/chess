package com.capgemini.chess.service.impl;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.PlayerService;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlayerServiceImplJpaTest {

	@Autowired
	private PlayerService playerService;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	// SearchPlayer - positive
	@Test
	public void testShouldSearchPlayer() throws PlayerValidationException {
		// given
		String playerNick = "mmaja";
		Long playerId = new Long(5L);
		// when
		PlayerTO result = playerService.searchPlayer(playerNick);
		// then
		assertNotNull(result);
		assertEquals(playerId, result.getId());
	}

	// SearchPlayer - negative
	@Test
	public void testShouldReturnPlayerValidationExceptionDuringSearchPlayer() throws Exception {
		// given
		String playerNick = "zzoja";
		// except
		exception.expect(PlayerValidationException.class);
		exception.expectMessage("Player doesn't exist!");
		// when
		playerService.searchPlayer(playerNick);
	}

	// GetPlayerFromUser - positive
	@Test
	public void testShouldGetPlayerFromUser() throws PlayerValidationException {
		// given
		UserTO user = new UserTO();
		user.setId(3L);
		// when
		PlayerTO result = playerService.getPlayerFromUser(user);
		// then
		assertNotNull(result);
		assertEquals("ggienek", result.getNick().toString());
	}

	// GetPlayerFromUser - negative
	@Test
	public void testShouldReturnPlayerValidationExceptionDuringNotGetPlayerFromUser() throws Exception {
		// given
		UserTO user = new UserTO();
		user.setId(30L);
		// except
		exception.expect(PlayerValidationException.class);
		exception.expectMessage("Player doesn't exist!");
		// when
		playerService.getPlayerFromUser(user);

	}

}
