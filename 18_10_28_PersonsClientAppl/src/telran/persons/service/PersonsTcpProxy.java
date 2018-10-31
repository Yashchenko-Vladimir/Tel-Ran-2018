package telran.persons.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import telran.net.*;
import telran.persons.api.Child;
import telran.persons.api.Person;
import static telran.persons.api.PersonsApiConstants.*;

public class PersonsTcpProxy extends Client implements IPersons {

	public PersonsTcpProxy(String hostname, int port) throws UnknownHostException, IOException {
		super(hostname, port);
	}

	@Override
	public boolean addPerson(Person person) {
		return sendRequest(ADD_PERSON, person);				
	}

	private <T> T sendRequest(String requestType, Object requestData){

		Request request = new Request(requestType, requestData);
		try {
			output.writeObject(request);
			Response response = (Response) input.readObject();
			ResponseCode code = response.getResponseCode();
			if (code!=ResponseCode.OK)
				throw new RuntimeException(code.toString());
			return (T) response.getResponseData();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public boolean removePerson(int id) {
		return sendRequest(REMOVE_PERSON, id);			
	}

	@Override
	public List<Person> getAllPersons() {
		return sendRequest(GET_ALL_PERSONS, null);		
	}

	@Override
	public List<String> mostPopulatedCities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayAvgSalariesCompanies() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Child> getChildrenMostPopularGartens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double averageSalary() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void displaySalariesGroupDistribution(int interval) {
		// TODO Auto-generated method stub

	}

}
