package telran.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.registerModule(new JavaTimeModule());
	}
	
	public Client(String hostname, int port) throws UnknownHostException, IOException {
		socket = new Socket(hostname, port);
		output = new PrintStream(socket.getOutputStream());
		input = new  BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	protected <T> T sendRequest(String requestType, Object requestData, TypeReference<T> typeReferense)  {
		try{
			
		
        String requestDataJson = mapper.writeValueAsString(requestData);
		RequestJson request = new RequestJson(requestType, requestDataJson );
       
        
//            output.writeObject(request);
		String jsonRequest = mapper.writeValueAsString(request);
        	output.print(jsonRequest);
//            Response response = (Response) input.readObject();
        	String responseJsone = input.readLine();
        	ResponseJson responseJson = (ResponseJson) mapper.readValue(responseJsone, ResponseJson.class);
            ResponseCode code = responseJson.getResponseCode();
            if (code != ResponseCode.OK) {
                throw new RuntimeException(code.toString());
            }
            String responseData = responseJson.getResponseData();
            T res = mapper.readValue(responseData, typeReferense);
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
