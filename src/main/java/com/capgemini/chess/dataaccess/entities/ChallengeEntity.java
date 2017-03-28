package com.capgemini.chess.dataaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.chess.enums.StatusChallenge;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "challenge")
@Getter
@Setter
public class ChallengeEntity extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private PlayerEntity idPlayer;
	
	@ManyToOne
	private PlayerEntity idOpponent;

	@Column(name = "challengeStatus", nullable = true)
	@Enumerated(EnumType.STRING)
	private StatusChallenge challengeStatus;

	
}
