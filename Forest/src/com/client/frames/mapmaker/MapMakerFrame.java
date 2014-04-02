package com.client.frames.mapmaker;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.common.image.ImageLoader;
import com.common.map.Map;

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
public class MapMakerFrame extends JFrame implements ActionListener {
	public static MapMakerFrame INSTANCE;
	private JMenuBar jMenuBar1;
	private JPanel panelTiles;
	public JRadioButtonMenuItem jRadioButtonMenuItem3;
	public JRadioButtonMenuItem jRadioButtonMenuItem2;
	public JRadioButtonMenuItem jRadioButtonMenuItem1;
	private ButtonGroup buttonGroup1;
	private JMenu jMenu2;
	private JMenuItem jMenuItem2;
	private JScrollPane jScrollPane;
	public JLabel lblInfo;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem3;
	public Panel canvas;
	private JTabbedPane jTabbedPane1;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;

	public File currentMapFile;

	private MapMakerCanvas glCanvas;

	/**
	 * Auto-generated main method to display this JFrame
	 */

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
				MapMakerFrame inst = new MapMakerFrame();
				INSTANCE = inst;
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	public LwjglAWTCanvas newCanvas;

	public MapMakerFrame() {
		super();
		newCanvas = new LwjglAWTCanvas(new MapMakerCanvas(), true);
		// currentMap = new Map("Default", 45, 45);
		initGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setSize(50 * 32, 50 * 32);
		newCanvas.getCanvas().setSize(canvas.getSize());
		canvas.add(newCanvas.getCanvas(), BorderLayout.CENTER);
	}

	public String capitalizeFirstLetter(String original) {
		if (original.length() == 0)
			return original;
		return original.substring(0, 1).toUpperCase() + original.substring(1);
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
					// panelTiles.setPreferredSize(new java.awt.Dimension(230,
					// 522));
					panelTiles.setFocusable(false);
					panelTiles.setLayout(new GridLayout(0, 6));
					JScrollPane pane = new JScrollPane();
					pane.setPreferredSize(panelTiles.getPreferredSize());
					pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

					for (String className : getClasses("com.common.map.tiles")) {
						if (className.equals("Tile")) {
							continue;
						}
						// Tile t = Tile.getTile(className);
						final Image img = ImageLoader
								.loadImage("C:\\ForestRP\\Forest\\images\\tiles\\"
										+ className.toLowerCase() + ".png");
						ImageIcon imageIcon = new ImageIcon(className);
						JButton button = new JButton(imageIcon) {
							@Override
							public void paint(Graphics g) {
								g.drawImage(img, 0, 0, null);
							}
						};
						button.setToolTipText(className);
						panelTiles.add(button);
						button.addActionListener(this);
					}
					pane.setViewportView(panelTiles);
					jTabbedPane1.addTab("Tiles", null, pane, null);
				}
			}
			{
				jScrollPane = new JScrollPane();
				getContentPane().add(jScrollPane, BorderLayout.CENTER);
				getContentPane().add(getLblInfo(), BorderLayout.SOUTH);
				jScrollPane
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jScrollPane
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jScrollPane.setIgnoreRepaint(true);
				jScrollPane.setViewportView(getCanvas());
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
						jMenu1.add(getJMenuItem3());
						jMenu1.add(jMenuItem2);
						jMenuItem2.setText("Open");
						jMenuItem2.setAccelerator(KeyStroke
								.getKeyStroke("ctrl pressed O"));
						jMenuItem2.addActionListener(this);

					}
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(getJMenuItem4());
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Save As");
						jMenuItem1.setAccelerator(KeyStroke
								.getKeyStroke("shift ctrl pressed S"));
						jMenuItem1.addActionListener(this);

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

	private Panel getCanvas() {
		if (canvas == null) {
			canvas = new Panel();
		}
		return canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			MapMakerCanvas.selectedTile = btn.getToolTipText();
		} else if (e.getSource() instanceof JMenuItem) {
			String cmd = e.getActionCommand();
			System.out.println(cmd);
			if (cmd.equals("Save As") || cmd.equals("Save") && currentMapFile == null) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new MapFileFilter());
				int returnVal = fc.showSaveDialog(this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String name = f.getName().replaceAll(".map", "");
					if (!f.getName().endsWith(".map")) {
						f = new File(f.getPath() + ".map");
					}
					currentMapFile = f;
					MapMakerCanvas.currentMap.name = name;
					// currentMap.saveMap(f);
					MapMakerCanvas.currentMap.saveMapNew(f);
					System.out.println(MapMakerCanvas.currentMap.mapToText());
				} else
					return;
			} else if (cmd.equals("Save")) {
				MapMakerCanvas.currentMap.saveMap(currentMapFile);
				System.out.println(currentMapFile.getName() + " saved.");
			} else if (cmd.equals("Open")) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new MapFileFilter());
				int returnVal = fc.showOpenDialog(this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File f = fc.getSelectedFile();
						System.out.println(f.getPath());
						Map map = Map.loadMapNew(f);
						MapMakerCanvas.currentMap = map;
						currentMapFile = f;
						repaint();
					} catch (Exception err) {
						err.printStackTrace();
						JOptionPane.showConfirmDialog(null, "Failed to load "
								+ fc.getSelectedFile().getName());
					}
				} else
					return;
			} else if (cmd.equals("New")) {
				int width = Integer.parseInt(JOptionPane.showInputDialog("Map width: "));
				int height = Integer
						.parseInt(JOptionPane.showInputDialog("Map height: "));
				currentMapFile = null;
				MapMakerCanvas.currentMap = new Map(null, width, height);
				repaint();
			}
		}
	}

	public int mouseEndX = -1;
	public int mouseEndY = -1;

	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("New");
			jMenuItem3.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed N"));
			jMenuItem3.addActionListener(this);
		}
		return jMenuItem3;
	}

	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();
			jMenuItem4.setText("Save");
			jMenuItem4.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed S"));
			jMenuItem4.addActionListener(this);
		}
		return jMenuItem4;
	}

	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel();
			lblInfo.setText("Map Maker Info Label");
		}
		return lblInfo;
	}

}

class MapFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory())
			return false;

		String s = f.getName();
		return s.endsWith(".map");
	}

	@Override
	public String getDescription() {
		return "*.map";
	}
}