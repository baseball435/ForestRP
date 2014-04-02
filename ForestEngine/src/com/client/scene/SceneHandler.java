package com.client.scene;

import java.util.HashMap;

import com.FEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneHandler {

	public static BaseScene currentScene;

	public static HashMap<String, BaseScene> scenes = new HashMap<String, BaseScene>();

	public static void deleteScene() {
		if (currentScene != null) {
			scenes.remove(currentScene);
			currentScene.removed();
			currentScene = null;
		}
	}

	public static void setScene(BaseScene scene) {
		if (scene == null)
			return;
		if (currentScene != null) {
			currentScene.removed();
			if (currentScene.disposeOnClose) {
				scenes.remove(currentScene.name);
			}
		}
		currentScene = scene;
		currentScene.added();
	}

	public static void setScene(String name) {
		if (scenes.get(name) != null) {
			setScene(scenes.get(name));
		} else {
			FEngine.log("StateHandler: Trying to set null scene (" + name + ")");
		}
	}

	public static void addScene(BaseScene scene) {
		scenes.put(scene.name, scene);
	}

	public static BaseScene getScene(String name) {
		return scenes.get(name);
	}

	public static void update() {
		if (currentScene != null) {
			currentScene.update();
		}
	}

	public static void render(SpriteBatch batch) {
		if (currentScene != null) {
			currentScene.render(batch);
		}
	}

}
