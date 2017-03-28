package com.capgemini.chess.dataaccess.source;

import java.util.Map;

import com.capgemini.chess.dataaccess.entities.UserEntity;

public interface MapDataSource {

	Map<Long, UserEntity> getUsers();

}
