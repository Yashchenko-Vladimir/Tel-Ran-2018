package telran.persons.controller.items;

import telran.persons.service.*;
import telran.view.*;

public abstract class PersonsItem implements Item {
	protected InputOutput io;
	protected IPersons persons;

	public PersonsItem(InputOutput io, IPersons persons) {
		super();
		this.io = io;
		this.persons = persons;
	} 
	
	
}
