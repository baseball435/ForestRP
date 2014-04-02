package com.client.frames.mapmaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.FEngine;
import com.common.image.ImageLoader;
import com.common.map.Map;
import com.common.map.tiles.Tile;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
public class MapMaker extends JFrame implements ActionListener, MouseListener,
		MouseMotionListener {
	private JMenuBar jMenuBar1;
	private JPanel panelTiles;
	private JRadioButtonMenuItem jRadioButtonMenuItem3;
	private JRadioButtonMenuItem jRadioButtonMenuItem2;
	private JRadioButtonMenuItem jRadioButtonMenuItem1;
	private ButtonGroup buttonGroup1;
	private JMenu jMenu2;
	private JMenuItem jMenuItem2;
	private JScrollPane jScrollPane;
	private JPanel canvas;
	private JTabbedPane jTabbedPane1;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;

	private HashMap<String, Image> images = new HashMap<String, Image>();

	private Map currentMap;

	private String selectedTile;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public Image getImage(String icon) {
		if (!images.containsKey(icon)) {
			BufferedImage[] imgs = ImageLoader.divideImage(
					ImageLoader.loadBufferedImage("images" + icon), 32, 32);
			images.put(icon, (Image) imgs[0]);
		}
		return images.get(icon);
	}

	public static String[] getClasses(String packageName) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL packageURL;
			ArrayList<String> names = new ArrayList<String>();
			;

			packageName = packageName.replace(".", "/");
			packageURL = classLoader.getResource(packageName);
			if (packageURL == null) {
				packageURL = ClassLoader.getSystemResource(packageName);
			}

			if (packageURL != null && packageURL.getProtocol().equals("jar")) {
				String jarFileName;
				JarFile jf;
				Enumeration<JarEntry> jarEntries;
				String entryName;

				// build jar file name, then loop through zipped entries
				jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
				jarFileName = jarFileName.substring(5, jarFileName.indexOf("!"));
				// System.out.println(">"+jarFileName);
				jf = new JarFile(jarFileName);
				jarEntries = jf.entries();
				while (jarEntries.hasMoreElements()) {
					entryName = jarEntries.nextElement().getName();
					if (entryName.startsWith(packageName)
							&& entryName.length() > packageName.length() + 5) {
						if (!entryName.contains("$")) {
							entryName = entryName.substring(packageName.length(),
									entryName.lastIndexOf('.'));
							entryName = entryName.replaceAll("/", "");
							names.add(entryName);
						}
					}
				}
				jf.close();

				// loop through files in classpath
			} else {
				File folder = new File(packageURL.getFile());
				if (!folder.exists()) {
					System.out.println("Didn't find this one either?");
					return null;
				}
				File[] contenuti = folder.listFiles();
				String entryName;
				for (File actual : contenuti) {
					entryName = actual.getName();
					if (!entryName.contains("$")) {
						entryName = entryName.substring(0, entryName.lastIndexOf('.'));
						names.add(entryName);
					}
				}
			}
			String[] classesA = new String[names.size()];
			names.toArray(classesA);
			return classesA;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MapMaker inst = new MapMaker();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	public MapMaker() {
		super();
		FEngine.openGL = false;
		currentMap = new Map("Default", 45, 45);
		initGUI();
		canvas.setSize(currentMap.width * 32, currentMap.height * 32);
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Map Maker");
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1, BorderLayout.WEST);
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(250, 548));
				{
					panelTiles = new JPanel();
					JScrollPane pane = new JScrollPane(panelTiles);
					pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

					jTabbedPane1.addTab("Tiles", null, pane, null);
					panelTiles.setFocusable(false);
					panelTiles.setLayout(new GridLayout(0, 4));

					for (String className : getClasses("com.common.map.tiles")) {
						if (className.equals("Tile")) {
							continue;
						}
						Tile t = Tile.getTile(className);
						BufferedImage[] imgs = ImageLoader.divideImage(
								ImageLoader.loadBufferedImage("images" + t.icon), 32, 32);
						ImageIcon imageIcon = new ImageIcon(imgs[0]);
						JButton button = new JButton(imageIcon);
						button.setPreferredSize(new Dimension(40, 40));
						button.setMinimumSize(button.getPreferredSize());
						button.setMaximumSize(button.getMinimumSize());
						button.setToolTipText(className);
						panelTiles.add(button);
						button.addActionListener(this);
					}
				}
			}
			{
				jScrollPane = new JScrollPane(getCanvas());
				getContentPane().add(jScrollPane, BorderLayout.CENTER);
				// getContentPane().add(getCanvas());
				jScrollPane
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jScrollPane
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jScrollPane.setIgnoreRepaint(true);
			}
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("File");
					{
						jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenuItem2.setText("Open");
						jMenuItem2.setAccelerator(KeyStroke
								.getKeyStroke("ctrl pressed O"));
					}
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Save As");
						jMenuItem1.setActionCommand("saveMap");
						jMenuItem1.setAccelerator(KeyStroke
								.getKeyStroke("shift ctrl pressed S"));
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Editor");
					{
						jRadioButtonMenuItem1 = new JRadioButtonMenuItem();
						jMenu2.add(jRadioButtonMenuItem1);
						jMenu2.add(getJRadioButtonMenuItem2());
						jMenu2.add(getJRadioButtonMenuItem3());
						jRadioButtonMenuItem1.setText("Single");
						jRadioButtonMenuItem1.setAccelerator(KeyStroke
								.getKeyStroke("pressed S"));
						getButtonGroup1().add(jRadioButtonMenuItem1);
					}
				}
			}
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private ButtonGroup getButtonGroup1() {
		if (buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItem2() {
		if (jRadioButtonMenuItem2 == null) {
			jRadioButtonMenuItem2 = new JRadioButtonMenuItem();
			jRadioButtonMenuItem2.setText("Rectangle");
			getButtonGroup1().add(jRadioButtonMenuItem2);
			jRadioButtonMenuItem2.setAccelerator(KeyStroke.getKeyStroke("pressed R"));
			jRadioButtonMenuItem2.setSelected(true);
			getButtonGroup1().add(jRadioButtonMenuItem2);
		}
		return jRadioButtonMenuItem2;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItem3() {
		if (jRadioButtonMenuItem3 == null) {
			jRadioButtonMenuItem3 = new JRadioButtonMenuItem();
			jRadioButtonMenuItem3.setText("Fill");
			jRadioButtonMenuItem3.setAccelerator(KeyStroke.getKeyStroke("pressed F"));
			getButtonGroup1().add(jRadioButtonMenuItem3);

		}
		return jRadioButtonMenuItem3;
	}

	private JPanel getCanvas() {
		if (canvas == null) {
			canvas = new JPanel() {
				@Override
				public void paint(Graphics g) {
					setPreferredSize(new Dimension(currentMap.width * 32,
							currentMap.height * 32));
					g.clearRect(0, 0, currentMap.width * 32, currentMap.height * 32);
					for (int x = 0; x < currentMap.width; x++) {
						for (int y = 0; y < currentMap.height; y++) {
							if (currentMap.tiles[x][y] != null) {
								g.drawImage(getImage(currentMap.tiles[x][y].icon),
										x * 32, y * 32, null);
							}
							g.setColor(Color.white);
							g.drawRect(x * 32, y * 32, 32, 32);
						}
					}
					// currentMap.render(g, true);
					if (mouseDown) {
						g.setColor(Color.red);
						if (mouseEndX == -1 || mouseEndY == -1) {
							g.drawRect(mouseX * 32, mouseY * 32, 32, 32);
						} else {
							int drawX = Math.min(mouseX * 32, mouseEndX * 32);
							int drawY = Math.min(mouseY * 32, mouseEndY * 32);
							int width = Math.abs(mouseX * 32 - mouseEndX * 32);
							int height = Math.abs(mouseY * 32 - mouseEndY * 32);
							width += 32;
							height += 32;
							g.drawRect(drawX, drawY, width, height);
						}
					}
				}
			};
			canvas.addMouseMotionListener(this);
			canvas.addMouseListener(this);
		}
		return canvas;
	}

	private int mouseX, mouseY;

	private boolean mouseDown = false;

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseDown = true;

		if (selectedTile != null) {
			if (jRadioButtonMenuItem1.isSelected()) {
				currentMap.tiles[mouseX][mouseY] = Tile.getTile(selectedTile);
				currentMap.tiles[mouseX][mouseY].x = mouseX;
				currentMap.tiles[mouseX][mouseY].y = mouseY;
			}
		}

		canvas.repaint();
	}

	public void fill(int startX, int startY) {
		fillTouchingTiles(currentMap.tiles[startX][startY]);
	}

	public void fillTouchingTiles(Tile t) {
		Tile oldTile = currentMap.tiles[(int) t.x][(int) t.y];
		currentMap.tiles[(int) t.x][(int) t.y] = Tile.getTile(selectedTile);
		currentMap.tiles[(int) t.x][(int) t.y].x = t.x;
		currentMap.tiles[(int) t.x][(int) t.y].y = t.y;
		for (int x = (int) t.x - 1; x <= t.x + 1; x++) {
			for (int y = (int) t.y - 1; y <= t.y + 1; y++) {
				Tile touchingTile = currentMap.getTile(x, y);
				if (touchingTile != null && touchingTile.icon.equals(oldTile.icon)) {
					fillTouchingTiles(touchingTile);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (selectedTile != null) {
			if (jRadioButtonMenuItem2.isSelected()) {
				int startX = Math.min(mouseX, mouseEndX);
				int startY = Math.min(mouseY, mouseEndY);
				int width = Math.abs(mouseX - mouseEndX) + 1;
				int height = Math.abs(mouseY - mouseEndY) + 1;
				for (int x = startX; x < startX + width; x++) {
					for (int y = startY; y < startY + height; y++) {
						currentMap.tiles[x][y] = Tile.getTile(selectedTile);
						currentMap.tiles[x][y].x = x;
						currentMap.tiles[x][y].y = y;
					}
				}
			} else if (jRadioButtonMenuItem3.isSelected()) {
				fill(mouseX, mouseY);
			}
		}

		mouseDown = false;
		mouseEndX = -1;
		mouseEndY = -1;
		canvas.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			selectedTile = btn.getToolTipText();
		}
	}

	public int mouseEndX = -1;
	public int mouseEndY = -1;

	@Override
	public void mouseDragged(MouseEvent e) {
		int newX = e.getX() / 32;
		int newY = e.getY() / 32;
		if (jRadioButtonMenuItem2.isSelected()) {
			if (mouseEndX != newX || mouseEndY != newY) {
				mouseEndX = e.getX() / 32;
				mouseEndY = e.getY() / 32;
				mouseEndX = Math.min(mouseEndX, currentMap.width - 1);
				mouseEndY = Math.min(mouseEndY, currentMap.height - 1);
				repaint();
			}
		} else {
			mouseMoved(e);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int newX = e.getX() / 32;
		int newY = e.getY() / 32;
		if (mouseX != newX || mouseY != newY) {
			mouseX = e.getX() / 32;
			mouseY = e.getY() / 32;
			if (mouseDown) {

				if (selectedTile != null) {
					if (jRadioButtonMenuItem1.isSelected()) {
						currentMap.tiles[newX][newY] = Tile.getTile(selectedTile);
						currentMap.tiles[newX][newY].x = mouseX;
						currentMap.tiles[newX][newY].y = mouseY;
					}
				}

				repaint();
			}
		}

	}

}
