package com.common.image;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

	public ArrayList<Image> frames = new ArrayList<Image>();

	public int LoopsPerFrame = 5;

	public static Color transparentColor = new Color(255, 0, 255);

	public boolean isAnimated() {
		return (frames.size() > 1);
	}

	public int frameCount() {
		return frames.size();
	}

	public Sprite(String filePath, int tileSize) {
		BufferedImage image = ImageLoader.loadBufferedImage(filePath);
		image = ImageLoader.makeColorTransparent(image, transparentColor);

		BufferedImage[] images = ImageLoader.divideImage(image, tileSize, tileSize);
		for (BufferedImage bimg : images) {
			Image img = ImageLoader.convert(bimg);
			frames.add(img);
		}
	}

	public Image getFrame(int frameNumber) {
		return frames.get(frameNumber);
	}

}
