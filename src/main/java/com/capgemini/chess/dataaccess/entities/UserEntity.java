package com.capgemini.chess.dataaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@OneToOne
	private ProfileEntity profile;
	
	@OneToOne
	private PlayerEntity player;

}
