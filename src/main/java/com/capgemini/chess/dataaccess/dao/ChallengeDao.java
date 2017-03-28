package com.capgemini.chess.dataaccess.dao;

import java.util.List;

import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

public interface ChallengeDao {

	/**
	 * aktualizuje challenge
	 * 
	 * @param challengeTO
	 * @return
	 */
	boolean updateChallenge(ChallengeTO challengeTO);

	/**
	 * wyswietla liste otrzymanych challenges dla gracza
	 * 
	 * @param player
	 * @return
	 */
	List<ChallengeTO> findReceivedChallengesForPlayer(PlayerTO player);

	/**
	 * znajduje challenge na podstawie id
	 * 
	 * @param idChallenge
	 * @return
	 */
	ChallengeTO serchChallenge(Long idChallenge);

	/**
	 * wyswietla liste wyslanych challenges dla uzytkownika
	 * 
	 * @param userTO
	 * @return
	 */
	List<ChallengeTO> serchSentChallengeForUser(UserTO userTO);

	/**
	 * liczy wszystkie challenges w bazie
	 * 
	 * @return
	 */
	Long countAllChallenges();

}
