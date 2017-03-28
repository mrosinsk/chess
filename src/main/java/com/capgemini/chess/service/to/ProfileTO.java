package com.capgemini.chess.service.to;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileTO extends AbstractTO{

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "aboutMe", nullable = false)
	private String aboutMe;

	@Column(name = "lifeMotto", nullable = false)
	private String lifeMotto;
	

}
