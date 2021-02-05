package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;

	public RPCClient(String server, int port) {

		msgclient = new MessagingClient(server, port);
	}

	public void register(RPCStub remote) {
		remote.register(this);
	}

	public void connect() {

		if (connection == null) {
			connection = msgclient.connect();
		}
	}

	public void disconnect() {

		this.connection.close();

	}

	public byte[] call(byte[] rpcrequest) {

		byte[] rpcreply;
		Message message = new Message(rpcrequest);

		connection.send(message);

		Message message2 = connection.receive();

		rpcreply = message2.getData();

		return rpcreply;

	}

}
