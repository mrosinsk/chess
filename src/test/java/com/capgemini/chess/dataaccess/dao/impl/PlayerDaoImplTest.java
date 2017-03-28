package com.capgemini.chess.dataaccess.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.dataaccess.source.MapPlayerSource;
import com.capgemini.chess.service.to.PlayerTO;

@RunWith(MockitoJUnitRunner.class)
public class PlayerDaoImplTest {

	private static final String EXISTING_NICK = "franek";
	private static final Long EXISTING_ID = 1L;

	private static final String NOT_EXISTING_NICK = "bolek";
	private static final Long NOT_EXISTING_ID = 8L;

	@InjectMocks
	private PlayerDaoImpl dao;

	@Mock
	private MapPlayerSource playerSource;

	@Test
	public void findByIdSchouldReturnNotNullWhenIdExist() {
		// given
		Mockito.when(playerSource.getPlayers()).thenReturn(givePlayersMap());
		// when
		PlayerTO player = dao.findById(EXISTING_ID);
		// then
		Assert.assertNotNull(player);
	}

	@Test
	public void findByIdSchouldReturnNullWhenIdNotExist() {
		// given
		Mockito.when(playerSource.getPlayers()).thenReturn(givePlayersMap());
		// when
		PlayerTO player = dao.findById(NOT_EXISTING_ID);
		// then
		Assert.assertNull(player);
	}

	@Test
	public void findByNickSchouldReturnNotNullWhenNickExist() {
		// given
		Mockito.when(playerSource.getPlayers()).thenReturn(givePlayersMap());
		// when
		PlayerTO player = dao.findByNick(EXISTING_NICK);
		// then
		Assert.assertNotNull(player);
	}

	@Test
	public void findByNickSchouldReturnNullWhenNickNotExist() {
		// given
		Mockito.when(playerSource.getPlayers()).thenReturn(givePlayersMap());
		// when
		PlayerTO player = dao.findByNick(NOT_EXISTING_NICK);
		// then
		Assert.assertNull(player);
	}

	@Test
	public void getOponentsSchouldReturnNull() {
		// given
		Mockito.when(playerSource.getPlayers()).thenReturn(givePlayersMap());
		// when
		List<PlayerTO> opponentsList = dao.getOponents(6, 8);
		// then
		Assert.assertEquals(0, opponentsList.size());

	}

	@Test
	public void getOponentsSchouldReturnNotNull() {
		// given
		Mockito.when(playerSource.getPlayers()).thenReturn(givePlayersMap());
		// when
		List<PlayerTO> opponentsList = dao.getOponents(1, 4);
		// then
		Assert.assertNotNull(opponentsList);
	}

	@Test
	public void getOponentsSchouldReturn1() {
		// given
		Mockito.when(playerSource.getPlayers()).thenReturn(givePlayersMap());
		// when
		List<PlayerTO> opponentsList = dao.getOponents(2, 5);
		// then
		Assert.assertEquals(2, opponentsList.size());
	}

	private Map<Long, PlayerEntity> givePlayersMap() {
		HashMap<Long, PlayerEntity> players = new HashMap<Long, PlayerEntity>();
		PlayerEntity player = new PlayerEntity();
		PlayerEntity player1 = new PlayerEntity();
		PlayerEntity player2 = new PlayerEntity();

		player.setId(EXISTING_ID);
		player.setNick(EXISTING_NICK);
		player.setLevel(1);

		player1.setId(2L);
		player1.setNick("zenek");
		player1.setLevel(2);

		player2.setId(3L);
		player2.setNick("zosia");
		player2.setLevel(5);

		players.put(10L, player);
		players.put(20L, player1);
		players.put(30L, player2);
		return players;
	}

}
