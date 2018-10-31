package telran.net;

import java.io.Serializable;

public class Response implements Serializable{
ResponseCode responseCode;
Object responseData;
public Response(ResponseCode responseCode, Object responseData) {
	super();
	this.responseCode = responseCode;
	this.responseData = responseData;
}
public ResponseCode getResponseCode() {
	return responseCode;
}
public Object getResponseData() {
	return responseData;
}

}
