package telran.persons.controller;

import java.io.IOException;

import telran.net.Protocol;
import telran.net.Server;
import telran.persons.service.PersonsMap;
import telran.persons.service.PersonsProtocol;


public class PersonsServerAppl {

	public static void main(String[] args) throws IOException {
		Protocol protocol = new PersonsProtocol(new PersonsMap());
		int port =2000;
		System.out.println("server is listening port " + port);
		Server server = new Server(protocol, port);
		server.run();		
	}

}
