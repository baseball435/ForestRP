package com.client.net;

import java.io.IOException;
import java.net.Socket;

import com.common.movable.Movable;
import com.common.net.PacketCommunicator;

public abstract class ConnectionToServer extends PacketCommunicator {

	public ConnectionToServer(String server, int port) throws IOException {
		super(new Socket(server, port), false);
	}

	public ConnectionToServer(Socket socket) {
		super(socket, false);
	}

	public abstract Movable findMovableByIdentity(String id);

	@Override
	public void packetReceived(String cmd, String data) {
	}

}
