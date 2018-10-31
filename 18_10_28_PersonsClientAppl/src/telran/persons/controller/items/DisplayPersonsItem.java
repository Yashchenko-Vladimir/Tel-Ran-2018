package telran.persons.controller.items;

import telran.persons.service.IPersons;
import telran.view.InputOutput;

public class DisplayPersonsItem extends PersonsItem {

	public DisplayPersonsItem(InputOutput io, IPersons persons) {
		super(io, persons);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Display all persons data";
	}

	@Override
	public void perform() {
		persons.getAllPersons().forEach(io::outputLine);
		
	}

}
