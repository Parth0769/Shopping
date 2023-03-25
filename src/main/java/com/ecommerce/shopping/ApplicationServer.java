package com.ecommerce.shopping;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.ecommerce.shopping.model.Orders;
import com.ecommerce.shopping.service.OrderService;
import com.ecommerce.shopping.service.OrderServiceImpl;

public class ApplicationServer {

	public static void main(String[] args) {
		ApplicationServer server = new ApplicationServer();
		server.start(7474, 10);
	}

	private ServerSocket serverSocket;

	public void start(int port, int noOfClients) {
		try {
			serverSocket = new ServerSocket(port);
			int clientCount = 0;
			while (clientCount <= noOfClients) {
				new ClientHandler(serverSocket.accept()).start();
				++clientCount;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ClientHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		DataInputStream in;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
				handleClientRequet();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void handleClientRequet() throws IOException {
			int action = in.readInt();
			int length = in.readInt();
			if (action == 1) {
				getAllOrderRequest(length);
			} else if (action == 2) {
				Long id = (long) 1;
				getOrderDetails(id);
			} else if (action == 3) {
				Orders order = new Orders();
				placeOrder(order);
			}
		}

		private void placeOrder(Orders order) {
			OrderService service = new OrderServiceImpl();
			out.write(service.placeOrder(order));
		}

		private void getAllOrderRequest(int length) throws IOException {
			OrderService service = new OrderServiceImpl();
			byte[] messageByte = new byte[length];
			boolean end = false;
			StringBuilder dataString = new StringBuilder(length);
			int totalBytesRead = 0;
			while (!end) {
				int currentBytesRead = in.read(messageByte);
				totalBytesRead = currentBytesRead + totalBytesRead;
				if (totalBytesRead <= length) {
					dataString.append(new String(messageByte, 0, currentBytesRead, StandardCharsets.UTF_8));
				} else {
					dataString.append(new String(messageByte, 0, length - totalBytesRead + currentBytesRead,
							StandardCharsets.UTF_8));
				}
				System.out.println("Data read:" + dataString.toString());

				if (dataString.length() >= length) {
					end = true;
				}
			}
			out.println(service.getAllOrders());
		}

		public void getOrderDetails(Long id) {
			OrderService service = new OrderServiceImpl();
			out.println(service.getOrderDetails(id));
		}
	}

}