package com.capgemini.chess.service;

import java.util.List;
import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.exception.BusinessEexception;
import com.capgemini.chess.exception.ChallengeValidationException;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

public interface ChallengeService {

	/**
	 * tworzy automatyczna liste potencjalnych oponentow do challenges
	 * 
	 * @param user
	 *            gracz dla ktorego metoda tworzy liste oponentow
	 * @return zwraca liste potencjalnych oponentow do challenges
	 * @throws PlayerValidationException 
	 */
	List<PlayerTO> createChallengeSugestions(UserTO user) throws PlayerValidationException;

	/**
	 * tworzy liste wyslanych przez gracza challenges
	 * 
	 * @param user
	 *            gracz dla ktorego metoda tworzy liste wyslanych przez niego
	 *            challenges
	 * @return zwraca liste wyslanych przez gracza challenges
	 */
	List<ChallengeTO> getSentChallenges(UserTO user);

	/**
	 * tworzy liste odebranych przez gracza challenges
	 * 
	 * @param user
	 *            uzytkownik dla ktorego lista jest tworzona
	 * @return zwraca liste odebranych przez gracza challenges
	 * @throws PlayerValidationException 
	 */
	List<ChallengeTO> getReceivedChallenges(UserTO user) throws PlayerValidationException;
	
	/**
	 * tworzy liste wszystkich challenges dla uzytkownika (wysłanych i odebranych)
	 * @param user
	 * @return
	 * @throws PlayerValidationException 
	 */
	List<ChallengeTO> getAllChallenges(UserTO user) throws PlayerValidationException;

	/**
	 * wysyla chellenga
	 * 
	 * @param fromUser
	 *            uzytkownik wysylajacy challenge
	 * @param toUserNick
	 *            uzytkownik do ktorego wysylany jest challenge
	 * @return zwraca TRUE jesli wyslanie challenge sie powiedzie
	 * @throws ChallengeValidationException 
	 */
	ChallengeTO sendChallenge(UserTO fromUser, String toUserNick) throws BusinessEexception;

	/**
	 * zmienia status challenge
	 * 
	 * @param challenge
	 *            challenge, ktorego status zostanie zmieniony
	 * @param status
	 *            nazwa statusu jaki zostanie ustawiony
	 * @return
	 * @throws ChallengeValidationException 
	 */
	ChallengeTO makeDecision(ChallengeTO challenge, StatusChallenge status) throws ChallengeValidationException;
	
	/**
	 * zapisz do bazy, utworz nowy challenge
	 * @param idPlayer
	 * @param idOpponent
	 * @param challengeStatus
	 * @return
	 */
	ChallengeTO saveChallenge(Long idPlayer, Long idOpponent, StatusChallenge challengeStatus);

	/**
	 * czy mecz moze byc rozpoczety
	 * @param challenge
	 * @return
	 * @throws ChallengeValidationException
	 */
	boolean canMatchBeStarted(ChallengeTO challenge) throws ChallengeValidationException;

}
