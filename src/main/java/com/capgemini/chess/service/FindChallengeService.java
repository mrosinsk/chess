package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

public interface FindChallengeService {
	/**
	 * znajduje max 5 potencjalnych oponentow do challenge dla uzytkownika
	 * 
	 * @param user
	 *            uzytkownik dla ktorego metoda znajduje oponentow
	 * @return zwraca liste potencjalnych oponentow
	 * @throws PlayerValidationException 
	 */
	List<PlayerTO> findChallengeSuggestions(UserTO user) throws PlayerValidationException;

	/**
	 * znajduje wszystkie challenge dla uzytkownika wyslane i otrzymane 
	 * 
	 * @param user
	 *            uzytkownik dla ktorego metoda znajduje challenge
	 * @return zwraca liste challenge dla okreslonego uzytkownika
	 * @throws PlayerValidationException 
	 */
	List<ChallengeTO> findExistingChallengeForUser(UserTO user) throws PlayerValidationException;

	/**
	 * wyswietlanie informacji o przeciwniku, jego statystykach
	 * @param challenge
	 * @return
	 */
	PlayerTO showInformationAboutPlayer(ChallengeTO challenge);
}
