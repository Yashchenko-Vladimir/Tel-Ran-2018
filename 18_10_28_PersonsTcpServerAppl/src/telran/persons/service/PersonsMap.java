package telran.persons.service;
import java.util.*;
import java.util.stream.Collectors;

import telran.persons.api.*;

public class PersonsMap implements IPersons {
	private Map<Integer, Person> persons;
	
	public PersonsMap() {
		persons = new HashMap<>();
	}
	@Override
	public boolean addPerson(Person person) {
		if(person==null)
			return false;
		return persons.putIfAbsent(person.getId(),
				person)==null;
	}
	@Override
	public boolean removePerson(int id) {
		return persons.remove(id) != null;
	}
	@Override
	public List<Person> getAllPersons() {
		List<Person> list = new ArrayList<>(persons.values());
		return list;
	}
	@Override
	public List<String> mostPopulatedCities() {
System.out.println("\nmostPopulatedCities:");
		Map<String, Long> mapAllPersons = 
				persons.
				values()
				.stream()
				.collect(Collectors.groupingBy(x ->
				x.getAddress().getCity(),Collectors.counting()));
		long maxResidents = mapAllPersons
				.values()
				.stream()
				.max(Long::compare)
				.orElse(0l);
		return mapAllPersons
		.entrySet()
		.stream()
		.filter(x -> x.getValue() == maxResidents)
		.map(x -> x.getKey())
		.collect(Collectors.toList());
	}
	@Override
	public void displayAvgSalariesCompanies() {
		Map<String, Double> map = persons.values().stream()
				.filter(x -> x instanceof Employee)
				.map(x -> (Employee) x)
				.collect(Collectors.groupingBy
	(Employee::getCompany,
			Collectors.averagingInt(Employee::getSalary)));
		System.out.println(map);	
		
	}
	@Override
	public List<Child> getChildrenMostPopularGartens() {
		Map <String, List<Child>> gardens = persons
				.values()
				.stream().
				filter(x->x instanceof Child)
				.map(x->(Child)x)
				.collect(Collectors.groupingBy
						(Child::getKindergarten));
		
		int max = gardens
				.values()
				.stream()
				.mapToInt(x->x.size())
				.max()
				.orElse(0);
				
		List<Child> listGardens = gardens.values().stream()
				.filter(x->x.size()==max)
				.flatMap(x->x.stream())
				.collect(Collectors.toList());
		
		return listGardens;
	}
	@Override
	public double averageSalary() {
//		  return persons.values().stream()
//	     .filter(x -> x instanceof Employee)
//.mapToDouble(x -> ((Employee) x).getSalary())
//.average().getAsDouble();
		  return persons.values().stream()
	                .filter(x-> x instanceof Employee)
	                .map(x-> (Employee)x)
	                .collect(Collectors.averagingInt(Employee::getSalary));

	}
	@Override
	public void displaySalariesGroupDistribution(int interval) {
		persons.values().stream()
		.filter(x -> x instanceof Employee)
		.map(x -> (Employee) x)
		.collect(Collectors.groupingBy(x -> 
		x.getSalary()/interval))//Map<Integer,List<Employee>>		
		.entrySet().stream()
		.sorted((x, y) -> x.getKey().compareTo(y.getKey()))		
		.forEach(x -> System.out.printf
		("salaries from %d to %d:\n %s\n",
		x.getKey()*interval, x.getKey()*interval + interval-1,
		x.getValue()));
		
	}
	
}
