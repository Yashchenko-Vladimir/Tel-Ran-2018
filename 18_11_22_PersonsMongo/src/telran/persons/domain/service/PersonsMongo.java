package telran.persons.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.persons.domain.Child;
import telran.persons.domain.Person;
import telran.persons.domain.repo.PersonsMongoRepository;
import telran.util.Address;

@Service
public class PersonsMongo implements IPersons{
	@Autowired
	PersonsMongoRepository personsRepository;
	
	@Override
	public void addPersons(Iterable<Person> persons) {
		personsRepository.saveAll(persons);
		
	}

	@Override
	public List<Person> getPersonsAge(int fromAge, int toAge) {
		LocalDate from = getDateFromAge(toAge);
		LocalDate to = getDateFromAge(fromAge);
		return personsRepository.findByBirthDateBetween(from, to);
	}

	private LocalDate getDateFromAge(int age) {
		LocalDate current = LocalDate.now();
		return current.minusYears(age);
	}

	@Override
	public Person getPerson(int id) {
		
		return personsRepository.findById(id).orElse(null);
	}

	@Override
	public List<Person> getPersonsCity(String city) {
		return personsRepository.findByAddressCity(city);
	}

	@Override
	public List<Person> getEmployeesSalaryGreat(int salary) {
		return personsRepository.findBySalary(salary, Integer.MAX_VALUE);
	}

	@Override
	public List<Person> getEmployeesSalaryRange(int fromSalary, int toSalary) {
		return personsRepository.findBySalary(fromSalary, toSalary);
	}

	@Override
	public List<Child> getChildrenGarten(String garten) {
		System.out.println(garten);
		return personsRepository.findByKindergarten(garten);
	}

	@Override
	public boolean updateAddress(int id, Address address) {
		Person person = personsRepository.findById(id).orElse(null);
		if(person == null) 
			return false;
		person.setAddress(address);
		personsRepository.save(person);
		return true;
	}

}
