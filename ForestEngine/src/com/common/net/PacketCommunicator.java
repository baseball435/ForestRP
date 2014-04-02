package com.common.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Random;

public abstract class PacketCommunicator implements Runnable {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;
	private boolean connected;
	protected boolean encryptionEnabled = false;
	protected String key = "default";

	public HashMap<String, Object> data;

	protected int sentPackets = 0;
	protected int receivedPackets = 0;

	public String getIP() {
		return socket.getInetAddress().toString();
	}

	public abstract void disconnect();

	/**
	 * This is used for the LPacket system which is not recommended for
	 * sensitive data since LPacket's can be decompiled and the functions for
	 * both server and client are visible.
	 * 
	 * @param packet
	 */
	public abstract void packetReceived(Object packet);

	/**
	 * This is good for sensitive data
	 * 
	 * @param packetID
	 *            ID of packet
	 * @param cmd
	 *            Labeling command
	 * @param data
	 */
	public abstract void packetReceived(String cmd, String data);

	public void toggleEncryption() {
		encryptionEnabled = !encryptionEnabled;
	}

	public boolean isConnected() {
		return connected;
	}

	/**
	 * Sends the LPacket to this client
	 * 
	 * @param packet
	 */
	public void sendPacket(Object packet) {
		try {
			sentPackets++;
			out.writeObject(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendPacket(String cmd, String data) {
		try {

			sentPackets++;

			String s = cmd + ":" + sentPackets + "::" + data;
			if (encryptionEnabled) {
				out.writeObject(encrypt(key, s));
			} else {
				if (out != null) {
					out.writeObject(s);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Constructor class for game client
	public PacketCommunicator(String server, int port) throws IOException {
		this(new Socket(server, port), false);
	}

	/**
	 * 
	 * @param socket
	 * @param onServer
	 *            The Packet Communicator on the server MUST create the input
	 *            stream first This constructor is used for the server
	 */
	public PacketCommunicator(Socket socket, boolean onServer) {
		this.socket = socket;
		data = new HashMap<String, Object>();

		try {

			if (onServer) {
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
			} else {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			}

			connected = true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		Thread t = new Thread(this);
		t.start();

	}

	/*
	 * @param Manually closes the connection
	 */
	protected void closeConnection() {
		connected = false;
		try {
			out.flush();
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (this.isConnected()) {
			try {
				Object packet = in.readObject();
				receivedPackets++;
				if (packet instanceof String) {
					String packetString = packet.toString();
					if (encryptionEnabled) {
						packetString = decrypt(key, packetString);
					}

					String cmd = packetString
							.substring(0, packetString.indexOf(":"));

					int packetID = Integer.parseInt(packetString.substring(
							packetString.indexOf(":") + 1,
							packetString.indexOf("::")));

					String data = packetString
							.substring(packetString.indexOf("::") + 2);

					// FServer.log(packetString);
					if (packetID == receivedPackets) {

					} else {
					}

					packetReceived(cmd, data);
				} else {
					this.packetReceived(packet);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SocketException e) {
				// Closed client
				connected = false;
			} catch (IOException e) {
				connected = false;
				e.printStackTrace();
			}
		}
		disconnect();
	}

	public static String encrypt(String key, String text) {
		long finalKey = 0;
		for (int i = 0; i < key.length(); i++) {
			long tempKey = key.charAt(i);
			tempKey *= 128;
			finalKey += tempKey;
		}
		Random generator = new Random(finalKey);
		String returnString = "";
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '	') {
				returnString += text.charAt(i);
			} else {
				int temp = text.charAt(i);
				temp += generator.nextInt(95);
				if (temp > 126) {
					temp -= 95;
				}
				returnString += (char) temp;
			}
		}
		return returnString;
	}

	public static String decrypt(String key, String text) {
		long finalKey = 0;
		for (int i = 0; i < key.length(); i++) {
			long tempKey = key.charAt(i);
			tempKey *= 128;
			finalKey += tempKey;
		}
		Random generator = new Random(finalKey);
		String returnString = "";
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '	') {
				returnString += text.charAt(i);
			} else {
				int temp = text.charAt(i);
				temp -= generator.nextInt(95);
				if (temp < 36) {
					temp += 95;
				}
				if (temp > 126) {
					temp -= 95;
				}
				returnString += (char) temp;
			}
		}
		return returnString;
	}

}
