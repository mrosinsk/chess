package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.exception.BusinessEexception;
import com.capgemini.chess.exception.ChallengeValidationException;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

public interface ChallengeFacade {

	/**
	 * tworzy automatyczna liste sugerowanych challenges dla uzytkownika
	 * @param user
	 * @return
	 * @throws PlayerValidationException
	 */
	List<PlayerTO> createChallengeSugestions(UserTO user) throws PlayerValidationException;

	/**
	 * wyswietla liste wyslanych przez uzytkownika challenges
	 * @param user
	 * @return
	 */
	List<ChallengeTO> getSentChallenges(UserTO user);

	/**
	 * wyswietla liste otrzymanych przez uzytkownika challenges
	 * @param user
	 * @return
	 * @throws PlayerValidationException
	 */
	List<ChallengeTO> getReceivedChallenges(UserTO user) throws PlayerValidationException;

	/**
	 * wyswietla liste wszystkich (wyslanych i otrzymanych) przez uzytkownika challenges
	 * @param user
	 * @return
	 * @throws PlayerValidationException
	 */
	List<ChallengeTO> getAllChallenges(UserTO user) throws PlayerValidationException;

	/**
	 * wysyla challenge do bazy (zapisuje go)
	 * @param fromUser
	 * @param toUserNick
	 * @return
	 * @throws BusinessEexception
	 */
	ChallengeTO sendChallenge(UserTO fromUser, String toUserNick) throws BusinessEexception;

	/**
	 * podjecie decyzji o challenge
	 * @param challenge
	 * @param status
	 * @return
	 * @throws ChallengeValidationException
	 */
	ChallengeTO makeDecision(ChallengeTO challenge, StatusChallenge status) throws ChallengeValidationException;

	/**
	 * wyswietlanie informacji o przeciwniku, jego statystykach
	 * @param challenge
	 * @return
	 */
	PlayerTO showInformationAboutPlayer(ChallengeTO challenge);

}
