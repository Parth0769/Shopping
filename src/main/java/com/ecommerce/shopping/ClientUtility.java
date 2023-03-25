package com.ecommerce.shopping;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientUtility {
	private Socket clientSocket;
	private DataOutputStream out;
	private BufferedReader in;
	DataOutputStream output;

	public void startConnection(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
//			output = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
			out = new DataOutputStream(clientSocket.getOutputStream());

			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String sendPayLoad(int action, int length, byte[] dataInBytes) {

		String resp = null;
		try {
			out.writeInt(action);
			out.writeInt(length);
			out.write(dataInBytes);
			resp = in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return resp;

	}
}