package com.capgemini.chess.dataaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "player")
@Getter
@Setter
public class PlayerEntity extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "level", nullable = false)
	private int level;
	
	@Column(name = "points", nullable = false)
	private int points;
	
	@Column(name = "nick", nullable = false)
	private String nick;
	
	@Column(name = "benefit", nullable = false)
	private int benefit;
	
	@Column(name = "loss", nullable = false)
	private int loss;

	
}
