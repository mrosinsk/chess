package com.capgemini.chess.dataaccess.source;

import java.util.Map;

import com.capgemini.chess.dataaccess.entities.ChallengeEntity;

public interface MapChallengeSource {

	Map<Long, ChallengeEntity> getChallenge();
}
