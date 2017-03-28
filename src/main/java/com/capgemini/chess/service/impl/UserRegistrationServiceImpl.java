package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.UserCreationService;
import com.capgemini.chess.service.UserRegistrationService;
import com.capgemini.chess.service.UserValidationService;
import com.capgemini.chess.service.to.RegistrationTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserValidationService validationService;

	@Autowired
	private UserCreationService creationService;

	@Override
	public UserTO register(RegistrationTO to) throws UserValidationException {
		validationService.validate(to);
		//UserTO user = creationService.create(to);
		//return userDao.save(user);
		return null;
	}

}
