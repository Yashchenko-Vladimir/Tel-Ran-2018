package telran.persons.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import telran.persons.Child;
import telran.persons.Employee;
import telran.persons.Person;
import telran.util.Address;

public class PersonsMap implements IPersons, Persistable {
	private  Map<Integer, Person> persons = new HashMap<>();
	

	@Override
	public boolean addPerson(Person person) {
		if(person == null)
			return false;
		return persons.putIfAbsent(person.getId(), person) == null;
	}

	@Override
	public boolean removePerson(int id) {
		return persons.remove(id) != null;
	}

	@Override
	public List<Person> getAllPersons() {
		return new ArrayList<>( persons.values());
	}

	@Override
	public List<String> mostPopulatedCities() {
				
		Map<String, Long> map = persons.values().stream()
//				.map(x -> x.getAddress().getCity())
//				.collect(Collectors.groupingBy(x-> x.toString(),Collectors.counting()));
				.collect(Collectors.groupingBy(x->  x.getAddress().getCity(), Collectors.counting() ));
		long max = map.values().stream().max(Long::compare).orElse(0l); // .mapToLong(x-> x).max().orElse(0);
		List<String> mostPopularCities = map.entrySet().stream()
				.filter(a -> a.getValue().equals(max))
					.map(x -> x.getKey())
						.collect(Collectors.toList());
		return mostPopularCities;
	}

	@Override
	public void displayAvgSalariesCompanies() {
		List<Employee> employees = persons.values().stream()
				.filter(x ->  x instanceof Employee)
					.map(x -> (Employee) x).collect(Collectors.toList());
//		Map<String, Double> averageSalaryComp = 
		employees.stream().collect(Collectors.groupingBy(x->x.getCompany() , Collectors.averagingDouble(x -> x.getSalary()))).entrySet()
		.stream().forEach(x -> System.out.printf("%s -> %.2f\n", x.getKey(), x.getValue()));
		
		
	}

	@Override
	public List<Child> getChildrenMostPopularGartens() {
		List<Child> childrenAll = persons.values().stream()
				.filter(x ->  x instanceof Child)
					.map(x -> (Child) x).collect(Collectors.toList());
		
		Map<String, Long> map = childrenAll.stream()
					.map(x-> x.getKindergarten())
						.collect(Collectors.groupingBy(x->x.toString(), Collectors.counting()));
		
		long max = map.values().stream().mapToLong(x-> x).max().orElse(0);
		
		List<String> mostPopularGarten = map.entrySet().stream()
				.filter(a -> a.getValue().equals(max))
					.map(x -> x.getKey())
						.collect(Collectors.toList());
		
		List<Child> children = childrenAll.stream()
				.filter(x -> mostPopularGarten.contains(x.getKindergarten()))
					.collect(Collectors.toList());
		
		
		
		return children;
	}

	@Override
	public double averageSalary() {
		double salaryAvr = persons.values().stream()
			.filter(x ->  x instanceof Employee)
				.map(x -> (Employee) x)
					.map(x->x.getSalary())
						.mapToDouble(x->x)
							.average()
								.orElse(0);
		return salaryAvr;
	}

	@Override
	public void displaySalariesGroupDistribution(int interval)  {

		 persons.values().stream()
			.filter(x ->  x instanceof Employee)
			.map(x -> (Employee) x)
				.collect(Collectors.groupingBy(x->x.getSalary()/interval
	//					,Collectors.mapping(x->x.getSalary(), Collectors.toList())    ������������� ������   Collectors.toList()  collect ��� ���������  map
						)).entrySet()
						.stream()
							.sorted((a, b) -> a.getKey().compareTo(b.getKey()))
								.forEach(x-> System.out.println("Salary range " + x.getKey()
								*interval + " - " + (x.getKey()*interval + (interval-1)) +"\n" + x.getValue() +"\n"));

	}

	@Override
	public void save(String fileName) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
			output.writeObject(this);
			System.out.println(this.getClass());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ���� ��� ��������� persistobal �� ���������
		// TODO Auto-generated method stub
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static IPersons restore(String fileName) throws IOException, IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
		PersonsMap personss = new PersonsMap();
//		System.out.println(input.readObject().getClass());
		personss =  (PersonsMap) input.readObject();
		
		input.close();
		
		return personss ;
	}


}
