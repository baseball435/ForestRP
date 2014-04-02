package com.server.panel;

import javax.swing.JTabbedPane;


/**
 * @author Buzzyboy
 * @date 03/20/2014
 * 
 *       A JTabbedPane that contains a console
 * 
 */

@SuppressWarnings("serial")
public class ServerTabbedPane extends JTabbedPane {

	private ConsolePanel consolePanel;

	public ServerTabbedPane() {
		this.consolePanel = new ConsolePanel();
		this.addTab("Console", consolePanel);
	}
}
