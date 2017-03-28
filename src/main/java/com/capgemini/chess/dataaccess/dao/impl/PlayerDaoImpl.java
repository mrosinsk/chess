package com.capgemini.chess.dataaccess.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.dataaccess.entities.QChallengeEntity;
import com.capgemini.chess.service.mapper.PlayerMapper;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class PlayerDaoImpl extends AbstractDao<PlayerEntity, Long> implements PlayerDao {

	@Autowired
	private PlayerMapper playerMapper;

	@Override
	public List<PlayerTO> getOponents(int levelFrom, int levelTo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlayerEntity> query = criteriaBuilder.createQuery(PlayerEntity.class);
		Root<PlayerEntity> root = query.from(PlayerEntity.class);

		Predicate levelPlayerPredicate = criteriaBuilder.between(root.get("level"), levelFrom, levelTo);
		query.where(levelPlayerPredicate);

		return playerMapper.mapPlayerTOList(entityManager.createQuery(query).getResultList());
	}

	@Override
	public PlayerTO findByNick(String nick) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlayerEntity> query = criteriaBuilder.createQuery(PlayerEntity.class);
		Root<PlayerEntity> root = query.from(PlayerEntity.class);

		Predicate nickPlayerPredicate = criteriaBuilder.equal(root.get("nick"), nick);
		query.where(nickPlayerPredicate);

		return playerMapper.map(entityManager.createQuery(query).getSingleResult());
	}

	@Override
	public PlayerTO findById(Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlayerEntity> query = criteriaBuilder.createQuery(PlayerEntity.class);
		Root<PlayerEntity> root = query.from(PlayerEntity.class);

		Predicate idPlayerPredicate = criteriaBuilder.equal(root.get("id"), id);
		query.where(idPlayerPredicate);

		return playerMapper.map(entityManager.createQuery(query).getSingleResult());
	}

	@Override
	public List<PlayerTO> findByNickAndLevel(String nick, int level) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<PlayerEntity> query = criteriaBuilder.createQuery(PlayerEntity.class);
		Root<PlayerEntity> root = query.from(PlayerEntity.class);

		Predicate nickPlayerPredicate = criteriaBuilder.equal(root.get("nick"), nick);
		Predicate levelPlayerPredicate = criteriaBuilder.equal(root.get("level"), level);

		query.where(levelPlayerPredicate, nickPlayerPredicate);

		return playerMapper.mapPlayerTOList(entityManager.createQuery(query).getResultList());
	}


}
