package com.capgemini.chess.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.chess.enums.StatusChallenge;
import com.capgemini.chess.exception.BusinessEexception;
import com.capgemini.chess.exception.ChallengeValidationException;
import com.capgemini.chess.exception.PlayerValidationException;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.ChangeChallengeStatusTO;
import com.capgemini.chess.service.to.NewChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserTO;

@Controller
@ResponseBody
@RequestMapping(value = "rest/challenge")
public class home {

	@Autowired
	private ChallengeService challengeService;

	@RequestMapping("/")
	public String index(Model model) {
		return "home";
	}

	/**
	 * wyswietla liste potencjalnych oponentow do challenges dla okreslonego
	 * usera
	 * 
	 * @param idUser
	 * @return
	 * @throws PlayerValidationException
	 */
	@RequestMapping(value = "/Opponent/{id}", method = RequestMethod.GET)
	public @ResponseBody List<PlayerTO> schowOpponentToChallenges(@PathVariable(value = "id") String idUser)
			throws PlayerValidationException {
		UserTO user = new UserTO();
		Long id = Long.parseLong(idUser);
		user.setId(id);
		return challengeService.createChallengeSugestions(user);
	}

	/**
	 * wyswietla liste wyslanych przez usera challenges
	 * 
	 * @param idUser
	 * @return
	 */
	@RequestMapping(value = "/Sent/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ChallengeTO> schowSentChallenges(@PathVariable(value = "id") String idUser) {
		UserTO user = new UserTO();
		Long id = Long.parseLong(idUser);
		user.setId(id);
		System.out.println("nanana "+challengeService.getSentChallenges(user).size());
		return challengeService.getSentChallenges(user);
	}

	/**
	 * wyswietla liste odebranych przez usera challenges
	 * 
	 * @param idUser
	 * @return
	 * @throws PlayerValidationException
	 */
	@RequestMapping(value = "/Received/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ChallengeTO> schowReceivedChallenges(@PathVariable(value = "id") String idUser)
			throws PlayerValidationException {
		UserTO user = new UserTO();
		Long id = Long.parseLong(idUser);
		user.setId(id);
		return challengeService.getReceivedChallenges(user);
	}

	/**
	 * wysylanie challenge
	 * 
	 * @param newChallenge
	 * @return
	 * @throws BusinessEexception
	 */
	@RequestMapping(value = "/Send", method = RequestMethod.POST)
	public ChallengeTO sendChallenge(@RequestBody NewChallengeTO newChallenge) throws BusinessEexception {
		UserTO fromUser = new UserTO();
		Long fromUserId = newChallenge.getFromUserId().getId();
		fromUser.setId(fromUserId);

		return challengeService.sendChallenge(fromUser, newChallenge.getToUserNick().getPlayer().getNick());
	}

	/**
	 * zmiana statusu challenge
	 * 
	 * @param change
	 * @return
	 * @throws ChallengeValidationException
	 */
	@RequestMapping(value = "/Status", method = RequestMethod.POST)
	public @ResponseBody ChallengeTO changeStatus(@RequestBody ChangeChallengeStatusTO change)
			throws ChallengeValidationException {

		ChallengeTO challenge = new ChallengeTO();
		Long id = change.getChallengeId().getId();
		challenge.setId(id);

		StatusChallenge status = change.getStatus();
		challenge.setChallengeStatus(status);

		return challengeService.makeDecision(challenge, status);
	}

}
