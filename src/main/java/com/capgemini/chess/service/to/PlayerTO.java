package com.capgemini.chess.service.to;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerTO extends AbstractTO implements Comparable<PlayerTO> {

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

	//metoda wykorzystywana do sortowania malejacego listy automatycznych challenges
	@Override
	public int compareTo(PlayerTO o) {
		int compareLevel=((PlayerTO)o).getLevel();
		return compareLevel-this.level;
	}

}
