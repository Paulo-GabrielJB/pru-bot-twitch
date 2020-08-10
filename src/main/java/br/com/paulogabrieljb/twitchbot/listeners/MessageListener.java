package br.com.paulogabrieljb.twitchbot.listeners;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListener;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

import br.com.paulogabrieljb.twitchbot.model.User;

public class MessageListener implements TwirkListener {

	private Twirk twirk;
	private User user;

	public MessageListener(Twirk twirk, User user) {
		this.twirk = twirk;
		this.user = user;
	}

	@Override
	public void onPrivMsg(TwitchUser sender, TwitchMessage message) {
		if (user == null)
			throw new IllegalArgumentException("User can't be null");

		if (message.getContent().toUpperCase().contains(user.getUsername().toUpperCase()) && user.getInteract()
				&& sender.getDisplayName().equalsIgnoreCase(user.getUsername()))
			twirk.channelMessage(sender.getDisplayName() + " mensagem teste bot twitch java");
	}

}
