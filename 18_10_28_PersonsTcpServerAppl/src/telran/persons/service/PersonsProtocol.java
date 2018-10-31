package telran.persons.service;

import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;
import telran.persons.api.*;

import static telran.persons.api.PersonsApiConstants.*;

public class PersonsProtocol implements Protocol {
IPersons persons;

	@Override
	public Response getResponse(Request request) {
		String requestType = request.getRequestType();
		switch(requestType) {
		case ADD_PERSON: return _person_add(request);
		case REMOVE_PERSON: return _person_remove(request);
		case GET_ALL_PERSONS:return _person_get(request);
		default: return wrongRequest();
		}
		
	}

	private Response wrongRequest() {
		return new Response(ResponseCode.WRONG_REQUEST,null);		
	}

	private Response _person_get(Request request) {
		try {			
			return new Response(ResponseCode.OK, persons.getAllPersons());
			}
			catch(Exception e) {
				return wrongRequest();
			}
	}

	private Response _person_remove(Request request) {
		try {
		int id = (int) request.getRequestData();
		return new Response(ResponseCode.OK, persons.removePerson(id));
		}
		catch(Exception e) {
			return wrongRequest();
		}
	}

	private Response _person_add(Request request) {
		try {
		Person person = (Person) request.getRequestData();
		return new Response(ResponseCode.OK, persons.addPerson(person));
		}
		catch(Exception e) {
			return wrongRequest();
		}
	}

	public PersonsProtocol(IPersons persons) {
		super();
		this.persons = persons;
	}

}
