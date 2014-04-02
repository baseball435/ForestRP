package com.client.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GUI {
	public boolean visible = false;

	public abstract void update();

	public abstract void render(SpriteBatch batch);

	public void remove() {
		visible = false;
		dispose();
	}

	public abstract void dispose();

}
