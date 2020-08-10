package br.com.paulogabrieljb.twitchbot.factories;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import br.com.paulogabrieljb.twitchbot.exceptions.UserException;
import br.com.paulogabrieljb.twitchbot.model.User;

public class UserFactory {
	
	
	public static User getUser() {
		Properties props = loadProperties();
		String username = props.getProperty("username");
		String token = props.getProperty("token");
		Boolean interact = Boolean.parseBoolean(props.getProperty("interact"));
		String welcomeMessage = props.getProperty("enter.channel.message");
		return new User(username, token, interact, welcomeMessage);
	}
	
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("twitch.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new UserException("Please create twitch.properties file, with properties username,token and interact option (true/false)");
		}
	}
	
}
