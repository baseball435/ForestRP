package com.client.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.client.Forest;

public class HealthGUI extends GUI {

	public Texture texture;
	public TextureRegion emptyBar;
	public TextureRegion healthTR, mindTR, staminaTR;
	public BitmapFont font;

	public HealthGUI() {
		texture = new Texture(Gdx.files.internal("images/gui/emptybar.png"));
		emptyBar = new TextureRegion(texture, 0, 0, 128, 32);
		healthTR = new TextureRegion(texture, 128, 0, 1, 32);
		mindTR = new TextureRegion(texture, 129, 0, 1, 32);
		staminaTR = new TextureRegion(texture, 130, 0, 1, 32);
		font = new BitmapFont();
	}

	@Override
	public void update() {
	}

	public void drawBar(SpriteBatch batch, TextureRegion tr, int x, int y) {
		batch.draw(emptyBar, x, y);
		float perc = (Forest.player.Health / Forest.player.MaxHealth) * 118;
		batch.draw(tr, 5 + x, y, perc, 32);
		font.draw(batch, Forest.player.Health + " / " + Forest.player.MaxHealth, x + 45,
				y + 25);

	}

	@Override
	public void render(SpriteBatch batch) {
		drawBar(batch, healthTR, 15, 15);
		drawBar(batch, mindTR, 150, 15);
		drawBar(batch, staminaTR, 150 + (150 - 15), 15);
	}

	@Override
	public void dispose() {
		texture.dispose();
		font.dispose();
	}

}
