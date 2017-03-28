package com.capgemini.chess.service;

import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

public interface PlayerService {

	/**
	 * szuka gracza o okreslonym nicku
	 * 
	 * @param playerNick
	 *            nik po ktorym medoda szuka gracza
	 * @return zwraca odnalezionego gracza
	 * @throws PlayerValidationException 
	 */
	PlayerTO searchPlayer(String playerNick) throws PlayerValidationException;

	/**
	 * pobranie playera z jego statystykami z usera
	 * @param user
	 * @return
	 * @throws PlayerValidationException
	 */
	PlayerTO getPlayerFromUser(UserTO user) throws PlayerValidationException;

}
