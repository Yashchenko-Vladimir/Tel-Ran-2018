package telran.persons.domain.service;

import java.util.List;

import telran.persons.domain.Child;
import telran.persons.domain.Person;
import telran.util.Address;

public interface IPersons {
	void addPersons(Iterable<Person> person);
	List<Person> getPersonsAge(int fromAge, int toAge);
	Person getPerson(int id);
	List<Person> getPersonsCity(String city);
	List<Person> getEmployeesSalaryGreat(int salary);
	List<Person> getEmployeesSalaryRange(int fromSalary, int toSalary);
	List<Person> getChildrenGarten(String garten);
	boolean updateAddress(int id, Address address);
}
