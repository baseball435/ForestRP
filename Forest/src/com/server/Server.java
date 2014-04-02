package com.server;

import java.net.Socket;
import java.util.ArrayList;

import com.common.movable.Movable;

@SuppressWarnings("serial")
public class Server extends FServer implements Runnable {

	public Server() {
		setUpGUI();
		SQLHandler.INSTANCE = new SQLHandler();
		Thread t = new Thread(this);
		t.start();
		startServer(3024);
	}

	public void broadCastLoggedin(String cmd, String data, Client... notToAdd) {
		for (Client c : loggedInClients(notToAdd)) {
			c.sendPacket(cmd, data);
		}
	}

	public ArrayList<Client> loggedInClients(Client... notToAdd) {
		ArrayList<Client> lic = new ArrayList<Client>();
		if (clients == null) {
			clients = new ArrayList<ConnectionToClient>();
		}
		for (ConnectionToClient ctc : clients) {
			Client c = (Client) ctc;
			if (c.player != null && c.fullyLoggedIn) {
				lic.add(c);
			}
		}
		if (notToAdd != null) {
			for (Client c : notToAdd) {
				lic.remove(c);
			}
		}
		return lic;
	}

	public Movable findMovable(String identity) {
		for (ConnectionToClient ctc : clients) {
			Client c = (Client) ctc;
			if (c.player != null && c.player.identity.equals(identity))
				return c.player;
		}
		return null;
	}

	@Override
	public void clientDisconnected(ConnectionToClient client) {
		System.out.println("Client disconnected.");
		clients.remove(client);
	}

	@Override
	public void clientConnected(Socket client) {
		System.out.println("Client connected.");
		clients.add(new Client(client, this));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Server();
	}

	@Override
	public void run() {
		while (!pendingCloseServer) {
			try {
				for (Client c : loggedInClients()) {
					c.update();
				}
				Thread.sleep(1000 / 60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
