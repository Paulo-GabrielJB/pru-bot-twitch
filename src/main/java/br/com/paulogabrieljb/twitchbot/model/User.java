package br.com.paulogabrieljb.twitchbot.model;

public class User {
	
	private String username;
	private String token;
	private Boolean interact;
	private String welcomeMessage;
	
	public User() {}

	public User(String username, String token, Boolean interact, String welcomeMessage) {
		this.username = username;
		this.token = token;
		this.interact = interact;
		this.welcomeMessage = welcomeMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getInteract() {
		return interact;
	}

	public void setInteract(Boolean interact) {
		this.interact = interact;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
}
