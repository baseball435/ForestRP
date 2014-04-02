package com.client.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InventoryGUI extends GUI {

	public Texture texture;

	public int sx, sy;

	public InventoryGUI() {
		texture = new Texture(Gdx.files.internal("images/gui/itemholder.png"));
		sx = (19 * 32) / 2;
		sx -= 120;
		sy = sx - 80;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(SpriteBatch batch) {

		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 4; y++) {
				batch.draw(texture, sx + (x * 40), sy + (y * 40));
			}
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
