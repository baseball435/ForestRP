package com.server.panel;

/**
 * @author Buzzyboy
 * @date 03/20/2014
 */

import java.awt.GridLayout;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class ConsolePanel extends JPanel {

	public JTextArea console = new JTextArea();

	public ConsolePanel() {
		setLayout(new GridLayout(1, 1));
		setBackground(java.awt.Color.black);

		PrintStream out = new PrintStream(new ETextArea(console));
		System.setOut(out);
		System.setErr(out);

		JScrollPane jScrollPane = new JScrollPane(console);
		jScrollPane.setBounds(20, 30, 300, 200);
		console.setForeground(java.awt.Color.white);
		console.setBackground(java.awt.Color.black);
		jScrollPane.setAutoscrolls(true);
		add(jScrollPane);
	}
}