package com.capgemini.chess.service.mapper;

import org.springframework.stereotype.Component;

import com.capgemini.chess.dataaccess.entities.AbstractEntity;
import com.capgemini.chess.service.to.AbstractTO;

@Component
public class AbstractMapper {

	public void abstractTO(AbstractEntity entity, AbstractTO to) {
		if (entity != null) {
			// AbstractTO to = new AbstractTO();
			to.setId(entity.getId());
			to.setVersion(entity.getVersion());
			to.setCreateDate(entity.getCreateDate());
			to.setModifyDate(entity.getModifyDate());
		}
	}

	public void abstractEntity(AbstractTO to, AbstractEntity entity) {
		if (to != null) {
			// AbstractEntity entity = new AbstractEntity();
			entity.setId(to.getId());
			entity.setVersion(to.getVersion());
			entity.setCreateDate(to.getCreateDate());
			entity.setModifyDate(to.getModifyDate());
		}
	}

}
