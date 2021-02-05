package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;

public class DisplayDevice {

	public static void main(String[] args) {

		System.out.println("Display server starting ...");

		DisplayImpl display = new DisplayImpl();

		RPCServer client = new RPCServer(Common.DISPLAYPORT);

		client.register(1, display);

		client.run();

		client.stop();

		System.out.println("Display server stopping ...");

	}
}
