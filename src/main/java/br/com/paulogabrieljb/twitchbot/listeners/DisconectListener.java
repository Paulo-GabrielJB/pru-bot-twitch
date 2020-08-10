package br.com.paulogabrieljb.twitchbot.listeners;

import java.io.IOException;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListener;

public class DisconectListener implements TwirkListener {

	private Twirk twirk;

	public DisconectListener(Twirk twirk) {
		this.twirk = twirk;
	}

	@Override
	public void onDisconnect() {
		if(twirk == null)
			throw new IllegalArgumentException("Twirk is null");
		try {
			if (!twirk.connect())
				twirk.close();
		} catch (IOException e) {
			twirk.close();
		} catch (InterruptedException e) {
		}
	}

}
