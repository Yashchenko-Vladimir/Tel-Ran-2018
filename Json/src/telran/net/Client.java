package telran.net;
import java.net.*;
import java.io.*;
public class Client {
//protected Socket socket;
//protected ObjectInputStream input;
//protected ObjectOutputStream output;
//public Client(String hostname,int port)	throws UnknownHostException, IOException {
//	socket=new Socket(hostname,port);
//	output= new ObjectOutputStream(socket.getOutputStream());
//	input= new ObjectInputStream(socket.getInputStream());
//	
//}
	
	protected Socket socket;
	protected BufferedReader input;
	protected PrintStream output;
	public Client(String hostname, int port) {
		socket = new Socket(hostname, port);
		
		
	}
}
