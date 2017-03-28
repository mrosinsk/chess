package com.capgemini.chess.service.to;

import javax.persistence.Column;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTO extends AbstractTO{

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@OneToOne
	private ProfileTO profile;
	
	@OneToOne
	private PlayerTO player;

	
	
}
