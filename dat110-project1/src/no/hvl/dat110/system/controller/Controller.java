package no.hvl.dat110.system.controller;

import java.io.IOException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) throws IOException {
		
		Display display;
		Sensor sensor;
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		displayclient = new RPCClient(Common.DISPLAYHOST,N);
		sensorclient = new RPCClient(Common.SENSORHOST,N);
		
		// TODO
		// connect to sensor and display RPC servers
		// create local display and sensor objects
		// register display and sensor objects in the RPC layer
		
		displayclient.connect();
		sensorclient.connect();
		
		display = new Display();
		sensor = new Sensor();
		
		display.register(displayclient);
		sensor.register(sensorclient);
		
		
		// register stop methods in the RPC layer
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		
		// TODO:
		// loop while reading from sensor and write to display via RPC
		
		
		//hva skal man stoppe loop'en med? 
		//skal vi parse read til en string, slik at vi kan putte dette inn i display sin write? 
		
		int read = sensor.read();
		display.write();
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
