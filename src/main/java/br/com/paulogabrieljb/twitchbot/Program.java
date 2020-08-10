package br.com.paulogabrieljb.twitchbot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.TwirkBuilder;

import br.com.paulogabrieljb.twitchbot.factories.UserFactory;
import br.com.paulogabrieljb.twitchbot.listeners.DisconectListener;
import br.com.paulogabrieljb.twitchbot.listeners.MessageListener;
import br.com.paulogabrieljb.twitchbot.model.User;

public class Program {

	public static void main(String args[]) throws IOException, InterruptedException {
		
		System.out.print("Insira o canal no qual você vai entrar: ");
		Scanner scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));
		String channel = "#" + scanner.nextLine();

		User user = UserFactory.getUser();
		
		Twirk twirk = new TwirkBuilder(channel, user.getUsername(), user.getToken())
				.setVerboseMode(true)
				.build(); 

		twirk.addIrcListener(new DisconectListener(twirk));
		twirk.addIrcListener(new MessageListener(twirk, user));
		
		if(user.getInteract())
			System.out.println("Interção com o chat via mensagens ativada!");

		System.out.println("\nPara sair, digiite .quit e aperte enter Enter\n");

		twirk.connect(); 

		String line;
		while (!(line = scanner.nextLine()).matches(".quit"))
			twirk.channelMessage(line);

		scanner.close();
		twirk.close(); 

	}

}
