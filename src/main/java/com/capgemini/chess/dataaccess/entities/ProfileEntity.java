package com.capgemini.chess.dataaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "aboutMe", nullable = false)
	private String aboutMe;

	@Column(name = "lifeMotto", nullable = false)
	private String lifeMotto;

}
