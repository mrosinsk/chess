package com.capgemini.chess.service.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationTO extends AbstractTO{
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String surname;


}
