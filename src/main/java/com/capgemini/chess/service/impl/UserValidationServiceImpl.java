package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.UserValidationService;
import com.capgemini.chess.service.to.RegistrationTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class UserValidationServiceImpl implements UserValidationService {

	@Autowired
	private UserDao userDao;

	@Override
	public void validate(RegistrationTO to) throws UserValidationException {
		validateEmail(to);
		validatePassword(to);
	}

	private void validateEmail(RegistrationTO to) throws UserValidationException {
		UserTO foundByEmail = userDao.findByEmail(to.getEmail());
		if (foundByEmail != null) {
			throw new UserValidationException("User with given email already exists");
		}
	}

	private void validatePassword(RegistrationTO to) throws UserValidationException {
		if (to.getPassword() != null && to.getPassword().length() < 8) {
			throw new UserValidationException("Password should be at least 8 characters long");
		}
	}
}
