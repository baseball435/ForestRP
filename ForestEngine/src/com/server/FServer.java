package com.server;

/**
 * @author Buzzyboy
 * @date 03/20/2014
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.server.panel.ServerTabbedPane;

@SuppressWarnings("serial")
public abstract class FServer extends JFrame {

	public static boolean DEBUG = true;

	protected ServerSocket serverSocket;

	protected ArrayList<ConnectionToClient> clients;

	protected boolean pendingCloseServer;

	public ServerTabbedPane pane;

	/**
	 * 
	 * @return Returns the full list of clients currently connected to the
	 *         server
	 */
	protected ArrayList<ConnectionToClient> getClients() {
		return clients;
	}

	/**
	 * 
	 * @param client
	 *            Don't forget to add the ClientConnection to this.clients
	 */

	public abstract void clientDisconnected(ConnectionToClient client);

	/**
	 * 
	 * @param client
	 *            Don't forget to remove the ClientConnection from this.clients
	 */

	public abstract void clientConnected(Socket client);

	/**
	 * Starts the server loop that waits for connections
	 * 
	 * @param port
	 *            What port to start the server on?
	 */
	protected void startServer(int port) {
		log("Starting FServer on port: " + port);
		clients = new ArrayList<ConnectionToClient>();

		try {
			serverSocket = new ServerSocket(port);
			pendingCloseServer = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		log("Awaiting connections.");
		while (!pendingCloseServer) {
			try {
				Socket socket = serverSocket.accept();
				clientConnected(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setUpGUI() {
		this.setTitle("Server");
		pane = new ServerTabbedPane();
		this.add(pane);

		JMenuBar menu = new JMenuBar();

		JMenu serverMenu = new JMenu("Server");

		JMenuItem closeServer = new JMenuItem("Close Server");
		// closeServer.addActionListener(this);
		serverMenu.add(closeServer);

		menu.add(serverMenu);

		this.setJMenuBar(menu);
		this.setSize(800, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void log(Object msg) {
		if (DEBUG) {
			System.out.println(msg);
		}
	}
}
