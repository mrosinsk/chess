package com.capgemini.chess.service.impl;

import org.springframework.stereotype.Service;

import com.capgemini.chess.service.UserCreationService;
import com.capgemini.chess.service.to.ProfileTO;
import com.capgemini.chess.service.to.RegistrationTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class UserCreationServiceImpl implements UserCreationService {

	@Override
	public UserTO create(RegistrationTO to) {
		ProfileTO profile = new ProfileTO();
		profile.setName(to.getName());
		profile.setSurname(to.getSurname());
		UserTO user = new UserTO();
		user.setProfile(profile);
		user.setEmail(to.getEmail());
		user.setPassword(to.getPassword());
		return user;
	}

}
