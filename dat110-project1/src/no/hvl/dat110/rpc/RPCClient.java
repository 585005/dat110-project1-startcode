package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() throws UnknownHostException, IOException {
		
		if(connection == null) {
			connection = msgclient.connect();
		}
	}
	
	public void disconnect() {
		
		this.connection.close();
		
	}
	
	public byte[] call(byte[] rpcrequest) throws IOException {
		
		byte[] rpcreply;
		Message message = new Message(rpcrequest);
		
		
		//spørsmål i lab, hvordan fungerer dette? 
		
		connection.send(message);
		
		message = connection.receive();
		
		rpcreply = message.getData();
		
		return rpcreply;
		
	}

}
