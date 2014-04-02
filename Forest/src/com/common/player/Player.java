package com.common.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.client.Forest;
import com.common.item.Item;
import com.common.map.Map;
import com.common.movable.Movable;

public abstract class Player extends Movable implements Serializable {

	public enum RACE {
		Badger, Beaver, Ferret, Fox, Hedgehog, Mouse, Rabbit, Rat, Shrew, Skunk, Squirrel, Weasel
	}

	public static int DIR_SOUTH = 0;
	public static int DIR_NORTH = 1;
	public static int DIR_EAST = 2;
	public static int DIR_WEST = 3;
	public static int DIR_NORTHEAST = 4;
	public static int DIR_NORTHWEST = 5;
	public static int DIR_SOUTHEAST = 6;
	public static int DIR_SOUTHWEST = 7;

	public int direction;

	public RACE race;
	public String name;

	private static final long serialVersionUID = 1L;

	private HashMap<String, Integer> skills = new HashMap<String, Integer>();

	public ArrayList<Item> inventory = new ArrayList<Item>();
	public Item leftHand;
	public Item rightHand;
	public Item neck;
	public Item ring;

	public float walkSpeed = 1.6f;

	public transient boolean east = false, west = false, south = false, north = false;

	public int Health = 10, MaxHealth = 10, Stamina = 8, MaxStamina = 8, Mind = 15,
			MaxMind = 15, Strength, Nimbleness, Prowess, Craft, Luck, Awareness,
			Fortitude, Magic, Intelligence, Hunger, Thirst, SkillPoints = 30,
			StatGain = 1000, StatMax = 1000;

	public int getSkillRaw(String skillName) {
		if (skills.containsKey(skillName))
			return skills.get(skillName);
		else
			return 0;
	}

	public void setSkill(String skillName, int skillLevel) {
		skills.put(skillName, skillLevel);
	}

	public void renderName(Graphics2D g, Movable centerLocation) {
		if (sprite == null) {
			setIcon(icon);
			return;
		}
		int drawX = 336 + ((int) x) - (int) centerLocation.x;
		int drawY = 336 + ((int) y) - (int) centerLocation.y;
		drawX += offsetX;
		drawY += offsetY;
		if (Forest.player == this) {
			g.setColor(Color.yellow);
		} else {
			g.setColor(Color.white);
		}
		int textWidth = (int) (g.getFont().getStringBounds(getFullName(),
				g.getFontRenderContext())).getWidth() - 32;
		g.drawString(getFullName(), drawX - textWidth / 2, drawY - 16);
	}

	public void setWalkSpeed(float value) {
		walkSpeed = value;
		if (Movable.comm != null) {
			Movable.comm.sendPacket("SetFloat", identity + ",walkSpeed=" + value);
		} else {
			System.out.println("Movable.comm shouldn't be null at this point...");
		}
	}

	public String getFullName() {
		return name + " the " + race.name();
	}

	@Override
	public void update() {
	}

	public int pixel_step = 0;

	public boolean canMove() {
		int xd = Math.abs((int) x - actualX);
		int yd = Math.abs((int) y - actualY);
		if (xd >= 1 || yd >= 1)
			return false;
		return true;
	}

	public transient Movable lastBump;

	public void update(Map currentMap, boolean server) {
		boolean xPos = (actualX >= x);
		boolean yPos = (actualY >= y);
		if (x != actualX || y != actualY) {

			if (actualX > (int) x) {
				x += walkSpeed;
			} else if (actualX < (int) x) {
				x -= walkSpeed;
			}
			if (actualY > (int) y) {
				y += walkSpeed;
			} else if (actualY < (int) y) {
				y -= walkSpeed;
			}

			if ((actualX >= x) != xPos) {
				x = actualX;
			}
			if ((actualY >= y) != yPos) {
				y = actualY;
			}
		}

		if (Forest.player == this && Forest.player.canMove()) {

			west = Gdx.input.isKeyPressed(Input.Keys.LEFT)
					&& currentMap.blockedMovement(this, -32, 0) == null;
			east = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
					&& currentMap.blockedMovement(this, 32, 0) == null;
			north = Gdx.input.isKeyPressed(Input.Keys.UP)
					&& currentMap.blockedMovement(this, 0, 32) == null;
			south = Gdx.input.isKeyPressed(Input.Keys.DOWN)
					&& currentMap.blockedMovement(this, 0, -32) == null;

			if (north && east) {
				direction = DIR_NORTHEAST;
			} else if (north && west) {
				direction = DIR_NORTHWEST;
			} else if (south && west) {
				direction = DIR_SOUTHWEST;
			} else if (south && east) {
				direction = DIR_SOUTHEAST;
			} else if (north) {
				direction = DIR_NORTH;
			} else if (east) {
				direction = DIR_EAST;
			} else if (west) {
				direction = DIR_WEST;
			} else if (south) {
				direction = DIR_SOUTH;
			}

			if (north || east || south || west) {
				move(direction);
				System.out.println("Sending move packet");
				// Forest.server.sendPacket("Move", "" + direction);
			}

		}
	}

	public void move(int dir) {
		direction = dir;
		if (dir == DIR_EAST) {
			actualX += 32;
		}
		if (dir == DIR_WEST) {
			actualX -= 32;
		}
		if (dir == DIR_NORTH) {
			actualY += 32;
		}
		if (dir == DIR_SOUTH) {
			actualY -= 32;
		}
		if (dir == DIR_NORTHWEST) {
			actualX -= 32;
			actualY += 32;
		}
		if (dir == DIR_NORTHEAST) {
			actualX += 32;
			actualY += 32;
		}
		if (dir == DIR_SOUTHEAST) {
			actualX += 32;
			actualY -= 32;
		}
		if (dir == DIR_SOUTHWEST) {
			actualX -= 32;
			actualY -= 32;
		}
	}

	public void savePlayer() {
		File f = new File("players");
		if (!f.exists()) {
			f.mkdir();
		}
		try {
			File file = new File("players/" + identity + ".p");
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			x = actualX;
			y = actualY;
			oos.writeObject(this);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Player loadPlayer(String identity) {
		try {
			File file = new File("players/" + identity + ".p");
			if (!file.exists())
				return null;
			ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file));
			Player player = (Player) oos.readObject();
			oos.close();
			return player;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isConscious() {
		return Health >= 0;
	}

	@Override
	public Rectangle getBox() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}
