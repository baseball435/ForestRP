package com.client.scenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.FEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.client.Forest;
import com.client.gui.GUI;
import com.client.gui.InventoryGUI;
import com.client.scene.BaseScene;
import com.common.map.Map;
import com.common.map.tiles.Tile;
import com.common.player.Player;

public class GameScene extends BaseScene {

	public static Map currentMap;

	public static int tickRate = 15;// 15 = 4t/sec

	public GUI currentGUI;

	int count = 0;

	public static ArrayList<Player> players = new ArrayList<Player>();

	public GameScene() {
		super("GameScene");
		currentMap = new Map("TestMap", 50, 50);
		currentGUI = new InventoryGUI();
		for (int x = 0; x < 50; x++) {
			for (int y = 0; y < 50; y++) {
				currentMap.setTile(Tile.getTile("Grass"), x, y);
			}
		}
		currentMap.setTile(Tile.getTile("Wall"), 15, 5);
		currentMap.setTile(Tile.getTile("Wall"), 15, 6);
		currentMap.setTile(Tile.getTile("Wall"), 15, 7);
		currentMap.setTile(Tile.getTile("Wall"), 5, 5);
		players.add(Forest.player);

	}

	@Override
	public void update() {

		currentMap.update();
		try {
			for (Player player : players) {
				player.update(currentMap, false);
			}
		} catch (Exception e) {
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			if (Forest.player.walkSpeed != 3f) {
				Forest.player.setWalkSpeed(1.5f);
			}
		} else {
			if (Forest.player.walkSpeed != 1f) {
				Forest.player.setWalkSpeed(1f);
			}
		}

		reorderPlayers();
	}

	Comparator<Player> comp = new Comparator<Player>() {
		@Override
		public int compare(Player one, Player two) {
			if (one.y == two.y)
				return 0;
			else if (one.y > two.y)
				return 1;
			else
				return -1;
		}
	};

	public void reorderPlayers() {
		Collections.sort(players, comp);
	}

	@Override
	public void render(SpriteBatch batch) {
		currentMap.render(batch, Forest.player, 11);
		try {
			for (Player player : players) {
				if (player != Forest.player) {
					player.render(batch, Forest.player);
				} else {
					Forest.player.render(batch, FEngine.centerInt, FEngine.centerInt);
				}
			}
		} catch (Exception e) {
		}
		for (Player player : players) {
			if (player != Forest.player) {
				// player.renderName(batch, Forest.player);
			}
		}
		currentMap.renderShadows(batch, Forest.player, 11);

		currentGUI.render(batch);

	}

	public void renderGUI(SpriteBatch batch) {

	}

	@Override
	public void added() {
	}

	@Override
	public void removed() {
	}

}
