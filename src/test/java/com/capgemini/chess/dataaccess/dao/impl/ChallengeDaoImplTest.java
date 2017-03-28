//package com.capgemini.chess.dataaccess.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
//import com.capgemini.chess.dataaccess.source.MapChallengeSource;
//import com.capgemini.chess.enums.StatusChallenge;
//import com.capgemini.chess.service.to.ChallengeTO;
//import com.capgemini.chess.service.to.PlayerTO;
//import com.capgemini.chess.service.to.UserTO;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ChallengeDaoImplTest {
//
//	@InjectMocks
//	private ChallengeDaoImpl dao;
//
//	@Mock
//	private MapChallengeSource challengeSource;
//	
//	private static final long EXISTING_ID = 1L;
//	private static final long NOT_EXISTING_ID = 20L;
//	
//	
//	
//	@Test
//	public void serchChallengeShouldReturnNotNullWhenExist() {
//		//given
//		Mockito.when(challengeSource.getChallenge()).thenReturn(giveChallengesMap());
//		//when
//		ChallengeTO chalenge = dao.serchChallenge(EXISTING_ID);
//		//then
//		Assert.assertNotNull(chalenge);
//	}
//
//	@Test
//	public void serchChallengeShouldReturnNullWhenNotExist() {
//		//given
//		Mockito.when(challengeSource.getChallenge()).thenReturn(giveChallengesMap());
//		//when
//		ChallengeTO chalenge = dao.serchChallenge(NOT_EXISTING_ID);
//		//then
//		Assert.assertNull(chalenge);
//	}
//	
//	@Test
//	public void serchSentChallengeForUserShouldReturn2() {
//		//given
//		Mockito.when(challengeSource.getChallenge()).thenReturn(giveChallengesMap());
//		//when
//		List<ChallengeTO> chalenges = dao.serchSentChallengeForUser(giveUser());
//		//then
//		Assert.assertEquals(2, chalenges.size());
//	}
//	
//	@Test
//	public void findReceivedChallengesForPlayerShouldReturn1() {
//		//given
//		Mockito.when(challengeSource.getChallenge()).thenReturn(giveChallengesMap());
//		//when
//		List<ChallengeTO> chalenges = dao.findReceivedChallengesForPlayer(givePlayer());
//		//then
//		Assert.assertEquals(2, chalenges.size());
//	}
//	 
//	
//	@Test
//	public void updateChallengeShouldReturnTrue() {
//		//given
//		Mockito.when(challengeSource.getChallenge()).thenReturn(giveChallengesMap());
//		//when
//		boolean wynik = dao.updateChallenge(givChallengeTO());
//		//then
//		Assert.assertTrue(wynik);
//	}
//	
//	@Test
//	public void updateChallengeShouldReturnFalse() {
//		//given
//		Mockito.when(challengeSource.getChallenge()).thenReturn(giveChallengesMap());
//		//when
//		boolean wynik = dao.updateChallenge(givChallengeTONotExist());
//		//then
//		Assert.assertFalse(wynik);
//	}
//	
//	@Test
//	public void saveChallengeShouldReturn() {
//		//given
//		Mockito.when(challengeSource.getChallenge()).thenReturn(giveChallengesMap());
//		//when
//		ChallengeTO challengeNew = dao.saveChallenge(givePlayer(), givePlayer2(), StatusChallenge.RECEIVED);
//		//then
//		Assert.assertNotNull(challengeSource.getChallenge().get(challengeNew.getId()));
//	}
//	
//	
//	private ChallengeTO givChallengeTONotExist(){
//		ChallengeTO challengeNew = new ChallengeTO();
//		challengeNew.setId(NOT_EXISTING_ID);
//		challengeNew.getIdPlayer().setId(2L);
//		challengeNew.getIdOpponent().setId(5L);
//		challengeNew.setChallengeStatus(StatusChallenge.ACCEPT);
//		
//		return challengeNew;
//	}
//
//	private ChallengeTO givChallengeTO(){
//		ChallengeTO challengeNew = new ChallengeTO();
//		challengeNew.setId(EXISTING_ID);
//		challengeNew.getIdPlayer().setId(2L);
//		challengeNew.getIdOpponent().setId(5L);
//		challengeNew.setChallengeStatus(StatusChallenge.ACCEPT);
//		
//		return challengeNew;
//	}
//	
//	private UserTO giveUser() {
//		UserTO user = new UserTO();
//		user.setId(2L);
//		return user;
//	}
//	
//	private PlayerTO givePlayer() {
//		PlayerTO player = new PlayerTO();
//		player.setId(5L);
//		return player;
//	}
//	
//	private PlayerTO givePlayer2() {
//		PlayerTO player = new PlayerTO();
//		player.setId(13L);
//		return player;
//	}
//	
//	private Map<Long, ChallengeEntity> giveChallengesMap() {
//		HashMap<Long, ChallengeEntity> challenges = new HashMap<Long, ChallengeEntity>();
//		ChallengeEntity challenge = new ChallengeEntity();
//		ChallengeEntity challenge1 = new ChallengeEntity();
//		ChallengeEntity challenge2 = new ChallengeEntity();
//	
//		challenge.getIdPlayer().setId(2L);
//		challenge1.getIdPlayer().setId(2L);
//		challenge2.getIdPlayer().setId(4L);
//		
//		challenge.getIdOpponent().setId(5L);
//		challenge1.getIdOpponent().setId(2L);
//		challenge2.getIdOpponent().setId(5L);
//		
//		challenge.setChallengeStatus(StatusChallenge.RECEIVED);
//		challenge1.setChallengeStatus(StatusChallenge.RECEIVED);
//		challenge2.setChallengeStatus(StatusChallenge.RECEIVED);
//		
//		challenges.put(EXISTING_ID, challenge);
//		challenges.put(2L, challenge1);
//		challenges.put(3L, challenge2);
//		return challenges;
//	}
//
//}
