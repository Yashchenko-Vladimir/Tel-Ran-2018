package telran.persons.controller;

import telran.persons.service.IPersons;
import telran.persons.service.PersonsMap;

import java.io.IOException;
//
//import telran.persons.*;
//import telran.persons.service.*;

public class PersonsTestAppl {

	private static final int INTERVAL = 5000;

	public static void main(String[] args) throws Exception, IOException {
		IPersons personsServiece = PersonsMap.restore("TestPerson.txt");// TODO
//		System.out.println("Printing all persons:");
//		personsServiece.getAllPersons().forEach(System.out:: println);
		System.out.println("\n\nPrinting all employess:");
		personsServiece.displaySalariesGroupDistribution(INTERVAL);

	}

}
