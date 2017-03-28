package com.capgemini.chess.dataaccess.dao.impl;

import javax.transaction.Transactional;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoImplJpaTest {

	@Autowired
	private UserDao userDao;

	// FindByEmail_positive
	@Test
	public void testShouldReturnFindByEmail() {
		// given
		String email = "gienek@ipsario.pl";
		// when
		UserTO result = userDao.findByEmail(email);
		// then
		assertNotNull(result);
		assertEquals("gienek@ipsario.pl", result.getEmail().toString());
	}

	// FindByEmail_negative
	@Test
	public void testShouldReturnThrowsExceptionDuringFindByEmail() {
		// given
		String email = "krasnoludek@ipsario.pl";
		// when
		try {
			UserTO result = userDao.findByEmail(email);
			// then
			assertNotNull(result);
			assertEquals("krasnoludek@ipsario.pl", result.getEmail().toString());
		} catch (Exception e) {
			assertFalse(!e.getMessage().contains("No entity found for query"));
		}
	}

	// FindById_positive
	@Test
	public void testShouldReturnFindById() {
		// given
		Long id = new Long(4L);
		// when
		UserTO result = userDao.findById(id);
		// then
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals("kaja@ipsario.pl", result.getEmail().toString());
	}

	// FindByEmail_negative
	@Test
	public void testShouldReturnThrowsExceptionDuringFindById() {
		// given
		Long id = new Long(400L);
		// when
		try {
			UserTO result = userDao.findById(id);
			// then
			assertNotNull(result);
			assertEquals(id, result.getId());
		} catch (Exception e) {
			assertFalse(!e.getMessage().contains("No entity found for query"));
		}
	}
	

	// SaveUser_positive
	@Test
	//@Rollback(false)
	public void testShouldReturnSaveUser() {
		//given
		String email = "ola@ipsario.pl";
		String password = "fajna_ola";
		//when
		UserTO newUser = userDao.saveUser(email, password);
		//then
		assertNotNull(newUser);
		assertEquals("fajna_ola", newUser.getPassword().toString());
	
	}
	

}
