package com.client;

import com.client.frames.LoginFrame;

/**
 * 
 * @author Buzzyboy
 * 
 *         Contains the main method
 */
public class Main {

	public static LoginFrame loginFrame;
	public static Forest game;

	public static String serverIP = "localhost";
	// public static String serverIP = "72.219.179.69";
	public static int serverPort = 3024;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// game = new Forest();
		loginFrame = new LoginFrame();
	}
}
