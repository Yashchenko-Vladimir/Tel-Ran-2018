package telran.net;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable {
String requestType;
Object requestData;
public String getRequestType() {
	return requestType;
}
public Object getRequestData() {
	return requestData;
}
public Request(String requestType, Object requestData) {
	super();
	this.requestType = requestType;
	this.requestData = requestData;
}

}
