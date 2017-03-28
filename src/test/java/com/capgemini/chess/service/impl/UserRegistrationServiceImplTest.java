//package com.capgemini.chess.service.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InOrder;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import com.capgemini.chess.dataaccess.dao.UserDao;
//import com.capgemini.chess.service.UserCreationService;
//import com.capgemini.chess.service.UserValidationService;
//import com.capgemini.chess.service.to.RegistrationTO;
//import com.capgemini.chess.service.to.UserTO;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserRegistrationServiceImplTest {
//
//	@Mock
//	private UserDao userDao;
//
//	@Mock
//	private UserCreationService creationService;
//
//	@Mock
//	private UserValidationService validationService;
//
//	@InjectMocks
//	private UserRegistrationServiceImpl service;
//
//	@Test
//	public void shouldValidateThenCreateAndThenSaveUser() throws Exception {
//		// given
//		RegistrationTO registration = new RegistrationTO();
//		UserTO expectedUser = new UserTO();
//		Mockito.when(creationService.create(registration)).thenReturn(expectedUser);
//
//		// when
//		service.register(registration);
//
//		// then
//		InOrder inOrder = Mockito.inOrder(validationService, creationService, userDao);
//		inOrder.verify(validationService).validate(registration);
//		inOrder.verify(creationService).create(registration);
//		inOrder.verify(userDao).save(expectedUser);
//	}
//
//}
