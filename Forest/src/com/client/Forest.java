package com.client;

import java.util.HashMap;

import com.FEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.client.frames.mapmaker.MapMakerFrame;
import com.client.net.ServerConnection;
import com.client.scene.SceneHandler;
import com.client.scenes.GameScene;
import com.common.map.Map;
import com.common.player.Badger;
import com.common.player.Player;

@SuppressWarnings("serial")
public class Forest extends FEngine {

	public static Player player;// reference to the player that the client is
								// logged into

	public static ServerConnection server;

	public static Forest INSTANCE;

	public static MapMakerFrame mapMaker;

	public Map map;
	public static HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	public static boolean nightTime = true;

	Texture texture;

	public Forest() {
		// GameScene gameScene = new GameScene();
		// SceneHandler.setScene(gameScene);

		INSTANCE = this;
		connectToServer();

	}

	Sprite sprite;

	@Override
	public void create() {
		super.create();

		Texture.setEnforcePotImages(false);
		player = new Badger();
		GameScene gs = new GameScene();
		SceneHandler.setScene(gs);
	}

	public static void connectToServer() {
		try {
			// server = new ServerConnection(Main.serverIP, Main.serverPort);
			// Movable.comm = server;
		} catch (Exception e) {
		}
	}

	@Override
	public void update() {
		if (server == null || !server.isConnected()) {
			paused = true;
			// SceneHandler.deleteScene();
			// LoginFrame lf = new LoginFrame();
			// lf.message.setText("Disconnected from server");
		}
	}

	@Override
	public void render(SpriteBatch batch) {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	public static boolean isKeyPressed(int... keys) {
		for (int key : keys) {
			if (!Gdx.input.isKeyPressed(key)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
