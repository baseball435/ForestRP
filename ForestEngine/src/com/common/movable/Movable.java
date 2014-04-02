package com.common.movable;

import java.awt.Rectangle;
import java.io.Serializable;

import com.FEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.common.image.SpriteOGL;
import com.common.net.PacketCommunicator;
import com.common.net.SharedObject;
import com.common.util.ResourceHandler;

/**
 * 
 * @author Buzzyboy
 * 
 *         A movable is any object that is to be drawn on the map
 */

public abstract class Movable extends SharedObject implements Serializable {

	public transient static PacketCommunicator comm;

	private static final long serialVersionUID = 1L;

	public transient SpriteOGL sprite;

	public transient int currentFrame;

	public transient String helpInfo;
	public transient int offsetX, offsetY;

	public float x, y;
	public transient int actualX, actualY;
	public boolean visible = true;
	public String icon;

	public void render(SpriteBatch batch) {
		if (sprite == null) {
			setIcon(icon);
			return;
		}
		if (visible) {
			sprite.render(batch, x * 32 + offsetX, y * 32 + offsetY, currentFrame);
		}
	}

	public void render(SpriteBatch batch, Movable centerLocation) {
		if (sprite == null) {
			setIcon(icon);
			return;
		}
		if (visible) {
			int drawX = FEngine.centerInt + ((int) x) - (int) centerLocation.x;
			int drawY = FEngine.centerInt + ((int) y) - (int) centerLocation.y;
			drawX += offsetX;
			drawY += offsetY;
			sprite.render(batch, drawX, drawY, currentFrame);
		}
	}

	public void render(SpriteBatch batch, int addX, int addY) {
		if (sprite == null) {
			setIcon(icon);
			return;
		}
		if (visible) {
			sprite.render(batch, offsetX + addX, offsetY + addY, currentFrame);
		}
	}

	public void setIcon(String value) {
		if (value == null)
			return;
		icon = value;
		sprite = ResourceHandler.getSprite(icon);
		currentFrame = 0;
		if (identity != null && Movable.comm != null) {
			// comm.sendPacket("SetString", identity + ",icon=" + value);
		}
	}

	public void setX(int value) {
		x = value;
		if (identity != null && Movable.comm != null) {
			comm.sendPacket("SetInt", identity + ",x=" + value);
		}
	}

	public void setY(int value) {
		y = value;
		if (identity != null && Movable.comm != null) {
			comm.sendPacket("SetInt", identity + ",y=" + value);
		}
	}

	int ticks = 0;

	public void update() {
		if (sprite.isAnimated()) {
			ticks++;
			if (ticks >= sprite.LoopsPerFrame) {
				ticks = 0;
				currentFrame++;
				if (currentFrame >= sprite.frameCount()) {
					currentFrame = 0;
				}
			}
		}
	}

	public int getTileX() {
		float tx = x + 16;
		tx /= 32;
		return Integer.parseInt(Float.toString(tx).substring(0,
				Float.toString(tx).indexOf(".")));
	}

	public int getTileY() {
		float tx = y + 32;
		tx /= 32;
		return Integer.parseInt(Float.toString(tx).substring(0,
				Float.toString(tx).indexOf(".")));
	}

	public void Enter(Movable M) {
	}

	public void Entered(Movable M) {
	}

	public void Exited(Movable M) {
	}

	public void Bump(Movable M) {
	}

	public Rectangle getBox() {
		return new Rectangle((int) x * 32, (int) y * 32, 32, 32);
	}

}
