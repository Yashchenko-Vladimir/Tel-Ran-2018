package telran.persons.service;

import java.util.List;

import telran.persons.api.*;

public interface IPersons {
boolean addPerson(Person person);
boolean removePerson(int id);
List<Person> getAllPersons();
List<String> mostPopulatedCities();
void displayAvgSalariesCompanies();
List<Child> getChildrenMostPopularGartens();
double averageSalary();
void displaySalariesGroupDistribution(int interval);
}
