package com.client.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BaseScene {

	public boolean visible = true;

	public boolean disposeOnClose = false;

	public String name;

	public BaseScene(String name) {
		this.name = name;
		SceneHandler.addScene(this);
	}

	public abstract void update();

	public abstract void render(SpriteBatch batch);

	public abstract void added();

	public abstract void removed();

}
