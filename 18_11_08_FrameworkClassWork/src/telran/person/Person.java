package telran.person;

import java.time.LocalDate;

import telran.annotations.Id;
import telran.annotations.Index;
import telran.annotations.Table;
@Table(name = "Pupu")
public class Person {
	@Id
	int id;
	@Index(unique = true)
	String name;
	@Index
	LocalDate birthDay;
	@Index(unique =true)
	String city;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {return id;}
	

}
