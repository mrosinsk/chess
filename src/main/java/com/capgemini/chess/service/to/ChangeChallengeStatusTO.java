package com.capgemini.chess.service.to;

import javax.persistence.OneToOne;

import com.capgemini.chess.enums.StatusChallenge;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeChallengeStatusTO extends AbstractTO{

	
	private StatusChallenge status;
	
	@OneToOne
	private ChallengeTO challengeId;
	

	
	
}
