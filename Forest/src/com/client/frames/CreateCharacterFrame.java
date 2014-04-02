package com.client.frames;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
public class CreateCharacterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	Color color1 = Color.RED;
	Color color2 = Color.GREEN;
	GradientPaint gp;

	public CreateCharacterFrame() {
		super();
		initGUI();
		color1 = getContentPane().getBackground();
		color2 = color1.darker().darker();
		gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
		setVisible(true);
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Create Character");
			getContentPane().setBackground(new java.awt.Color(0, 128, 0));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	public static void main(String[] args) {
		new CreateCharacterFrame();
	}

}
