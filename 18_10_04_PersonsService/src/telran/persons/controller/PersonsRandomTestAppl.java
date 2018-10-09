package telran.persons.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import telran.persons.*;
import telran.persons.service.*;
import telran.util.Address;

public class PersonsRandomTestAppl {
	
	static Random gen = new Random();

	private static final int INTERVAL = 1000;
	private static final int N_PERSONS = 100;

	private static final int EMPLOYEE_PROBABILITY = 0;

	private static final int MIN_CHILD_YEAR = 0;

	private static final int MAX_CHILD_YEAR = 0;

	private static final int MIN_EMPLOYEE_YEAR = 0;

	private static final int MAX_EMPLOYEE_YEAR = 0;

	private static final int N_GARTENS = 0;

	private static final int MIN_SALARY = 0;

	private static final int MAX_SALARY = 0;

	private static final int N_COMPANIES = 0;

	private static final int N_STREETS = 0;

	

	public static void main(String[] args) {
		List<Person> persons = getRandomPersons();
		IPersons personsServiece = new PersonsMap();
		persons.forEach(personsServiece:: addPerson);
		System.out.println("Printing all persons:");
		personsServiece.getAllPersons().forEach(System.out:: println);
		System.out.println("\n\nPrinting all employess:");
		personsServiece.displaySalariesGroupDistribution(INTERVAL);
	}

	private static List<Person> getRandomPersons() {
		List<Person> result = new ArrayList<>();
		for (int i = 0; i < N_PERSONS; i++) {
			result.add(getRandomPerson(i));
		}
		return result;
	}

	private static Person getRandomPerson(int id) {
		Person personData = getRandomPersonData(id);
		int chanse = getRandomNumber(1,100);
		return chanse <=EMPLOYEE_PROBABILITY?getRandomEmployee(personData):getRandomChild(personData);
	}

	private static Child getRandomChild(Person personData) {
		String kindergarten = getRandomKindergarten();
		LocalDate birthDate = getRandomDate(MIN_CHILD_YEAR, MAX_CHILD_YEAR);
		return new Child(personData.getId(), personData.getPhone(), personData.getName(), personData.getAddress(), kindergarten, birthDate);
	}

	private static String getRandomKindergarten() {
		return "garten" + getRandomNumber(1, N_GARTENS);
	}

	private static Employee getRandomEmployee(Person personData) {
		LocalDate birthDate = getRandomDate(MIN_EMPLOYEE_YEAR, MAX_EMPLOYEE_YEAR);
		String company = getRandomCompany();
		int salary = getRandomSalary();
		return new Employee(personData.getId(), personData.getPhone(), personData.getName(), personData.getAddress(), company, salary, birthDate);
	}

	private static LocalDate getRandomDate(int minYear, int maxYear) {
		int year = getRandomNumber(minYear, minYear);
		int month = getRandomNumber(1, 12);
		int dayOfMonth = getRandomNumber(1, 28);;
		return LocalDate.of(year, month, dayOfMonth);
	}

	private static int getRandomSalary() {
		return getRandomNumber(MIN_SALARY, MAX_SALARY);
	}

	private static String getRandomCompany() {
		return "company" + getRandomNumber(1, N_COMPANIES);
	}

	private static Person getRandomPersonData(int id) {
		
		String phone = getRandomPhone();
		String name = getRandomPersonName();
		Address address = getRandomAdress();
		return new Person(id, phone, name, address, null);
	}

	private static Address getRandomAdress() {
		String city = getRandomCity();
		String street = getRandomStreet();
		int building = getRandomBuilding();
		int aprt = getRandomAprt();
		return new Address(city, street, building, aprt);
	}

	private static int getRandomAprt() {
		return getRandomNumber(1, 100);
	}

	private static int getRandomBuilding() {
		return getRandomNumber(1, 100);
	}

	private static String getRandomStreet() {
		return "street" + getRandomNumber(1, N_STREETS);
	}

	private static String getRandomCity() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getRandomPersonName() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getRandomPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	private static int getRandomNumber(int min, int max) {
		
		return gen.ints(1, min, max+1).findFirst().getAsInt();
	}

}

