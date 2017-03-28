//package com.capgemini.chess.dataaccess.dao.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import com.capgemini.chess.dataaccess.entities.UserEntity;
//import com.capgemini.chess.dataaccess.source.MapDataSource;
//import com.capgemini.chess.service.to.UserTO;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserDaoImplTest {
//
//	private static final String EXISTING_EMAIL = "a@b.pl";
//
//	private static final String NOT_EXISTING_EMAIL = "b@c.pl";
//
//	@InjectMocks
//	private UserDaoImpl dao;
//
//	@Mock
//	private MapDataSource dataSource;
//
//	@Test
//	public void shouldSetIdOfSavedUserTo1WhenNoUsersYet() {
//		// given
//		UserTO user = new UserTO();
//		Mockito.when(dataSource.getUsers()).thenReturn(new HashMap<>());
//
//		// when
//		UserTO savedUser = dao.save(user);
//
//		// then
//		Assert.assertEquals(savedUser.getId(), new Long(1));
//	}
//
//	@Test
//	public void shouldGenerateAvailableIdWhenUsersExists() {
//		// given
//		UserTO user = new UserTO();
//		Mockito.when(dataSource.getUsers()).thenReturn(giveUsersMap());
//
//		// when
//		UserTO savedUser = dao.save(user);
//
//		// then
//		Assert.assertEquals(savedUser.getId(), new Long(101));
//	}
//
//	@Test
//	public void shouldInsertSavedUserIntoDataSource() {
//		// given
//		UserTO user = new UserTO();
//		Mockito.when(dataSource.getUsers()).thenReturn(giveUsersMap());
//
//		// when
//		UserTO savedUser = dao.save(user);
//
//		// then
//		Assert.assertNotNull(dataSource.getUsers().get(savedUser.getId()));
//	}
//
//	@Test
//	public void shouldFindUserByExistingEmail() {
//		// given
//		Mockito.when(dataSource.getUsers()).thenReturn(giveUsersMap());
//
//		// when
//		UserTO user = dao.findByEmail(EXISTING_EMAIL);
//
//		// then
//		Assert.assertNotNull(user);
//	}
//
//	@Test
//	public void shouldNotFindUserWhenEmailDoesNotExist() {
//		// given
//		Mockito.when(dataSource.getUsers()).thenReturn(giveUsersMap());
//
//		// when
//		UserTO user = dao.findByEmail(NOT_EXISTING_EMAIL);
//
//		// then
//		Assert.assertNull(user);
//	}
//
//	private Map<Long, UserEntity> giveUsersMap() {
//		HashMap<Long, UserEntity> users = new HashMap<Long, UserEntity>();
//		UserEntity user1 = new UserEntity();
//		user1.setId(1L);
//		user1.setEmail(EXISTING_EMAIL);
//		users.put(1L, user1);
//		users.put(5L, user1);
//		users.put(100L, user1);
//		return users;
//	}
//}
