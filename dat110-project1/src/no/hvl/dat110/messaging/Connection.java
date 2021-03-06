package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.Socket;

import jdk.net.Sockets;
import no.hvl.dat110.TODO;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		try {
			byte[] encoded = message.encapsulate();
			outStream.write(encoded);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Message receive() {

		Message message = new Message();
		byte[] recvbuf = new byte[MessageConfig.SEGMENTSIZE];

		int l = 0;

		try {
			l = inStream.read(recvbuf, 0, MessageConfig.SEGMENTSIZE);

			if (l != MessageConfig.SEGMENTSIZE) {
				throw new IOException("");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		message.decapsulate(recvbuf);

		return message;

	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}