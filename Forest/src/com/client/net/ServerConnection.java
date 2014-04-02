package com.client.net;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import com.FEngine;
import com.client.Forest;
import com.client.Main;
import com.client.scene.SceneHandler;
import com.client.scenes.GameScene;
import com.common.movable.Movable;
import com.common.player.Player;

public class ServerConnection extends ConnectionToServer {

	public ServerConnection(String server, int port) throws IOException {
		super(server, port);
	}

	public ServerConnection(Socket socket) {
		super(socket);
	}

	@Override
	public Movable findMovableByIdentity(String id) {
		for (Player player : GameScene.players) {
			if (player.identity.equals(id))
				return player;
		}
		return null;
	}

	@Override
	public void disconnect() {
		Movable.comm = null;
		Forest.server = null;
	}

	@Override
	public void packetReceived(Object packet) {
		if (packet instanceof Player) {
			if (Forest.player == null) {
				Forest.player = (Player) packet;
				Forest.player.actualX = (int) Forest.player.x;
				Forest.player.actualY = (int) Forest.player.y;
				GameScene gameScene = new GameScene();
				SceneHandler.setScene(gameScene);
				Main.loginFrame.setVisible(false);
				Main.loginFrame = null;// This will make sure it gets removed in
										// gb

				/*
				 * Main.game.frame.setVisible(true); if (!Main.game.paused) {
				 * Main.game.startLoop(); } else { Main.game.paused = false; }
				 * Forest.INSTANCE.requestFocus();
				 */
			} else {
				Player p = (Player) packet;
				p.actualX = (int) p.x;
				p.actualY = (int) p.y;
				GameScene.players.add(p);
			}
		}
	}

	@Override
	public void packetReceived(String cmd, String data) {
		if (cmd.equals("Logout")) {
			Player p = (Player) findMovableByIdentity(data);
			GameScene.players.remove(p);
		} else if (cmd.equals("Move")) {
			String[] splits = data.split(",");
			Player player = (Player) findMovableByIdentity(splits[0]);
			player.actualX = Integer.parseInt(splits[1]);
			player.actualY = Integer.parseInt(splits[2]);
			player.direction = Integer.parseInt(splits[3]);
			System.out.println(data);
		} else if (cmd.equals("KeyEvent")) {
			String[] splits = data.split(",");
			Player player = (Player) findMovableByIdentity(splits[0]);
			if (player == null)
				return;
			int key = Integer.parseInt(splits[1]);
			boolean value = Boolean.parseBoolean(splits[2]);
			if (key == KeyEvent.VK_LEFT) {
				player.west = value;
			}
			if (key == KeyEvent.VK_RIGHT) {
				player.east = value;
			}
			if (key == KeyEvent.VK_UP) {
				player.north = value;
			}
			if (key == KeyEvent.VK_DOWN) {
				player.south = value;
			}
		} else if (cmd.equals("SetInt") || cmd.equals("SetString")
				|| cmd.equals("SetFloat")) {
			String[] splits = data.split(",");
			Movable M = findMovableByIdentity(splits[0]);
			String[] splits2 = splits[1].split("=");
			try {
				if (cmd.equals("SetInt")) {
					M.setField(splits2[0], Integer.parseInt(splits2[1]));
				}
				if (cmd.equals("SetFloat")) {
					M.setField(splits2[0], Float.parseFloat(splits2[1]));
				}
				if (cmd.equals("SetString")) {
					M.setField(splits2[0], splits2[1]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
