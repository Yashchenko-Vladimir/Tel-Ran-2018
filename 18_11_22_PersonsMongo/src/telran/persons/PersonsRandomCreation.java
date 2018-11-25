package telran.persons;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.persons.domain.*;
import telran.persons.domain.service.IPersons;
import telran.util.Address;

@SpringBootApplication
public class PersonsRandomCreation {
private static final int N_PERSONS = 100;
private static final int EMPLOYEE_PROBABILITY = 70;
private static final int MIN_CHILD_YEAR = 2013;
private static final int MAX_CHILD_YEAR = 2018;
private static final int MIN_EMPLOYEE_YEAR = 1959;
private static final int MAX_EMPLOYEE_YEAR = 2000;
private static final int N_GARTENS = 5;
private static final int MIN_SALARY = 5000;
private static final int MAX_SALARY = 30000;
private static final int N_COMPANIES = 3;
private static final int N_STREETS = 2;
private static final int N_CITIES = 3;
private static final int N_PERSONS_NAMES = 20;
static Random gen=new Random();


	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = 
				SpringApplication.run(PersonsRandomCreation.class, args);
				
		List<Person> personsList=getRandomPersons();
		IPersons persons = ctx.getBean(IPersons.class);
		persons.addPersons(personsList);
		ctx.close();


	}

	private static List<Person> getRandomPersons() {
		List<Person> res=new ArrayList<>();
		for(int i=1; i<=N_PERSONS; i++) {
			res.add(getRandomPerson(i));
		}
		return res;
	}

	private static Person getRandomPerson(int id) {
		Person personData=getRandomPersonData(id);
		int chance=getRandomNumber(1,100);
		
		return chance<=EMPLOYEE_PROBABILITY?
				getRandomEmployee(personData):getRandomChild(personData);
	}

	private static Child getRandomChild(Person personData) {
		LocalDate birthDate=getRandomDate(MIN_CHILD_YEAR,
				MAX_CHILD_YEAR);
		String kindergarten=getRandomKindergarten();
		return new Child
		(personData.getId(), personData.getPhone(),
		personData.getName(), personData.getAddress(),
		birthDate, kindergarten);
	}
		
	private static String getRandomKindergarten() {
		return "garten"+getRandomNumber(1,N_GARTENS);
	}

	private static LocalDate getRandomDate
	(int minYear, int maxYear) {
		
		int year=getRandomNumber(minYear,maxYear);
		int month=getRandomNumber(1,12);
		int dayOfMonth=getRandomNumber(1,28);
		return LocalDate.of(year,
				month, dayOfMonth);
	}

	private static Employee getRandomEmployee(Person personData) {
		
		LocalDate birthDate=getRandomDate(MIN_EMPLOYEE_YEAR,
				MAX_EMPLOYEE_YEAR);
		String company=getRandomCompany();
		int salary=getRandomSalary();
		return new Employee
		(personData.getId(), personData.getPhone(),
		personData.getName(), personData.getAddress(),
		birthDate, company, salary);
	}
	
	
	private static int getRandomSalary() {
		
		return getRandomNumber(MIN_SALARY, MAX_SALARY);
	}

	private static String getRandomCompany() {
		return "company"+getRandomNumber(1, N_COMPANIES);
	}

	private static Person getRandomPersonData(int id) {
		
		String phone=getRandomPhone();
		String name=getRandomPersonName();
		Address address=getRandomAddress();
		return new Person
	(id, phone, name, address, null);
	}

	private static Address getRandomAddress() {
		
		String city=getRandomCity();
		int building=getRandomBuilding();
		int aprt=getRandomAprt();
		String street=getRandomStreet();
		return new Address
	(city, street, building, aprt);
	}

	private static String getRandomStreet() {
		
		return "street"+getRandomNumber(1,N_STREETS);
	}

	private static int getRandomAprt() {
		return getRandomNumber(1,100);
	}

	private static int getRandomBuilding() {
		return getRandomNumber(1,100);
	}

	private static String getRandomCity() {
		return "city"+getRandomNumber(1,N_CITIES);
	}

	private static String getRandomPersonName() {
		return "name"+getRandomNumber(1,N_PERSONS_NAMES);
	}

	private static String getRandomPhone() {
		String[]prefixes= {"050","051","052","053",
				"054","055","058"};
		return String.format("%s-%d",
				prefixes
				[getRandomNumber(0,prefixes.length-1)],
				getRandomNumber(1000000,9999999));
	}

	private static int getRandomNumber(int min, int max) {
		
		return gen.ints(1,min,max+1).findFirst().getAsInt();
	}

}
