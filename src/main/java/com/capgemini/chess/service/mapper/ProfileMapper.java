package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.chess.dataaccess.entities.ProfileEntity;
import com.capgemini.chess.service.to.ProfileTO;

@Component
public class ProfileMapper {

	@Autowired
	private AbstractMapper abstractMapper;
	
	public ProfileTO map(ProfileEntity entity) {
		if (entity != null) {
			ProfileTO to = new ProfileTO();
			
			abstractMapper.abstractTO(entity, to);
			//to.setId(entity.getId());
			to.setName(entity.getName());
			to.setSurname(entity.getSurname());
			to.setAboutMe(entity.getAboutMe());
			to.setLifeMotto(entity.getLifeMotto());
			return to;
		}
		return null;
	}

	public ProfileEntity map(ProfileTO to) {
		if (to != null) {
			ProfileEntity entity = new ProfileEntity();
			
			abstractMapper.abstractEntity(to, entity);
			//entity.setId(to.getId());
			entity.setName(to.getName());
			entity.setSurname(to.getSurname());
			entity.setAboutMe(to.getAboutMe());
			entity.setLifeMotto(to.getLifeMotto());
			return entity;
		}
		return null;
	}

	public List<ProfileTO> map2TOs(List<ProfileEntity> entities) {
		return entities.stream().map(this::map).collect(Collectors.toList());
	}

	public List<ProfileEntity> map2Entities(List<ProfileTO> tos) {
		return tos.stream().map(this::map).collect(Collectors.toList());
	}
}
