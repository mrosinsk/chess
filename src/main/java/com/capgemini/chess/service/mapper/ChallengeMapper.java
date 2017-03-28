package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.service.to.ChallengeTO;

@Component
public class ChallengeMapper {

	@Autowired
	private AbstractMapper abstractMapper;

	public ChallengeTO map(ChallengeEntity entity) {
		if (entity != null) {
			ChallengeTO to = new ChallengeTO();
			to.setChallengeStatus(entity.getChallengeStatus());
			abstractMapper.abstractTO(entity, to);
			to.setIdPlayer(entity.getIdPlayer());
			to.setIdOpponent(entity.getIdOpponent());
			return to;
		}
		return null;
	}

	public ChallengeEntity map(ChallengeTO to) {
		if (to != null) {
			ChallengeEntity entity = new ChallengeEntity();
			entity.setChallengeStatus(to.getChallengeStatus());
			abstractMapper.abstractEntity(to, entity);
			entity.setIdPlayer(to.getIdPlayer());
			entity.setIdOpponent(to.getIdOpponent());
			return entity;
		}
		return null;
	}

	public List<ChallengeTO> mapChallengeTOList(List<ChallengeEntity> entities) {
		// this - biore instancje tej klasy w ktorej jestem
		return entities.stream().map(this::map).collect(Collectors.toList());
	}

	public List<ChallengeEntity> mapChallengeEntityList(List<ChallengeTO> to) {
		// this - biore instancje tej klasy w ktorej jestem
		return to.stream().map(this::map).collect(Collectors.toList());
	}

}
