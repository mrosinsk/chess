package com.capgemini.chess.dataaccess.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.service.to.PlayerTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlayerDaoImplJpaTest {

	@Autowired
	private PlayerDao playerDao;

	// FindById_positive
	@Test
	public void testShouldReturnFindById() {
		// given
		Long id = new Long(1L);
		// when
		PlayerTO result = playerDao.findById(id);
		// then
		assertNotNull(result);
		assertEquals("zzenek", result.getNick());
	}

	// FindById_negative
	@Test
	public void testShouldReturnThrowsExceptionDuringFindById() {
		// given
		Long id = new Long(100L);
		// when
		try {
			PlayerTO result = playerDao.findById(id);
			assertNotNull(result);
		} catch (Exception e) {
			// then
			assertFalse(!e.getMessage().contains("No entity found for query"));
		}

	}

	// FindByNickAndLevel_positive
	@Test
	public void testShouldReturnFindByNickAndLevel() {
		// given
		String nick = "bbenek";
		int level = 1;
		// when
		List<PlayerTO> resultList = playerDao.findByNickAndLevel(nick, level);
		// then
		assertNotNull(resultList);
		assertEquals(1, resultList.size());
		assertEquals("bbenek", resultList.get(0).getNick());
	}

	// FindByNickAndLevel_negative
	@Test
	public void testShouldReturn0FindByNickAndLevel() {
		// given
		String nick = "krasnoludek";
		int level = 19;
		// when
		List<PlayerTO> resultList = playerDao.findByNickAndLevel(nick, level);
		assertEquals(0, resultList.size());
	}

	// FindByNick_positive
	@Test
	public void testShouldReturnFindByNick() {
		// given
		String nick = "kkaja";
		Long idPlayerKkaja = new Long(4L);
		// when
		PlayerTO result = playerDao.findByNick(nick);
		// then
		assertNotNull(result);
		assertEquals(idPlayerKkaja, result.getId());
	}

	// FindByNick_negative
	@Test
	public void testShouldReturnThrowsExceptionDuringFindByNick() {
		// given
		String nick = "zosia";
		// when
		try {
			PlayerTO result = playerDao.findByNick(nick);
			assertNotNull(result);
		} catch (Exception e) {
			// then
			assertFalse(!e.getMessage().contains("No entity found for query"));
		}
	}

	// GetOponents_positive
	@Test
	public void testShouldReturnGetOponents() {
		// given
		int levelFrom = 2;
		int levelTo = 3;
		// when
		List<PlayerTO> resultList = playerDao.getOponents(levelFrom, levelTo);
		// then
		assertNotNull(resultList);
		assertEquals(2, resultList.size());
	}

	// GetOponents_negative
	@Test
	public void testShouldReturn0GetOponents() {
		// given
		int levelFrom = 200;
		int levelTo = 300;
		// when
		List<PlayerTO> resultList = playerDao.getOponents(levelFrom, levelTo);
		// then
		assertEquals(0, resultList.size());
	}

}
