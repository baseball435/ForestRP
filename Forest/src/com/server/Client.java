package com.server;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.net.Socket;

import com.common.map.Map;
import com.common.movable.Movable;
import com.common.player.Badger;
import com.common.player.Player;
import com.server.player.Account;

public class Client extends ConnectionToClient {

	public boolean fullyLoggedIn = false;
	public Player player;
	public Server server;

	public int ticks = 0;

	float lastX, lastY;

	public Map currentMap = new Map("TestMap", 15, 15);

	public void update() {
		// player.update(currentMap, true);

	}

	public Client(Socket socket, FServer server) {
		super(socket, server);
		this.server = (Server) server;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void packetReceived(Object packet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void packetReceived(String cmd, String data) {
		if (cmd.equals("Login")) {
			String[] splits = data.split("!@#");
			Account acc = SQLHandler.loadAccount(splits[0]);
			if (acc == null) {
				acc = new Account(splits[0], splits[1]);
				SQLHandler.saveAccount(acc);
				System.out.println("Creating account: " + splits[0]);
				return;
			}

			if (acc != null) {
				if (acc.correctPassword(splits[1])) {
					System.out.println("Correct login: " + splits[0]);
					for (Client c : server.loggedInClients()) {
						if (c.player.identity.equals(splits[0])) {
							System.out.println("Already logged in apparently...");
							c.closeConnection();
						}
					}
					Player player = new Badger();
					player.identity = splits[0];
					player.name = splits[0];
					player.savePlayer();
					this.player = player;
					sendPacket(player);
					for (ConnectionToClient ctc : server.clients) {
						Client c = (Client) ctc;
						if (c.fullyLoggedIn && c.player != null && c != this) {
							sendPacket(c.player);
							c.sendPacket(player);
						}
					}
					fullyLoggedIn = true;
				} else {
					System.out.println("Incorrect login: " + splits[0]);
					System.out.println(splits[1] + " / " + acc.password);
				}
			}
		} else if (cmd.equals("Move")) {
			player.move(Integer.parseInt(data));
			float oldx = player.x;
			float oldy = player.y;
			player.x = player.actualX;
			player.y = player.actualY;
			if (currentMap.blockedMovement(player, 0, 0, true) != null) {
				player.x = oldx;
				player.y = oldy;
				player.actualX = (int) oldx;
				player.actualY = (int) oldy;
				return;
			}
			server.broadCastLoggedin(cmd, player.identity + "," + player.actualX + ","
					+ player.actualY + "," + player.direction);
		} else if (cmd.equals("KeyEvent")) {
			String[] splits = data.split(",");
			boolean value = Boolean.parseBoolean(splits[1]);
			int e = Integer.parseInt(splits[0]);
			if (e == KeyEvent.VK_LEFT) {
				player.west = value;
			}
			if (e == KeyEvent.VK_RIGHT) {
				player.east = value;
			}
			if (e == KeyEvent.VK_UP) {
				player.north = value;
			}
			if (e == KeyEvent.VK_DOWN) {
				player.south = value;
			}
		} else if (cmd.equals("SetInt") || cmd.equals("SetString")
				|| cmd.equals("SetFloat")) {
			String[] splits = data.split(",");
			Movable M = server.findMovable(splits[0]);
			if (M == player) {
				String[] splits2 = splits[1].split("=");
				try {
					if (cmd.equals("SetInt")) {
						M.setField(splits2[0], Integer.parseInt(splits2[1]));
					}
					if (cmd.equals("SetString")) {
						M.setField(splits2[0], splits2[1]);
					}
					if (cmd.equals("SetFloat")) {
						M.setField(splits2[0], Float.parseFloat(splits2[1]));
					}
					for (Client c : server.loggedInClients(this)) {
						c.sendPacket(cmd, data);
					}
				} catch (Exception e) {
					for (Field f : M.getClass().getFields()) {
						System.out.println(f.getName());
					}
					this.closeConnection();
					e.printStackTrace();
				}
			} else {
				System.out.println(data);
				System.out.println(player.identity
						+ " is trying to edit someone else. Packet editing?");
			}
		}
	}

	@Override
	public void disconnect() {
		server.clientDisconnected(this);
		if (player != null) {
			for (ConnectionToClient client : server.clients) {
				if (client == this || !client.isConnected()) {
					continue;
				}
				client.sendPacket("Logout", player.identity);
			}
		}
	}

}
