package telran.persons.controller.items;

import telran.persons.service.IPersons;
import telran.view.InputOutput;

public class RemovePersonItem extends PersonsItem {

	public RemovePersonItem(InputOutput io, IPersons persons) {
		super(io, persons);
		
	}

	@Override
	public String displayedName() {
		
		return "Remove person";
	}

	@Override
	public void perform() {
		
		
		io.outputLine(persons.removePerson(io.inputInteger("Entre ID") ));

	}

}
