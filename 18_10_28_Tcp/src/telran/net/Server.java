package telran.net;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Server {
	Protocol protocol;
	int port;
	ServerSocket serverSocket;
	private int nThreads = 3;

	public Server(Protocol protocol, int port) throws IOException {
		this.protocol = protocol;
		this.port = port;
		serverSocket = new ServerSocket(port);

	}

	public void run() {
		ExecutorService executor = null;
		try {
			 executor = Executors.newFixedThreadPool(nThreads, new ThreadFactory() {
				
				@Override
				public Thread newThread(Runnable runThread) {
					Thread tt = Executors.defaultThreadFactory().newThread(runThread);
					tt.setDaemon(true);
					return tt;
				}
			});
			while (true) {
				Socket socket = serverSocket.accept();
				ServerClient serverClient = new ServerClient(socket, protocol);
				executor.execute(serverClient);
				
//				Thread thread = new Thread(serverClient);
//				thread.start();
			}
		} catch (IOException e) {
			executor.shutdown();
			e.printStackTrace();
		}
	}
}
