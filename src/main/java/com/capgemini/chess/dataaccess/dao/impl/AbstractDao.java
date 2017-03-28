package com.capgemini.chess.dataaccess.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
public abstract class AbstractDao<T, K extends Serializable> {

	@PersistenceContext
	protected EntityManager entityManager;

}