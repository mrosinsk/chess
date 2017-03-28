package com.capgemini.chess.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.chess.service.UserCreationService;
import com.capgemini.chess.service.impl.UserCreationServiceImpl;
import com.capgemini.chess.service.to.RegistrationTO;
import com.capgemini.chess.service.to.UserTO;

public class UserCreationServiceImplTest {

	private UserCreationService service = new UserCreationServiceImpl();

	@Test
	public void shouldCreateUserWithProfile() {
		// given
		RegistrationTO registration = giveRegistration();

		// when
		UserTO user = service.create(registration);

		// then
		Assert.assertEquals(registration.getEmail(), user.getEmail());
		Assert.assertEquals(registration.getPassword(), user.getPassword());
		Assert.assertEquals(registration.getName(), user.getProfile().getName());
		Assert.assertEquals(registration.getSurname(), user.getProfile().getSurname());
	}

	private RegistrationTO giveRegistration() {
		RegistrationTO to = new RegistrationTO();
		to.setEmail("a@b.pl");
		to.setPassword("12345678");
		to.setName("Name");
		to.setSurname("Surname");
		return to;
	}
}
