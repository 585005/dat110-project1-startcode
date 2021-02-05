package no.hvl.dat110.system.controller;

import java.io.IOException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) throws IOException {

		
	byte[] request = RPCUtils.marshallVoid(RPCID);
		
	byte[] response = rpcclient.call(request);
		
		RPCUtils.unmarshallVoid(response);
	
		// TODO
		// implement marshalling, call and unmarshalling for write RPC method
	}
}
