package telran.net;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.json.*;

@SuppressWarnings("serial")
public class RequestJson implements Serializable {
//String requestType;
//Object requestData;
//public String getRequestType() {
//	return requestType;
//}
//public Object getRequestData() {
//	return requestData;
//}
//public Request(String requestType, Object requestData) {
//	super();
//	this.requestType = requestType;
//	this.requestData = requestData;
//}
	String requestType;
	String requestData;
	static ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.registerModule(new JavaTimeModule());
//		mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
//        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
	}
	
	public <T> T getRequestData(TypeReference<T> typeReference) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(requestData, typeReference);
	}
	
	public RequestJson() {
	}
	
	public RequestJson(String requestType, String requestData) throws JsonParseException, JsonMappingException, IOException {
		this.requestType = requestType;
		this.requestData = requestData;
	}



	public String getRequestType() {
		return requestType;
	}


	public String getRequestData() {
		return requestData;
	}
	

}
