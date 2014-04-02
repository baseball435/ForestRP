package com;

import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.client.scene.SceneHandler;

public abstract class FEngine implements ApplicationListener {

	/**
	 * Whether or not to print System.out.print messages
	 */
	public static boolean DEBUG = true;

	public static String LOG_PATH = "logs.txt";// If null, no logs.txt is
												// written to
	public static String LOG_ERROR_PATH = "errors.txt";

	public static int WIDTH = 19 * 32;
	public static int HEIGHT = 19 * 32;

	public static int TILE_SIZE = 32;

	public static int centerInt = WIDTH / 2 - 16;

	public static boolean openGL = true;

	public boolean paused = false;

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;

	public Thread thread;

	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public static void log(Object msg) {
		if (DEBUG) {
			if (LOG_PATH != null) {
				writeToFile(LOG_PATH, msg);
			}
			System.out.println(msg);
		}
	}

	public static void log(Exception err) {
		err.printStackTrace();
		if (LOG_ERROR_PATH != null) {
			writeToFile(LOG_ERROR_PATH, err.getMessage());
		}
	}

	public static void writeToFile(String file, Object text) {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			new PrintStream(fout).print(text.toString());
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void update();

	public abstract void render(SpriteBatch batch);

	@Override
	public void render() {
		update();
		SceneHandler.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

		render(batch);
		SceneHandler.render(batch);

		batch.end();

	}

	public void run() {

		Graphics2D g2d;
		while (true) {
			if (paused) {
				continue;
			}
			/*
			 * update(); SceneHandler.update(); g2d = (Graphics2D)
			 * getBufferStrategy().getDrawGraphics(); g2d.clearRect(0, 0,
			 * getWidth(), getHeight()); SceneHandler.render(g2d); render(g2d);
			 * 
			 * g2d.dispose(); getBufferStrategy().show();
			 * 
			 * try { Thread.sleep(SKIP_TICKS); } catch (Exception e) { }
			 */
		}
	}
}
