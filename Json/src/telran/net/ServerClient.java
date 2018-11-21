package telran.net;
import java.net.*;


import java.io.*;
public class ServerClient implements Runnable{
Socket socket;
ObjectInputStream input;
ObjectOutputStream output;
private Protocol protocol;
public ServerClient (Socket socket,
		Protocol protocol) throws IOException {
	this.socket=socket;
	this.protocol=protocol;
	input=new ObjectInputStream
			(socket.getInputStream());
	output=new ObjectOutputStream
			(socket.getOutputStream());
}
	@Override
	public void run() {
		try {
			while (true) {
				Request request =
						(Request) input.readObject();
				Response response =
						protocol.getResponse(request);
				output.writeObject(response);
			}
		}catch(EOFException e) {
			System.out.println
			("client closed connection");
		}
		catch(Exception e) {
			System.out.println("error "+e);
		}
		
	}

}
