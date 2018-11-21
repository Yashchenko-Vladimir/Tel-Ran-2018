package telran.net;

import java.io.*;
import java.net.*;

public class Server {
Protocol protocol;
int port;
ServerSocket serverSocket;

public Server(Protocol protocol, int port) throws IOException {
	this.protocol = protocol;
	this.port = port;
	serverSocket = new ServerSocket(port);

}

public  void run()
{
	try {
		while (true) {
			Socket socket = serverSocket.accept();
			ServerClient serverClient =	new ServerClient(socket, protocol);
			Thread thread = new Thread(serverClient);
			thread.start();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

