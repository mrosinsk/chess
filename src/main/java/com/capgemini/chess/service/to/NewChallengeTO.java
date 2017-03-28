package com.capgemini.chess.service.to;

import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewChallengeTO extends AbstractTO{
	
	@OneToOne
	private UserTO fromUserId;
	
	@OneToOne
	private UserTO toUserNick;
	
	
}
