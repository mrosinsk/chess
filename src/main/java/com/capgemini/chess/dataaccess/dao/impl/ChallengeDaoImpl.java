package com.capgemini.chess.dataaccess.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.dataaccess.entities.QChallengeEntity;
import com.capgemini.chess.service.mapper.ChallengeMapper;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class ChallengeDaoImpl extends AbstractDao<ChallengeEntity, Long> implements ChallengeDao {

	@Autowired
	private ChallengeMapper challengeMapper;
	
	@Override
	public Long countAllChallenges(){
		return (Long) entityManager.createQuery("Select count(*) from ChallengeEntity").getSingleResult();
	}
	
	@Override
	public boolean updateChallenge(ChallengeTO challengeTO) {
		try {
			serchChallenge(challengeTO.getId());
		} catch (Exception e) {
			return false;
		}
		entityManager.merge(challengeMapper.map(challengeTO));
		return true;
	}

	// QueryDSL
	@Override
	public List<ChallengeTO> findReceivedChallengesForPlayer(PlayerTO player) {
		JPAQuery query = new JPAQuery(entityManager);
		QChallengeEntity challengeEntity = QChallengeEntity.challengeEntity;

		Long idPlayer = player.getId();
		List<ChallengeEntity> resultList = query.from(challengeEntity).where(challengeEntity.idOpponent.id.eq(idPlayer))
				.list(challengeEntity);

		return challengeMapper.mapChallengeTOList(resultList);
	}

	// jpql
	@Override
	public ChallengeTO serchChallenge(Long idChallenge) {
		TypedQuery<ChallengeEntity> query = entityManager
				.createQuery("select ch from ChallengeEntity ch where ch.id = :idCh", ChallengeEntity.class);
		query.setParameter("idCh", idChallenge);
		return challengeMapper.map(query.getSingleResult());
	}

	// QueryDSL
	@Override
	public List<ChallengeTO> serchSentChallengeForUser(UserTO userTO) {
		JPAQuery query = new JPAQuery(entityManager);
		QChallengeEntity challengeEntity = QChallengeEntity.challengeEntity;

		PlayerTO player = userTO.getPlayer();
		Long idPlayer = player.getId();
		List<ChallengeEntity> resultList = query.from(challengeEntity).where(challengeEntity.idPlayer.id.eq(idPlayer))
				.list(challengeEntity);

		return challengeMapper.mapChallengeTOList(resultList);
	}



}
