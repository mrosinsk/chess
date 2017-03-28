package com.capgemini.chess.dataaccess.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.dao.UserDao;
import com.capgemini.chess.dataaccess.entities.UserEntity;
import com.capgemini.chess.service.mapper.UserMapper;
import com.capgemini.chess.service.to.UserTO;

@Repository
public class UserDaoImpl extends AbstractDao<UserEntity, Long> implements UserDao {

	@Autowired
	private UserMapper userMaper;

	@Override
	public UserTO saveUser(String email, String password) {
		UserEntity newUser = new UserEntity();
		newUser.setEmail(email);
		newUser.setPassword(password);
		entityManager.persist(newUser);
		return userMaper.map(newUser);
	}

	// JPQL
	@Override
	public UserTO findByEmail(String email) {
		TypedQuery<UserEntity> query = entityManager.createQuery("select u from UserEntity u where u.email = :email",
				UserEntity.class);
		query.setParameter("email", email);
		return userMaper.map(query.getSingleResult());
	}

	// JPQL
	@Override
	public UserTO findById(Long id) {
		TypedQuery<UserEntity> query = entityManager.createQuery("select u from UserEntity u where u.id = :id",
				UserEntity.class);
		query.setParameter("id", id);
		return userMaper.map(query.getSingleResult());
	}

}
