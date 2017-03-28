package com.capgemini.chess.dataaccess.source.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.dataaccess.source.MapPlayerSource;

@Component
public class MapPlayerSourceImpl implements MapPlayerSource {

	private Map<Long, PlayerEntity> players = new HashMap<Long, PlayerEntity>();

	@Override
	public Map<Long, PlayerEntity> getPlayers() {
		return players;
	}
	
	//konstruktor tworzacy kilku playerow do testowania restow
	public MapPlayerSourceImpl(){
		PlayerEntity nowyPlayer1 = new PlayerEntity();
		nowyPlayer1.setId(Long.parseLong("1"));
		nowyPlayer1.setNick("Kaka");
		nowyPlayer1.setLevel(2);
		players.put(1L, nowyPlayer1);
		
		PlayerEntity nowyPlayer2 = new PlayerEntity();
		nowyPlayer2.setId(Long.parseLong("2"));
		nowyPlayer2.setNick("Koko");
		nowyPlayer2.setLevel(2);
		players.put(2L, nowyPlayer2);
		
		PlayerEntity nowyPlayer3 = new PlayerEntity();
		nowyPlayer3.setId(Long.parseLong("3"));
		nowyPlayer3.setNick("Zenek");
		nowyPlayer3.setLevel(4);
		players.put(3L, nowyPlayer3);
		
		PlayerEntity nowyPlayer4 = new PlayerEntity();
		nowyPlayer4.setId(Long.parseLong("4"));
		nowyPlayer4.setNick("Wojtek");
		nowyPlayer4.setLevel(4);
		players.put(4L, nowyPlayer4);
		
		PlayerEntity nowyPlayer5 = new PlayerEntity();
		nowyPlayer5.setId(Long.parseLong("5"));
		nowyPlayer5.setNick("Franek");
		nowyPlayer5.setLevel(4);
		players.put(5L, nowyPlayer5);
	}

}
