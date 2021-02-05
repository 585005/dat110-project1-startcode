package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConfig;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded = new byte[str.getBytes().length + 1];
		
		byte[] strEncoded = new byte[str.getBytes().length]; 
		
		//hvorfor må vi legge str i en byte tabell ? 
		//og ikke direkte i for-løkken skrive 
		//encoded[i+1] = (byte) str.indexOf(i);
		
		strEncoded = str.getBytes();
		
		encoded[0] = rpcid; 
		
		for(int i = 0; i < str.length(); i++) {
			encoded[i+1] = strEncoded[i];
		}
		
		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded = new String(Arrays.copyOfRange(data, 1, data.length));

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		return new byte[]{rpcid};
		
	}

	public static void unmarshallVoid(byte[] data) {
		
		return;
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		
		byte[] encoded = new byte[5];
		byte[] buffer = ByteBuffer.allocate(4).putInt(x).array();
		 
		encoded[0] = rpcid;
		
		for(int i = 1; i < encoded.length; i++) {
			encoded[i] = buffer[i-1];
		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {
		
		int decoded;
		
		return decoded = ByteBuffer.wrap(data, 1, 4).getInt();

		
	}
}
