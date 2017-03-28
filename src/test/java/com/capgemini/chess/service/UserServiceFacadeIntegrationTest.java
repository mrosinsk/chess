package com.capgemini.chess.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.service.to.RegistrationTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ChessApplication.class })
public class UserServiceFacadeIntegrationTest {

	@Autowired
	private UserServiceFacade service;

	@Autowired
	private UserDao dao;

	@Test
	public void shouldRegisterNewUser() throws Exception {
		// given
		RegistrationTO registration = giveRegistrationTO();

		// when
		UserTO user = service.register(registration);

		// then
		UserTO actual = dao.findById(user.getId());
		Assert.assertEquals(registration.getEmail(), actual.getEmail());
		Assert.assertEquals(registration.getPassword(), actual.getPassword());
		Assert.assertEquals(registration.getName(), actual.getProfile().getName());
		Assert.assertEquals(registration.getSurname(), actual.getProfile().getSurname());
	}

	private RegistrationTO giveRegistrationTO() {
		RegistrationTO to = new RegistrationTO();
		to.setEmail("a@b.pl");
		to.setPassword("12345678");
		to.setName("Name");
		to.setSurname("Surname");
		return to;
	}

}
