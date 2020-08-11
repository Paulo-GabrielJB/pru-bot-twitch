package br.com.paulogabrieljb.twitchbot.listeners;

import br.com.paulogabrieljb.twitchbot.configurations.DatabaseConfig;
import br.com.paulogabrieljb.twitchbot.repositories.MessageRepository;
import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListener;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

import br.com.paulogabrieljb.twitchbot.model.User;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageListener implements TwirkListener {

	private Twirk twirk;
	private User user;
	private MessageRepository messageRepository;

	public MessageListener(Twirk twirk, User user, MessageRepository messageRepository) {
		this.twirk = twirk;
		this.user = user;
		this.messageRepository = messageRepository;
	}

	@Override
	public void onPrivMsg(TwitchUser sender, TwitchMessage message) {
		if (user == null)
			throw new IllegalArgumentException("User can't be null");

		if (message.getContent().toUpperCase().contains(user.getUsername().toUpperCase()) && user.getInteract()
				&& !sender.getDisplayName().equalsIgnoreCase(user.getUsername())) {
			List<String> messages = getMessages();
			twirk.channelMessage(sender.getDisplayName() + " " + messages.get(new Random().nextInt(messages.size())));
		}
	}

	@Override
	public void onConnect() {
		twirk.channelMessage(user.getWelcomeMessage());
	}

	private List<String> getMessages(){
		return messageRepository.findAll();
	}
}
