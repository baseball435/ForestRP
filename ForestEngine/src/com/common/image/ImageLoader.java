package com.common.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;

import javax.imageio.ImageIO;

import com.FEngine;

public class ImageLoader {

	public static BufferedImage loadBufferedImage(String file) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(file));
		} catch (Exception e) {
			FEngine.log("Failed to load: " + file);
			FEngine.log(e);
		}
		return image;
	}

	public static Image loadImage(String file) {
		Image image = null;

		try {
			image = ImageIO.read(new File(file));
		} catch (Exception e) {
			File f = new File(file);
			FEngine.log(f.getAbsolutePath());
			FEngine.log("Failed to load: " + file);
			FEngine.log(e);
		}
		return image;
	}

	public static BufferedImage[] divideImage(BufferedImage img, int divideWidth,
			int divideHeight) {
		try {
			int a = img.getWidth() / divideWidth, b = img.getHeight() / divideHeight;
			BufferedImage[] images = new BufferedImage[a * b];
			int i = 0, thex = 0;
			for (int they = 0; i < images.length;) {
				if (i < a * b + 1) {
					images[i] = img.getSubimage(thex, they, divideWidth, divideHeight);
					i++;
					thex += divideWidth;
					if (thex == img.getWidth()) {
						they += divideHeight;
						thex = 0;
					}
				}
			}
			return images;
		} catch (Exception e) {
			FEngine.log(e);
			return null;
		}
	}

	public static BufferedImage makeColorTransparent(Image image, final Color color) {
		ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB();

			@Override
			public int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB)
					return 0x00FFFFFF & rgb;
				else
					return rgb;
			}
		};
		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		return convert(Toolkit.getDefaultToolkit().createImage(ip));
	}

	public static Image convert(BufferedImage im) {
		return Toolkit.getDefaultToolkit().createImage(im.getSource());
	}

	public static BufferedImage convert(Image im) {
		int type = BufferedImage.TYPE_INT_ARGB;
		BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null), type);
		Graphics bg = bi.getGraphics();
		bg.drawImage(im, 0, 0, null);
		bg.dispose();
		return bi;
	}

	public static Image[] convert(BufferedImage[] im) {
		Image[] imgs = new Image[im.length];
		for (int x = 0; x < im.length; x++) {
			imgs[x] = Toolkit.getDefaultToolkit().createImage(im[x].getSource());
		}
		return imgs;
	}
}
