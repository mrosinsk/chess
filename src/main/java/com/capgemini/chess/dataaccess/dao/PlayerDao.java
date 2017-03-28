package com.capgemini.chess.dataaccess.dao;

import java.util.List;

import com.capgemini.chess.service.to.PlayerTO;

public interface PlayerDao {

	/**
	 *wyswietla liste potencjalnych oponentow do challenges 
	 * @param levelFrom
	 * @param levelTo
	 * @return
	 */
	List<PlayerTO> getOponents(int levelFrom, int levelTo);

	/**
	 * znajduje gracza na podstawie jego nicku
	 * @param nick
	 * @return
	 */
	PlayerTO findByNick(String nick);

	/**
	 * znajduje gracza na podstawie jego id
	 * @param id
	 * @return
	 */
	PlayerTO findById(Long id);

	/**
	 * znajduje gracza na podstawie jego niku i poziomu
	 * @param nick
	 * @param level
	 * @return
	 */
	List<PlayerTO> findByNickAndLevel(String nick, int level);

}
