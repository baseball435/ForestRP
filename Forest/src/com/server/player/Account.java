package com.server.player;

import java.util.ArrayList;

public class Account {

	public String username;
	public String password;
	public ArrayList<String> players = new ArrayList<String>();

	public transient static String encryptionKey = "abc123";

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean correctPassword(String pass) {
		return password.equals(pass);
	}

	public String playersToString() {
		String str = "";
		for (String string : players) {
			str += string + ",";
		}
		if (str.contains(","))
			return str.substring(0, str.length() - 1);
		else
			return str;
	}
}
