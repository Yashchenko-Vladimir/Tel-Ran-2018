package telran.persons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.persons.domain.Child;
import telran.persons.domain.Person;
import telran.persons.domain.service.IPersons;
import telran.util.Address;

@RestController
public class PersonsController {
	@Autowired
	IPersons persons;
	
	@GetMapping("/persons/by_age")
	List<Person> getPersonsByAge(@RequestParam(name="fromAge", defaultValue = "0") int fromAge, 
			@RequestParam(name = "toAge", defaultValue = "200") int toAge){
		return persons.getPersonsAge(fromAge, toAge);
	}
	
	@GetMapping("/person/{id}")
	Person getPerson(@PathVariable("id") int id) {
		return persons.getPerson(id);
	}
	
	@GetMapping("/persons/{city}")
	List<Person> getPersonsByCity(@PathVariable String city){
		return persons.getPersonsCity(city);
	}
	
	@PostMapping("/person/{id}")
	boolean updateAddress(@PathVariable("id") int id, @RequestBody Address address) {
		return persons.updateAddress(id, address);
	}
	
	@GetMapping("/employees")
	List<Person> getEmployeesBySalary(@RequestParam(name = "from", defaultValue = "-1") int fromSalary, 
			@RequestParam(name =  "to", defaultValue= "-1") int toSalary){
		if(toSalary == -1)
			return persons.getEmployeesSalaryGreat(fromSalary);
		return persons.getEmployeesSalaryRange(fromSalary, toSalary);
	}
	
	@GetMapping("/children/{garten}")
	List<Person> getChildrenGarten(@PathVariable("garten") String garten){
		return persons.getChildrenGarten(garten);
	}
}
