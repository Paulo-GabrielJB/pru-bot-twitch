package br.com.paulogabrieljb.twitchbot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import br.com.paulogabrieljb.twitchbot.configurations.DatabaseConfig;
import br.com.paulogabrieljb.twitchbot.configurations.TwirkConfigure;
import br.com.paulogabrieljb.twitchbot.listeners.InputConsoleListener;
import com.gikk.twirk.Twirk;
import com.gikk.twirk.TwirkBuilder;

import br.com.paulogabrieljb.twitchbot.factories.UserFactory;
import br.com.paulogabrieljb.twitchbot.listeners.DisconectListener;
import br.com.paulogabrieljb.twitchbot.listeners.MessageListener;
import br.com.paulogabrieljb.twitchbot.model.User;
import com.gikk.twirk.events.TwirkListener;
import org.apache.log4j.BasicConfigurator;

public class Program {

	public static void main(String args[]) throws IOException, InterruptedException {

		BasicConfigurator.configure();
		DatabaseConfig.loadDatabase();
		System.out.print("Insira o canal no qual você vai entrar: ");
		Scanner scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));
		String channel = "#" + scanner.nextLine();

		User user = UserFactory.getUser();
		
		Twirk twirk = TwirkConfigure.configureTwirk(channel, user);

		System.out.println("\nPara sair, digite .quit e aperte enter Enter\n");

		if(twirk != null)
			InputConsoleListener.readConsole(twirk);
	}

}
