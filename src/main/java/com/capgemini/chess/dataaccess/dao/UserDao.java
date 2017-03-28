package com.capgemini.chess.dataaccess.dao;

import com.capgemini.chess.service.to.UserTO;

public interface UserDao {

	/**
	 * zapisuje uzytkownika
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	UserTO saveUser(String email, String password);

	/**
	 * znajduje uzytkownika na podstawie jego emailu
	 * 
	 * @param email
	 * @return
	 */
	UserTO findByEmail(String email);

	/**
	 * znajduje uzytkownika na podstawie jego id
	 * 
	 * @param id
	 * @return
	 */
	UserTO findById(Long id);

}
