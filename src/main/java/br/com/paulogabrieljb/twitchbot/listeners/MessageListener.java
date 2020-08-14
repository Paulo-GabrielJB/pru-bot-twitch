package br.com.paulogabrieljb.twitchbot.listeners;

import br.com.paulogabrieljb.twitchbot.model.Generic;
import br.com.paulogabrieljb.twitchbot.services.InteractService;
import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListener;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

import br.com.paulogabrieljb.twitchbot.model.User;

import java.util.List;
import java.util.Random;

public class MessageListener implements TwirkListener {

	private Twirk twirk;
	private User user;
	private final InteractService interactService;

	public MessageListener(Twirk twirk, User user, final InteractService interactService) {
		this.twirk = twirk;
		this.user = user;
		this.interactService = interactService;
	}

	@Override
	public void onPrivMsg(TwitchUser sender, TwitchMessage message) {
		if (user == null)
			throw new IllegalArgumentException("User can't be null");

		if (message.getContent().toUpperCase().contains(user.getUsername().toUpperCase()) && user.getInteract()
				&& !sender.getDisplayName().equalsIgnoreCase(user.getUsername())) {
			List<Generic> messages = getMessages();
			if(messages.size() > 0)
				twirk.channelMessage(sender.getDisplayName() + " " + messages.get(new Random().nextInt(messages.size())).getMessage());
		}
	}

	@Override
	public void onConnect() {
		twirk.channelMessage(user.getWelcomeMessage());
	}

	private List<Generic> getMessages(){
		return interactService.findAll();
	}
}
