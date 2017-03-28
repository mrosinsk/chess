package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.service.to.PlayerTO;

@Component
public class PlayerMapper {

	@Autowired
	private AbstractMapper abstractMapper;

	public PlayerTO map(PlayerEntity entity) {
		if (entity != null) {
			PlayerTO to = new PlayerTO();
			abstractMapper.abstractTO(entity, to);
			to.setPoints(entity.getPoints());
			to.setLevel(entity.getLevel());
			to.setNick(entity.getNick());
			to.setBenefit(entity.getBenefit());
			to.setLoss(entity.getLoss());
			return to;
		}
		return null;
	}

	public PlayerEntity map(PlayerTO to) {
		if (to != null) {
			PlayerEntity entity = new PlayerEntity();
			abstractMapper.abstractEntity(to, entity);
			entity.setPoints(to.getPoints());
			entity.setLevel(to.getLevel());
			entity.setNick(to.getNick());
			entity.setBenefit(to.getBenefit());
			entity.setLoss(to.getLoss());
			return entity;
		}
		return null;
	}

	public List<PlayerTO> mapPlayerTOList(List<PlayerEntity> entities) {
		return entities.stream().map(this::map).collect(Collectors.toList());
	}

	public List<PlayerEntity> mapPlayerEntityList(List<PlayerTO> to) {
		return to.stream().map(this::map).collect(Collectors.toList());
	}

}
