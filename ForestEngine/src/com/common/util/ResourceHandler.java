package com.common.util;

import java.util.HashMap;

import com.FEngine;
import com.common.image.SpriteOGL;

/**
 * 
 * @author Buzzyboy
 * 
 *         Global class that holds all sprites in a hashmap.
 * 
 */
public class ResourceHandler {

	public static HashMap<String, SpriteOGL> sprites = new HashMap<String, SpriteOGL>();

	public static void addSprite(String filePath, int tileSize) {
		SpriteOGL newSprite = new SpriteOGL(filePath, tileSize);
		sprites.put(filePath, newSprite);
	}

	/**
	 * 
	 * @param filePath
	 *            -Filepath of the sprite's image
	 * @return-The sprite reference you're trying to retrieve
	 */
	public static SpriteOGL getSprite(String filePath) {
		if (sprites.get(filePath) == null) {
			addSprite(filePath, FEngine.TILE_SIZE);
		}

		return sprites.get(filePath);
	}

}
