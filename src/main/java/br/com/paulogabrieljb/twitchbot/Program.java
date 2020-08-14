package br.com.paulogabrieljb.twitchbot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import br.com.paulogabrieljb.twitchbot.db.DatabaseFactory;
import br.com.paulogabrieljb.twitchbot.configurations.TwirkConfigure;
import br.com.paulogabrieljb.twitchbot.listeners.InputConsoleListener;
import br.com.paulogabrieljb.twitchbot.listeners.ListenerFactory;
import com.gikk.twirk.Twirk;

import br.com.paulogabrieljb.twitchbot.model.UserFactory;
import br.com.paulogabrieljb.twitchbot.model.User;
import org.apache.log4j.BasicConfigurator;

public class Program {

	public static void main(String args[]) {

		BasicConfigurator.configure();
		DatabaseFactory.loadDatabase();
		
		Twirk twirk = TwirkConfigure.getTwirk();

		System.out.println("\nFor help type .help and hit enter\n");

		if(twirk != null)
			ListenerFactory.getInputConsoleListener().readConsole();
	}

}
