package telran.persons.controller;

import telran.persons.controller.items.*;
import telran.persons.service.*;
import telran.view.*;


public class PersonsAppl {
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		IPersons persons = new PersonsMap();
		Item [] items = {
				new AddPersonItem(io, persons),
				new RemovePersonItem(io, persons),
				new DisplayPersonItem(io, persons)
				
		};
		
		Menu menu = new MenuWithExit(io, items);
		menu.runMenu();
	}

}
