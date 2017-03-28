package com.capgemini.chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.chess.dataaccess.dao.impl.ChallengeDaoImplJpaTest;
import com.capgemini.chess.dataaccess.dao.impl.PlayerDaoImplJpaTest;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImplJpaTest;
import com.capgemini.chess.rest.HomeTest;
import com.capgemini.chess.service.impl.ChallengeFacadeImplTest;
import com.capgemini.chess.service.impl.ChallengeServiceImplJpaTest;
import com.capgemini.chess.service.impl.FindChallengeServiceImplJpaTest;
import com.capgemini.chess.service.impl.PlayerServiceImplJpaTest;

@RunWith(Suite.class)
@SuiteClasses({ ChallengeDaoImplJpaTest.class, PlayerDaoImplJpaTest.class, UserDaoImplJpaTest.class, HomeTest.class,
		ChallengeFacadeImplTest.class, ChallengeServiceImplJpaTest.class, FindChallengeServiceImplJpaTest.class,PlayerServiceImplJpaTest.class })
public class AllTests {

}
