package com.ecommerce.shopping;

import java.nio.charset.StandardCharsets;

public class ShoppingClient {

	public static void main(String args[]) {

		ClientUtility client = new ClientUtility();
		client.startConnection("127.0.0.1", 7474);
		int action = 3; // for retrival of orders
		String data = "get orders";
		byte[] dataInBytes = data.getBytes(StandardCharsets.UTF_8);

		String response = client.sendPayLoad(action, dataInBytes.length, dataInBytes);
		System.out.println(response);
	}
}
