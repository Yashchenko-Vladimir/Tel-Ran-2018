package telran.net;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ResponseJson implements Serializable{
ResponseCode responseCode;
String responseData;
static ObjectMapper mapper = new ObjectMapper();

static {
	mapper.registerModule(new JavaTimeModule());
}

public ResponseJson() {
	
}

public ResponseJson (ResponseCode responseCode, Object ressponseData) throws Exception {
	this.responseCode = responseCode;
	this. responseData = mapper.writeValueAsString(ressponseData);
}

public ResponseJson(ResponseCode responseCode, String responseData) {
	super();
	this.responseCode = responseCode;
	this.responseData = responseData;
}
public ResponseCode getResponseCode() {
	return responseCode;
}
public String getResponseData() {
	return responseData;
}

}
