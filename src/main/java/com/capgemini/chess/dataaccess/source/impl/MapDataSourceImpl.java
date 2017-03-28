package com.capgemini.chess.dataaccess.source.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.capgemini.chess.dataaccess.entities.UserEntity;
import com.capgemini.chess.dataaccess.source.MapDataSource;

@Component
public class MapDataSourceImpl implements MapDataSource {

	private Map<Long, UserEntity> users = new HashMap<Long, UserEntity>();

	@Override
	public Map<Long, UserEntity> getUsers() {
		return users;
	}

}
