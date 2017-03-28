package com.capgemini.chess.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.controllers.home;
import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.ChangeChallengeStatusTO;
import com.capgemini.chess.service.to.NewChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
@WebAppConfiguration
public class HomeTest {

	@Mock
	private ChallengeService challengeService;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private home home1;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(this.challengeService);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		// mockito musi sie odwolywac do prywatnych zmiennych po stronie
		// challengeService i trzeba mu to zmapowac,zeby mockito mogl z nich
		// korzystac i zwrocic odpowiednie wartosci zamiast odwolywac sie do
		// bazy
		ReflectionTestUtils.setField(home1, "challengeService", challengeService);
	}

	@Test
	public void schowOpponentToChallengesSchouldReturnOpponent() throws Exception {
		// given
		List<PlayerTO> listaOponentow = new ArrayList<PlayerTO>();
		PlayerTO player1 = new PlayerTO();
		player1.setId(1L);
		player1.setLevel(2);
		player1.setNick("Benek");
		listaOponentow.add(player1);

		PlayerTO player2 = new PlayerTO();
		player2.setId(2L);
		player2.setLevel(4);
		player2.setNick("Jola");
		listaOponentow.add(player2);

		// gdy moj testowany rest wywola metode z challengeService z
		// jakimkolwiek argumentem to ma zwrocić stworzone przykładowe dane
		// nie testuje dzialania challengeSerwisu, bo zakladam ze dziala dobrze,
		// testuje czy przekazywanie restowe dziala poprawnie
		//
		Mockito.when(challengeService.createChallengeSugestions(Mockito.any())).thenReturn(listaOponentow);
		// when
		ResultActions response = this.mockMvc.perform(get("/rest/challenge/Opponent/1"));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("$[0].id").value(1))//
				.andExpect(jsonPath("$[0].level").value(2))//
				.andExpect(jsonPath("$[0].nick").value("Benek"))//
				.andExpect(jsonPath("$[1].id").value(2))//
				.andExpect(jsonPath("$[1].level").value(4))//
				.andExpect(jsonPath("$[1].nick").value("Jola"));//

	}

	@Test
	public void schowSentChallengesShouldReturnChallengesSentList() throws Exception {
		// given
		List<ChallengeTO> challengesSentList = new ArrayList<ChallengeTO>();
		ChallengeTO challenge1 = new ChallengeTO();
		challenge1.setId(1L);
		
		PlayerEntity player1 = new PlayerEntity();
		player1.setId(10L);
		challenge1.setIdPlayer(player1);
		
		PlayerEntity player2 = new PlayerEntity();
		player2.setId(20L);
		challenge1.setIdOpponent(player2);
		
		challengesSentList.add(challenge1);

		
		ChallengeTO challenge2 = new ChallengeTO();
		challenge2.setId(2L);
		
		PlayerEntity player3 = new PlayerEntity();
		player3.setId(30L);
		challenge2.setIdPlayer(player3);
		
		PlayerEntity player4 = new PlayerEntity();
		player4.setId(40L);
		challenge2.setIdOpponent(player4);
		
		challengesSentList.add(challenge2);

		Mockito.when(challengeService.getSentChallenges(Mockito.any())).thenReturn(challengesSentList);
		// when
		ResultActions response = this.mockMvc.perform(get("/rest/challenge/Sent/10"));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("$[0].id").value(1))//
				.andExpect(jsonPath("$[0].idPlayer.id").value(10))//
				.andExpect(jsonPath("$[0].idOpponent.id").value(20))//
				.andExpect(jsonPath("$[1].id").value(2))//
				.andExpect(jsonPath("$[1].idPlayer.id").value(30))//
				.andExpect(jsonPath("$[1].idOpponent.id").value(40));//
	}

	@Test
	public void schowReceivedChallengesShouldReturnChallengesReceivedList() throws Exception {
		// given
		List<ChallengeTO> challengesReceivedList = new ArrayList<ChallengeTO>();
		ChallengeTO challenge1 = new ChallengeTO();
		challenge1.setId(1L);
		PlayerEntity player = new PlayerEntity();
		player.setId(10L);
		challenge1.setIdPlayer(player);
		PlayerEntity opponent = new PlayerEntity();
		opponent.setId(20L);
		challenge1.setIdOpponent(opponent);
		challengesReceivedList.add(challenge1);

		ChallengeTO challenge2 = new ChallengeTO();
		challenge2.setId(2L);
		PlayerEntity player2 = new PlayerEntity();
		player2.setId(30L);
		challenge2.setIdPlayer(player2);
		PlayerEntity opponent2 = new PlayerEntity();
		opponent2.setId(20L);
		challenge2.setIdOpponent(opponent2);
		challengesReceivedList.add(challenge2);

		Mockito.when(challengeService.getReceivedChallenges(Mockito.any())).thenReturn(challengesReceivedList);
		// when
		ResultActions response = this.mockMvc.perform(get("/rest/challenge/Received/20"));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("$[0].id").value(1))//
				.andExpect(jsonPath("$[0].idPlayer.id").value(10))//
				.andExpect(jsonPath("$[0].idOpponent.id").value(20))//
				.andExpect(jsonPath("$[1].id").value(2))//
				.andExpect(jsonPath("$[1].idPlayer.id").value(30))//
				.andExpect(jsonPath("$[1].idOpponent.id").value(20));//
	}

	@Test
	public void sendChallengeSchouldReturnSendChallenge() throws Exception {
		// given
		ChallengeTO sendChallenge = new ChallengeTO();
		sendChallenge.setId(1L);
		PlayerEntity player1 = new PlayerEntity();
		player1.setId(10L);
		sendChallenge.setIdPlayer(player1);
		PlayerEntity opponent = new PlayerEntity();
		opponent.setId(20L);
		sendChallenge.setIdOpponent(opponent);

		NewChallengeTO newChallenge = new NewChallengeTO();
		UserTO fromUserId = new UserTO();
		fromUserId.setId(10L);
		newChallenge.setFromUserId(fromUserId);
		UserTO toUserNick = new UserTO();
		PlayerTO player = new PlayerTO();
		player.setNick("Zenek");
		toUserNick.setPlayer(player);
		newChallenge.setToUserNick(toUserNick);

		Mockito.when(challengeService.sendChallenge(Mockito.any(), Mockito.any())).thenReturn(sendChallenge);
		// when
		ResultActions response = this.mockMvc.perform(post("/rest/challenge/Send")//
				.contentType(MediaType.APPLICATION_JSON)//
				.content(new ObjectMapper().writeValueAsString(newChallenge)));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("$.idPlayer.id").value(10))//
				.andExpect(jsonPath("$.idOpponent.id").value(20));//
	}

	@Test
	public void changeStatusSchouldReturnNewStatus() throws Exception {
		// given
		ChallengeTO challenge = new ChallengeTO();
		challenge.setId(1L);
		StatusChallenge status1 = StatusChallenge.DECLINE;
		challenge.setChallengeStatus(status1);
		PlayerEntity player1 = new PlayerEntity();
		player1.setId(1L);
		challenge.setIdPlayer(player1);
		PlayerEntity player2 = new PlayerEntity();
		player2.setId(2L);
		challenge.setIdOpponent(player2);

		ChangeChallengeStatusTO change = new ChangeChallengeStatusTO();
		ChallengeTO changeChallenge = new ChallengeTO();
		changeChallenge.setId(1L);
		change.setChallengeId(changeChallenge);
		change.setStatus(StatusChallenge.DECLINE);

		Mockito.when(challengeService.makeDecision(Mockito.any(), Mockito.any())).thenReturn(challenge);
		// when
		ResultActions response = this.mockMvc.perform(post("/rest/challenge/Status")//
				.contentType(MediaType.APPLICATION_JSON)//
				.content(new ObjectMapper().writeValueAsString(change)));
		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("$.idPlayer.id").value(1))//
				.andExpect(jsonPath("$.idOpponent.id").value(2))//
				.andExpect(jsonPath("$.id").value(1))//
				.andExpect(jsonPath("$.challengeStatus").value("DECLINE"));
	}

}
