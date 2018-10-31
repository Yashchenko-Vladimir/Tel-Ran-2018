package telran.persons.controller.items;

import java.time.LocalDate;

import telran.persons.api.Person;
import telran.persons.service.IPersons;
import telran.util.Address;
import telran.view.InputOutput;

public class AddPersonItem extends PersonsItem {

	public AddPersonItem(InputOutput io, IPersons persons) {
		super(io, persons);
	}

	@Override
	public String displayedName() {		
		return "Add person data";
	}

	@Override
	public void perform() {
		int id = io.inputInteger("Enter Id");
		String phone = io.inputString("Enter phone number");
		String name = io.inputString("Enter name");
		Address address = io.inputObject("Enter <city>#<street>#<Building>#<aprt>", AddPersonItem::parseAddress);
		LocalDate birthDate = io.inputDate("Enter birth date", "dd/MM/yyyy");
		Person person = new Person(id, phone, name, address, birthDate);
		System.out.println(person.toString());
		io.outputLine(persons.addPerson(person));

	}
	
	public static Address parseAddress(String str) {		
		String tokens[] = str.split("#");
		String city = tokens[0];
		String street = tokens[1];
		int building =Integer.parseInt(tokens[2]);
		int aprt = Integer.parseInt(tokens[3]);
		return new Address(city, street, building, aprt);
	}

}
