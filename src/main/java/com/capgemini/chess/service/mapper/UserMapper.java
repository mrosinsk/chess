package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.chess.dataaccess.entities.UserEntity;
import com.capgemini.chess.service.to.UserTO;

@Component
public class UserMapper {

	@Autowired
	private AbstractMapper abstractMapper;
	
	@Autowired
	private ProfileMapper profileMapper;
	
	@Autowired
	private PlayerMapper playerMapper;
	
	public UserTO map(UserEntity entity) {
		if (entity != null) {
			UserTO to = new UserTO();
			
			abstractMapper.abstractTO(entity, to);
			//to.setId(entity.getId());
			to.setEmail(entity.getEmail());
			to.setPassword(entity.getPassword());
			to.setProfile(profileMapper.map(entity.getProfile()));
			to.setPlayer(playerMapper.map(entity.getPlayer()));
			return to;
		}
		return null;
	}

	public UserEntity map(UserTO to) {
		if (to != null) {
			UserEntity entity = new UserEntity();
			
			abstractMapper.abstractEntity(to, entity);
			//entity.setId(to.getId());
			entity.setEmail(to.getEmail());
			entity.setPassword(to.getPassword());
			entity.setProfile(profileMapper.map(to.getProfile()));
			entity.setPlayer(playerMapper.map(to.getPlayer()));
			return entity;
		}
		return null;
	}

	public  List<UserTO> map2TOs(List<UserEntity> entities) {
		return entities.stream().map(this::map).collect(Collectors.toList());
	}

	public  List<UserEntity> map2Entities(List<UserTO> tos) {
		return tos.stream().map(this::map).collect(Collectors.toList());
	}
}
