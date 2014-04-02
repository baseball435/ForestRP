package com.server;

/**
 * @author Buzzyboy
 * @date 03/20/2014
 * 
 * ClientConnection is the packet communicator on the server
 * Extend this class for your custom server
 * 
 */

import java.net.Socket;

import com.common.net.PacketCommunicator;

public abstract class ConnectionToClient extends PacketCommunicator {

	protected FServer server; // Reference to the main server object

	public ConnectionToClient(Socket socket, FServer server) {
		super(socket, true);

		this.server = server;
		FServer.log("Client Connection created.");
	}

	public void broadcastPacket(Object packet) {
		broadcastPacket(packet, true);
	}

	public void broadcastPacket(Object packet, boolean includeSelf) {
		for (ConnectionToClient client : server.clients) {
			if (!includeSelf && client == this || !client.isConnected())
				continue;
			client.sendPacket(packet);
		}
	}

	@Override
	public void disconnect() {
		server.clientDisconnected(this);
	}

}
