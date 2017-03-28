package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.RegistrationTO;

public interface UserValidationService {

	void validate(RegistrationTO to) throws UserValidationException;

}
