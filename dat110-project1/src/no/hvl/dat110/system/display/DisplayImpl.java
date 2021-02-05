package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.system.sensor.SensorImpl;

public class DisplayImpl implements RPCImpl {

	static final int RANGE = 20;

	//? usikker om det er slik de vil vi skal gjøre dette 
	
	public int read() {

		long seconds = System.currentTimeMillis();

		double temp = RANGE * Math.sin(seconds / 1000);

		return (int) Math.ceil(temp);
	}
	
	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] request) {
		
		int temp = read();

		RPCUtils.unmarshallVoid(request); 
		
		byte rpcid = request[0];
		
		byte[] reply = RPCUtils.marshallInteger(rpcid, temp);
		
		// TODO: 
		// implement unmarshalling, call, and marshall for write RPC method
		// look at how this is done int he SensorImpl for the read method
		
		return reply;
	}
}
