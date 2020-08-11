package br.com.paulogabrieljb.twitchbot.factories;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import br.com.paulogabrieljb.twitchbot.configurations.DatabaseConfig;
import br.com.paulogabrieljb.twitchbot.exceptions.UserException;
import br.com.paulogabrieljb.twitchbot.model.User;
import org.apache.log4j.Logger;

public class UserFactory {

	private static final String TWITCH_PROPERTIES = "twitch.properties";
	private static final Logger LOG = Logger.getLogger(UserFactory.class);
	
	public static User getUser() {
		Properties props = loadProperties();
		String username = props.getProperty("username");
		String token = props.getProperty("token");
		Boolean interact = Boolean.parseBoolean(props.getProperty("interact"));
		String welcomeMessage = props.getProperty("enter.channel.message");
		return new User(username, token, interact, welcomeMessage);
	}
	
	
	private static Properties loadProperties() {
		LOG.info("Reading twitch.properties");
		try (FileInputStream fs = new FileInputStream(TWITCH_PROPERTIES)) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			LOG.error("File not found, creating...");
			createTwitchProperties();
			return loadProperties();
		}
	}

	private static void createTwitchProperties(){

		try(
				BufferedWriter bw = new BufferedWriter(new FileWriter(TWITCH_PROPERTIES));
		) {
			Scanner scanner = ScannerFactory.getScanner();
			System.out.print("Enter the username: ");
			String username = scanner.nextLine();

			System.out.print("Enter oauth token: ");
			String oauth = scanner.nextLine();

			System.out.print("Interact(n/y)? ");
			Character interact = scanner.nextLine().toLowerCase().charAt(0);

			System.out.print("Enter channel message: ");
			String message = scanner.nextLine();

			bw.write("username=" + username.trim());
			bw.newLine();
			bw.write("token=" + oauth.trim());
			bw.newLine();
			bw.write("interact=" + (interact.equals('y') ? "true" : "false"));
			bw.newLine();
			bw.write("enter.channel.message=" + message);
		} catch (IOException e){
			LOG.error(e.getMessage());
			System.exit(500);
		}
	}
	
}
