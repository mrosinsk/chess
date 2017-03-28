package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.RegistrationTO;
import com.capgemini.chess.service.to.UserTO;

public interface UserServiceFacade {
	
	UserTO register(RegistrationTO to) throws UserValidationException;
}
