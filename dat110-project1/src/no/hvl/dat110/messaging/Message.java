package no.hvl.dat110.messaging;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload;
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload;
	}

	public byte[] encapsulate() {

		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];

		Integer payloadLength = payload.length;

		encoded[0] = payloadLength.byteValue();

		for (int i = 0; i < payload.length; i++) {
			encoded[i + 1] = payload[i];
		}

		return encoded;
	}

	public void decapsulate(byte[] received) {

		this.payload = new byte[received[0]];

		for (int i = 0; i < received[0]; i++) {
			payload[i] = received[i + 1];
		}
	}
}
