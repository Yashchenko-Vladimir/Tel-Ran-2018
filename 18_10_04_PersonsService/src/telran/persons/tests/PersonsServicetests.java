package telran.persons.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.persons.Child;
import telran.persons.Employee;
import telran.persons.Person;
import telran.persons.service.PersonsMap;
import telran.util.Address;

class PersonsServicetests {
	PersonsMap personMap; 
	Person p1 = new Person(123, "1234567", "Vasya", new Address("Lod", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
	Person p2 = new Person(124, "1234568", "Dafna", new Address("Lod", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
	Person p3 = new Person(125, "1234567", "Ilya", new Address("Brest", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
	Person p4 = new Person(126, "1234568", "Boris", new Address("Brest", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
	Person p5 = new Person(127, "1234567", "Dina", new Address("Lviv", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
	Person p6 = new Person(128, "1234568", "Bert", new Address("Kiev", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
	Child ch1 = new Child(210, "1234568", "Moisha", new Address("Lod", "Sokolov", 25, 10), "Sovok", LocalDate.of(1990, 10, 10));
	Child ch2 = new Child(211, "1234568", "Dany", new Address("Brest", "Sokolov", 25, 10), "Sovok", LocalDate.of(1990, 10, 10));
	Child ch3 = new Child(212, "1234568", "Petr", new Address("Kiev", "Sokolov", 25, 10), "Busel", LocalDate.of(1990, 10, 10));
	Employee em1 = new Employee(312, "1234568", "Petr", new Address("Lod", "Sokolov", 25, 10), "Sony", 3850, LocalDate.of(1990, 10, 10));
	Employee em2 = new Employee(314, "1234568", "Petr", new Address("Lod", "Sokolov", 25, 10), "Sony", 3100, LocalDate.of(1990, 10, 10));
	Employee em3 = new Employee(315, "1234568", "Petr", new Address("Brest", "Sokolov", 25, 10), "IBM", 3300, LocalDate.of(1990, 10, 10));
	Employee em4 = new Employee(316, "1234568", "Petr", new Address("Brest", "Sokolov", 25, 10), "IBM", 4850, LocalDate.of(1990, 10, 10));

	@BeforeEach
	void setUp() throws Exception {
		personMap = new PersonsMap();
		personMap.addPerson(p1);
		personMap.addPerson(p2);
		personMap.addPerson(p3);
		personMap.addPerson(p4);
		personMap.addPerson(p5);
		personMap.addPerson(p6);
		personMap.addPerson(ch1);
		personMap.addPerson(ch2);
		personMap.addPerson(ch3);
		personMap.addPerson(em1);
		personMap.addPerson(em2);
		personMap.addPerson(em3);
		personMap.addPerson(em4);
		
	}

	@Test
	void test() {
		Employee expected = new Employee(123, "1234567", "Vasya", new Address("Lod", "Sokolov", 25, 10), "Motorola", 10000, LocalDate.of(1990, 10, 10));
		Employee actual = new Employee(123, "1234567", "Vasya", new Address("Lod", "Sokolov", 25, 10), "Motorola", 10000, LocalDate.of(1990, 10, 10));
		assertEquals(expected, actual);
		
	}
	
	@Test
	void addPersonTest() {
		Person p7 = new Person(120, "1234567", "Vasya", new Address("Lod", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
		Person p8 = new Person(121, "1234568", "Dafna", new Address("Lod", "Sokolov", 25, 10), LocalDate.of(1990, 10, 10));
		
		assertTrue(personMap.addPerson(p7));
		assertTrue(personMap.addPerson(p8));
		assertFalse(personMap.addPerson(p7));
		assertFalse(personMap.addPerson(null));
		
	}
	
	@Test 
	void removePersonTest(){
		
		assertTrue(personMap.removePerson(p1.getId()));
		assertFalse(personMap.removePerson(15));
		
	}
	
	@Test
	void getAllPersons() {
		List <Person> list = personMap.getAllPersons();
		List <Person> list2 = new ArrayList<>();
		list2.add(p1);
		list2.add(p2);
		list2.add(p3);
		list2.add(p4);
		list2.add(p5);
		list2.add(p6);
		list2.add(ch1);
		list2.add(ch2);
		list2.add(ch3);
		list2.add(em1);
		list2.add(em2);
		list2.add(em3);
		list2.add(em4);
		list.sort((o1, o2) -> o1.getId() - o2.getId());
		list2.sort((o1, o2) -> o1.getId() - o2.getId());
		assertEquals(list,list2);
		
		
	}
	
	@Test
	void popularCitiesTest() {
		List <String> actual = personMap.mostPopulatedCities();
		List <String> extend = new ArrayList<>();
		extend.add("Lod");
		extend.add("Brest");
		actual.sort((o1, o2) -> o1.compareTo(o2));
		extend.sort((o1, o2) -> o1.compareTo(o2));
		assertEquals(actual,extend);
		
	}
	
	@Test
	void mostpopelarGartenTest() {
		List<Child> actual = personMap.getChildrenMostPopularGartens();
		List<Child> extend = new ArrayList<>();
		extend.add(ch1);
		extend.add(ch2);
		actual.sort((o1, o2) -> o1.getId()- o2.getId());
		extend.sort((o1, o2) -> o1.getId()- o2.getId());
		assertEquals(actual,extend);
			
	}
	
	@Test
	void averageSalaryTest() {
		assertEquals(3775, personMap.averageSalary(), 0.01);
	}
	
	@Test
	void averagSalaryComp() {
		personMap.displayAvgSalariesCompanies();
	}
	
	@Test
	 void tt() {
		personMap.displaySalariesGroupDistribution(500);
	}

}
