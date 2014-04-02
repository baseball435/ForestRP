package com.common.map;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.FEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.client.Forest;
import com.common.map.tiles.Tile;
import com.common.movable.Movable;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	public int width, height;
	public String name;
	public boolean isBurrow = false;

	public Tile[][] tiles;

	public ArrayList<Movable> ground = new ArrayList<Movable>();

	public transient ArrayList<Light> lights = new ArrayList<Light>();

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public transient Image[] darkness;

	public Map(String name, int w, int h) {
		this.name = name;
		width = w;
		height = h;

		tiles = new Tile[w][h];

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
			}
		}
	}

	public Tile getTile(int tileX, int tileY) {
		try {
			return tiles[tileX][tileY];
		} catch (Exception e) {// probably threw a out of bounds exception
			return null;
		}

	}

	public void setTile(Tile t, int tileX, int tileY) {
		tiles[tileX][tileY] = t;
		t.x = tileX;
		t.y = tileY;
	}

	public Movable blockedMovement(Movable M, int addX, int addY) {
		return blockedMovement(M, addX, addY, false);
	}

	public Movable blockedMovement(Movable M, int addX, int addY, boolean processbumps) {
		Tile onTile = tiles[(int) M.x / 32][(int) M.y / 32];
		M.x += addX;
		M.y += addY;
		if (M.x < 0 || M.y < 0 || M.x > width * FEngine.TILE_SIZE - 32
				|| M.y > height * FEngine.TILE_SIZE - 32) {
			M.x -= addX;
			M.y -= addY;
			return onTile;
		}
		Tile newOnTile = tiles[(int) M.x / 32][(int) M.y / 32];

		if (newOnTile != null && !newOnTile.canStepOn) {
			M.x -= addX;
			M.y -= addY;
			if (processbumps) {
				newOnTile.Bump(M);
			}
			return newOnTile;
		}

		if (onTile != newOnTile && newOnTile != null && processbumps) {
			onTile.Exited(M);
			newOnTile.Entered(M);
		}
		M.x -= addX;
		M.y -= addY;
		return null;
	}

	int drawX = 336, drawY = 336;// declare these globally so we don't have to
									// recreate variables

	public void render(SpriteBatch batch, boolean grid) {
		Tile t = null;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null) {
					t = tiles[x][y];
					t.render(batch);
					if (grid) {
						/*
						 * g.drawLine((int) t.x * 32, (int) t.y * 32, (int) t.x
						 * * 32 + 32, (int) t.y * 32); g.drawLine((int) t.x *
						 * 32, (int) t.y * 32, (int) t.x * 32, (int) t.y * 32 +
						 * 32);
						 */
					}
				}
			}
		}
	}

	public void render(SpriteBatch batch, Movable centerLocation, int renderDistance) {
		int dist;
		if (Forest.nightTime) {
			// renderDistance = 4;
		}
		for (int x = ((int) centerLocation.x / 32) - renderDistance; x < (centerLocation.x / 32)
				+ renderDistance + 1; x++) {
			for (int y = ((int) centerLocation.y / 32) - renderDistance; y < (centerLocation.y / 32)
					+ renderDistance + 1; y++) {
				if (x < 0 || y < 0 || x >= width || y >= height) {
					continue;
				}
				dist = Math.max(Math.abs(x - (int) centerLocation.x / 32),
						Math.abs(y - (int) centerLocation.y / 32));
				dist += 2;
				// dist = Math.min(dist, darkness.length - 1);

				if (tiles[x][y] != null) {
					drawX = FEngine.centerInt + (x * 32) - (int) centerLocation.x;
					drawY = FEngine.centerInt + (y * 32) - (int) centerLocation.y;
					tiles[x][y].render(batch, drawX, drawY);
					// g.drawLine(drawX, drawY, drawX + 32, drawY);
					// g.drawLine(drawX, drawY, drawX, drawY + 32);

					if (Forest.nightTime) {
						// g.drawImage(darkness[dist], drawX, drawY, 32, 32,
						// null);
					}

				}
			}
		}
		for (Movable M : ground) {
			M.render(batch, centerLocation);
		}
	}

	public void renderShadows(SpriteBatch batch, Movable centerLocation) {
		int tx = centerLocation.actualX / 32;
		int ty = centerLocation.actualY / 32;
		int renderDistance = 10;

		ShapeRenderer sr = Forest.INSTANCE.shapeRenderer;

		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		int realCenter = FEngine.centerInt + 16;

		// This ugly long chunk of code casts shadows
		for (int x = ((int) centerLocation.x / 32) - (renderDistance - 1); x < centerLocation.x
				+ (renderDistance - 1); x++) {
			for (int y = ((int) centerLocation.y / 32) - (renderDistance - 1); y < centerLocation.y
					+ (renderDistance - 1); y++) {
				if (x < 0 || y < 0 || x >= width || y >= height) {

					continue;
				}
				if (tiles[x][y] != null && tiles[x][y].blocksVision) {
					if (tx == x && ty == y) {
						continue;
					}
					drawX = FEngine.centerInt + (x * 32) - (int) centerLocation.x + 16;
					drawY = FEngine.centerInt + (y * 32) - (int) centerLocation.y + 16;
					if (ty == y) {
						if (x > tx) {// the tile is to the east
							Point p1 = new Point(drawX, drawY + 16);
							Point p2 = new Point(drawX, drawY - 16);
							float ratioy = (p1.y - realCenter) / (p1.x - realCenter);
							float ratiox = (p1.x - realCenter) / (p1.y - realCenter);
							sr.line(realCenter, realCenter, p1.x, p1.y);
							sr.line(realCenter, realCenter, p2.x, p2.y);
							sr.setColor(Color.BLUE);
							sr.line(p1.x, p1.y, p1.x * 2 * ratioy, p2.y * 2 * ratiox);
						} else {// tile is to the west
							sr.line(realCenter, realCenter, drawX, drawY + 16);
							sr.line(realCenter, realCenter, drawX, drawY - 16);
						}
					} else if (tx == x) {
						if (y > ty) {// the tile is to the north
							sr.line(realCenter, realCenter, drawX + 16, drawY);
							sr.line(realCenter, realCenter, drawX - 16, drawY);
						} else {
							sr.line(realCenter, realCenter, drawX + 16, drawY);
							sr.line(realCenter, realCenter, drawX - 16, drawY);
						}
					} else if (x > tx) {// the tile is to the east
						if (y > ty) {// tile is to the northeast

						} else {// tile is to the southeast
						}
					} else {// tile is to the west
						if (y < ty) {// tile is to the northwest
						} else {// tile is to the southwest
						}
					}
				}
			}
		}
		sr.end();
	}

	public void renderShadows(SpriteBatch batch, Movable centerLocation,
			int renderDistance) {
		int tx = centerLocation.actualX / 32;
		int ty = centerLocation.actualY / 32;

		int xd, yd, xyd;

		ShapeRenderer sr = Forest.INSTANCE.shapeRenderer;

		sr.begin(ShapeType.Filled);
		sr.setColor(Color.BLACK);

		// This ugly long chunk of code casts shadows
		for (int x = ((int) centerLocation.x / 32) - (renderDistance - 1); x < centerLocation.x
				+ (renderDistance - 1); x++) {
			for (int y = ((int) centerLocation.y / 32) - (renderDistance - 1); y < centerLocation.y
					+ (renderDistance - 1); y++) {
				if (x < 0 || y < 0 || x >= width || y >= height) {

					continue;
				}
				if (tiles[x][y] != null && tiles[x][y].blocksVision) {
					if (tx == x && ty == y) {
						continue;
					}
					xd = Math.abs(x - tx);
					yd = Math.abs(y - ty);
					xyd = Math.abs(xd - yd);
					drawX = FEngine.centerInt + (x * 32) - (int) centerLocation.x;
					drawY = FEngine.centerInt + (y * 32) - (int) centerLocation.y;
					if (ty == y) {
						if (x > tx) {// the tile is to the east
							for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
								sr.rect(drawX + 32 + (32 * xdist), drawY - 32
										- ((32 * xdist)), 32, 96 + (xdist * 64));
							}
						} else {// tile is to the west
							for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
								sr.rect(drawX - 32 - (32 * xdist), drawY - 32
										- ((32 * xdist)), 32, 96 + (xdist * 64));
							}
						}
					} else if (tx == x) {
						if (y > ty) {// the tile is to the north
							for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
								sr.rect(drawX - 32 - (32 * xdist), drawY + 32
										+ (32 * xdist), 96 + (xdist * 64), 32);
							}

						} else {
							for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
								sr.rect(drawX - 32 - (32 * xdist), drawY - 32
										- (32 * xdist), 96 + (xdist * 64), 32);
							}
						}
					} else if (x > tx) {// the tile is to the east
						if (y < ty) {// tile is to the northeast
							if (xd == yd) {
								for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
									sr.rect(drawX + 32 + (xdist * 32), drawY - 32
											- (xdist * 48), 32 + (xdist * 16),
											32 + (xdist * 16));
								}
							} else if (xd > yd) {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32 + (xdist * 32), drawY - 32
												- (xdist * 32), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32 + (xdist * 32), drawY - 32
												- (xdist * 16), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								}
							} else {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32 + (xdist * 16), drawY - 32
												- (xdist * 48), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32, drawY - 32 - (xdist * 48),
												32 + (xdist * 16), 32 + (xdist * 16));
									}
								}
							}
						} else {// tile is to the southeast
							if (xd == yd) {
								for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
									sr.rect(drawX + 32 + (xdist * 32), drawY + 32
											+ (xdist * 32), 32 + (xdist * 16),
											32 + (xdist * 16));
								}
							} else if (xd > yd) {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32 + (xdist * 32), drawY + 32
												+ (xdist * 16), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32 + (xdist * 32), drawY + 32,
												32 + (xdist * 16), 32 + (xdist * 16));
									}
								}
							} else {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32 + (xdist * 16), drawY + 32
												+ (xdist * 32), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX + 32, drawY + 32 + (xdist * 32),
												32 + (xdist * 16), 32 + (xdist * 16));
									}
								}
							}
						}
					} else {// tile is to the west
						if (y < ty) {// tile is to the northwest
							if (xd == yd) {
								for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
									sr.rect(drawX - 32 - (xdist * 48), drawY - 32
											- (xdist * 48), 32 + (xdist * 16),
											32 + (xdist * 16));
								}
							} else if (xd > yd) {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 48), drawY - 32
												- (xdist * 32), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 48), drawY - 32
												- (xdist * 16), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								}
							} else {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 32), drawY - 32
												- (xdist * 48), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 16), drawY - 32
												- (xdist * 48), 32 + (xdist * 16),
												32 + (xdist * 16));
									}

								}
							}
						} else {// tile is to the southwest
							if (xd == yd) {
								for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
									sr.rect(drawX - 32 - (xdist * 48), drawY + 32
											+ (xdist * 32), 32 + (xdist * 16),
											32 + (xdist * 16));
								}
							} else if (xd > yd) {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 48), drawY + 32
												+ (xdist * 16), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 48), drawY + 32,
												32 + (xdist * 16), 32 + (xdist * 16));
									}
								}
							} else {
								if (xyd <= 2) {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 32), drawY + 32
												+ (xdist * 32), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								} else {
									for (int xdist = 0; xdist < 10 - (x - tx); xdist++) {
										sr.rect(drawX - 32 - (xdist * 16), drawY + 32
												+ (xdist * 32), 32 + (xdist * 16),
												32 + (xdist * 16));
									}
								}
							}
						}
					}
				}
			}
		}
		sr.end();
	}

	public void update() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null) {
					tiles[x][y].update();
				}
			}
		}
	}

	public File getFile() {
		if (isBurrow)
			return new File("maps/burrows/" + name + ".map");
		else
			return new File("maps/" + name + ".map");
	}

	public String mapToText() {
		String result = "";
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null) {
					result += tiles[x][y].getClass().getSimpleName();
				}
				result += ";";
			}
		}
		return result;
	}

	public static Map textToMap(int width, int height, String text) {
		String[] splits = text.split(";");
		int count = 0;
		Map map = new Map(null, width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				String correspondingText = splits[count];
				try {
					map.tiles[x][y] = Tile.getTile(correspondingText);
					map.tiles[x][y].x = x;
					map.tiles[x][y].y = y;
				} catch (Exception e) {
				}
				count++;
			}
		}
		return map;
	}

	public static Map loadMapNew(File file) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			String name = ois.readUTF();
			int width = ois.readInt();
			int height = ois.readInt();
			String text = "" + ois.readObject();
			Map map = textToMap(width, height, text);
			map.name = name;
			ois.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveMapNew(File file) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeUTF(name);
			oos.writeInt(width);
			oos.writeInt(height);
			oos.writeObject(mapToText());
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveMap(File file) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(this);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveMap() {
		File f = new File("maps");
		if (!f.exists()) {
			f.mkdir();
		}
		if (isBurrow) {
			f = new File("maps/burrows");
			if (!f.exists()) {
				f.mkdir();
			}
		}
		saveMap(getFile());
	}

	public static Map loadMap(String name) {
		return loadMap(name, false);
	}

	public static Map loadMap(File file) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			Map map = (Map) ois.readObject();
			ois.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Map loadMap(String name, boolean isBurrow) {
		try {
			File file = null;
			if (isBurrow) {
				file = new File("maps/burrows/" + name + ".map");
			}
			return loadMap(file);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

class Light {
	public float x, y, power;
	public float depth;
	public float size;
	Color color;

	public Light(int tileX, int tileY, int P) {
		x = tileX;
		y = tileY;
		power = P;
	}
}

class ConvexHull {
	List<Vector2> points;
	public float depth, centerX, centerY;
}

class TeleportTile extends Tile {

	private static final long serialVersionUID = 1L;

	private float telX, telY;

	public TeleportTile(float tx, float ty) {
		canStepOn = false;
		telX = tx;
		telY = ty;
		setIcon("/tiles/beacon.png");
	}

	@Override
	public void Bump(Movable M) {
		M.x = telX;
		M.y = telY;
		M.actualX = (int) telX;
		M.actualY = (int) telY;
	}

}