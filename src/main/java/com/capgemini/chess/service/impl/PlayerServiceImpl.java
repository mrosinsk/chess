package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.PlayerService;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao playerDao;

	// PRZETESTOWANA
	@Override
	public PlayerTO searchPlayer(String playerNick) throws PlayerValidationException {
		PlayerTO playerSearch;
		try {
			playerSearch = playerDao.findByNick(playerNick);
		} catch (Exception e) {
			throw new PlayerValidationException("Player doesn't exist!");
		}
		return playerSearch;
	}

	// PRZETESTOWANA
	@Override
	public PlayerTO getPlayerFromUser(UserTO user) throws PlayerValidationException {
		PlayerTO searchUser;
		try {
			searchUser = playerDao.findById(user.getId());
		} catch (Exception e) {
			throw new PlayerValidationException("Player doesn't exist!");
		}
		return searchUser;
	}

}
