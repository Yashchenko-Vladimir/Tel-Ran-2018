package telran.persons.service;



import java.util.List;

import telran.persons.Child;
import telran.persons.Person;

public interface IPersons {
	public boolean addPerson(Person person);
	public boolean removePerson(int id);
	public List<Person> getAllPersons();
	List<String> mostPopulatedCities();
	void displayAvgSalariesCompanies();
	List<Child> getChildrenMostPopularGartens();
	double averageSalary();
	void displaySalariesGroupDistribution(int interval) ;
	
	
}
