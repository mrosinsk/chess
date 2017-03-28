package com.capgemini.chess.service.to;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.enums.StatusChallenge;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeTO extends AbstractTO{

	@ManyToOne
	private PlayerEntity idPlayer;
	
	@ManyToOne
	private PlayerEntity idOpponent;

	@Column(name = "challengeStatus", nullable = true)
	@Enumerated(EnumType.STRING)
	private StatusChallenge challengeStatus;


}
