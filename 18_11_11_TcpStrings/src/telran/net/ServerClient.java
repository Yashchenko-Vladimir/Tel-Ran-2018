package telran.net;
import java.net.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
public class ServerClient implements Runnable{
Socket socket;
//ObjectInputStream input;
//ObjectOutputStream output;
BufferedReader input;
PrintStream output;
private Protocol protocol;
ObjectMapper mapper = new ObjectMapper();



public ServerClient (Socket socket,
		Protocol protocol) throws IOException {
	this.socket=socket;
	this.protocol=protocol;
	input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	output=new PrintStream(socket.getOutputStream());
	mapper.registerModule(new JavaTimeModule());
	
}

	
	
	public void run() {
		
		try {
			while (true) {
				String line = input.readLine();
				if(line == null)
					break;
				RequestJson request =	(RequestJson) mapper.readValue(line, RequestJson.class);
				ResponseJson responseJson =	protocol.getResponse(request);
				
				output.print(responseJson);

			}
		}catch(EOFException e) {
			System.out.println("client closed connection");
		}
		catch(Exception e) {
			System.out.println("error "+e);
		}
		
	}

}
