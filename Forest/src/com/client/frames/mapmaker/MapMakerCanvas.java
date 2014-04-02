package com.client.frames.mapmaker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.common.map.Map;
import com.common.map.tiles.Tile;

public class MapMakerCanvas extends ApplicationAdapter implements InputProcessor {

	private SpriteBatch batch;
	private ShapeRenderer sr;

	public static String selectedTile;

	public static Map currentMap;

	public int mouseX, mouseY;
	public int mouseEndX = -1, mouseEndY = -1;
	public boolean mouseDown = false;

	@Override
	public void create() {
		super.create();
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		Gdx.input.setInputProcessor(this);
		currentMap = new Map("Default", 50, 50);
		for (int x = 0; x < 50; x++) {
			for (int y = 0; y < 50; y++) {
				currentMap.tiles[x][y] = Tile.getTile("Grass");
				currentMap.tiles[x][y].x = x;
				currentMap.tiles[x][y].y = y;

			}
		}
		MapMakerFrame.INSTANCE.canvas.setSize(MapMakerFrame.INSTANCE.newCanvas
				.getCanvas().getSize());
		MapMakerFrame.INSTANCE.newCanvas.getCanvas().setSize(currentMap.width * 32,
				currentMap.height * 32);
	}

	public void fill(int startX, int startY) {
		fillTouchingTiles(currentMap.tiles[startX][startY]);
	}

	public void fillTouchingTiles(Tile t) {
		Tile oldTile = null;
		if (t != null) {
			oldTile = currentMap.tiles[(int) t.x][(int) t.y];
		}
		currentMap.tiles[(int) t.x][(int) t.y] = Tile.getTile(selectedTile);
		currentMap.tiles[(int) t.x][(int) t.y].x = t.x;
		currentMap.tiles[(int) t.x][(int) t.y].y = t.y;
		for (int x = (int) t.x - 1; x <= t.x + 1; x++) {
			for (int y = (int) t.y - 1; y <= t.y + 1; y++) {
				Tile touchingTile = currentMap.getTile(x, y);
				if (touchingTile != null && touchingTile.icon.equals(oldTile.icon)) {
					fillTouchingTiles(touchingTile);
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void update() {
	}

	@Override
	public void render() {
		update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (currentMap != null) {
			currentMap.render(batch, true);
		}

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
				|| MapMakerFrame.INSTANCE.jRadioButtonMenuItem1.isSelected() || true) {
			sr.begin(ShapeType.Line);
			sr.setColor(Color.RED);
			if (mouseEndX == -1 || mouseEndY == -1) {
				sr.rect(mouseX * 32, mouseY * 32, 32, 32);
			} else {
				int drawX = Math.min(mouseX * 32, mouseEndX * 32);
				int drawY = Math.min(mouseY * 32, mouseEndY * 32);
				int width = Math.abs(mouseX * 32 - mouseEndX * 32);
				int height = Math.abs(mouseY * 32 - mouseEndY * 32);
				width += 32;
				height += 32;
				sr.rect(drawX, drawY, width, height);
			}
			sr.end();
		}

		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		System.out.println("Disposing");
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouseDown = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		mouseDown = false;
		if (selectedTile != null) {
			if (MapMakerFrame.INSTANCE.jRadioButtonMenuItem2.isSelected()) {
				int startX = Math.min(mouseX, mouseEndX);
				int startY = Math.min(mouseY, mouseEndY);
				int width = Math.abs(mouseX - mouseEndX) + 1;
				int height = Math.abs(mouseY - mouseEndY) + 1;
				if (mouseEndX == -1 || mouseEndY == -1) {
					startX = mouseX;
					startY = mouseY;
					width = 1;
					height = 1;
				}
				for (int x = startX; x < startX + width; x++) {
					for (int y = startY; y < startY + height; y++) {
						currentMap.tiles[x][y] = Tile.getTile(selectedTile);
						currentMap.tiles[x][y].x = x;
						currentMap.tiles[x][y].y = y;
					}
				}
			} else if (MapMakerFrame.INSTANCE.jRadioButtonMenuItem3.isSelected()) {
				fill(mouseX, mouseY);
			}
		}

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		int newX = x / 32;
		int newY = y / 32;
		if (MapMakerFrame.INSTANCE.jRadioButtonMenuItem2.isSelected()) {
			if (mouseEndX != newX || mouseEndY != newY) {
				mouseEndX = x / 32;
				mouseEndY = y / 32;
			}
		} else {
			mouseMoved(x, y);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {

		int newX = x / 32;
		int newY = (currentMap.height - 1) - (y / 32);
		if (mouseX != newX || mouseY != newY) {
			mouseX = x / 32;
			mouseY = (currentMap.height - 1) - (y / 32);
			MapMakerFrame.INSTANCE.lblInfo.setText(mouseX + " , " + mouseY);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

				if (selectedTile != null) {
					if (MapMakerFrame.INSTANCE.jRadioButtonMenuItem1.isSelected()) {
						currentMap.tiles[newX][newY] = Tile.getTile(selectedTile);
						currentMap.tiles[newX][newY].x = newX;
						currentMap.tiles[newX][newY].y = newY;
					}
				}

			}

		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
