package com.common.image;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.FEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteOGL {

	public Texture texture;
	public ArrayList<TextureRegion> frames;

	public int LoopsPerFrame = 5;

	public static Color transparentColor = new Color(255, 0, 255);

	public boolean isAnimated() {
		return frames != null;
	}

	public int frameCount() {
		return frames.size();
	}

	public SpriteOGL(String filePath, int tileSize) {
		if (!FEngine.openGL)
			return;
		try {
			filePath = "images" + filePath;
			texture = new Texture(Gdx.files.internal(filePath));
			if (texture.getWidth() == tileSize && texture.getHeight() == tileSize) {

			} else {
				frames = new ArrayList<TextureRegion>();
				for (int y = 0; y < texture.getHeight(); y += tileSize) {
					for (int x = 0; x < texture.getWidth(); x += tileSize) {
						TextureRegion region = new TextureRegion(texture, x, y, tileSize,
								tileSize);
						frames.add(region);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to load resource: " + filePath);
			e.printStackTrace();
		}
	}

	public void SpriteOGLOLD(String filePath, int tileSize) {
		BufferedImage image = ImageLoader.loadBufferedImage(filePath);
		image = ImageLoader.makeColorTransparent(image, transparentColor);

		BufferedImage[] images = ImageLoader.divideImage(image, tileSize, tileSize);
		for (BufferedImage bimg : images) {
			Image img = ImageLoader.convert(bimg);
			// frames.add(img);
		}
	}

	public TextureRegion getFrame(int frameNumber) {
		return frames.get(frameNumber);
	}

	public Texture getTexture() {
		return texture;
	}

	public void render(SpriteBatch batch, float x, float y, int frame) {
		if (frames == null) {
			batch.draw(texture, x, y);
		} else {
			batch.draw(getFrame(frame), x, y);
		}
	}

}
