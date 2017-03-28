package com.capgemini.chess.service.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.RegistrationTO;
import com.capgemini.chess.service.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class UserValidationServiceImplTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserValidationServiceImpl service;

	@Test
	public void shouldThrowExceptionWhenEmailAlreadyExists() throws Exception {
		// given
		RegistrationTO user = new RegistrationTO();
		Mockito.when(userDao.findByEmail(user.getEmail())).thenReturn(new UserTO());

		// expect
		exception.expect(UserValidationException.class);
		exception.expectMessage("User with given email already exists");

		// when
		service.validate(user);
	}

	@Test
	public void shouldValidateWhenEmailNotExists() throws Exception {
		// given
		RegistrationTO user = new RegistrationTO();
		Mockito.when(userDao.findByEmail(user.getEmail())).thenReturn(null);

		// when
		service.validate(user);
	}

	@Test
	public void shouldThrowExceptionWhenPasswordShorterThan8() throws Exception {
		// given
		RegistrationTO user = new RegistrationTO();
		user.setPassword("1234567");

		// expect
		exception.expect(UserValidationException.class);
		exception.expectMessage("Password should be at least 8 characters long");

		// when
		service.validate(user);
	}
}
