package telran.persons.controller.items;

import telran.persons.service.IPersons;
import telran.view.InputOutput;

public class RemovePersonItem extends PersonsItem {

	public RemovePersonItem(InputOutput io, IPersons persons) {
		super(io, persons);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Remove person";
	}

	@Override
	public void perform() {
		int id = io.inputInteger("Enter id");
		io.outputLine(persons.removePerson(id));
		

	}

}
