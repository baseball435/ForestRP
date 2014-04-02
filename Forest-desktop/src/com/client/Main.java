package com.client;

import com.FEngine;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Forest RP";
		cfg.useGL20 = false;
		cfg.width = FEngine.WIDTH;
		cfg.height = FEngine.HEIGHT;

		new LwjglApplication(new Forest(), cfg);
	}
}
