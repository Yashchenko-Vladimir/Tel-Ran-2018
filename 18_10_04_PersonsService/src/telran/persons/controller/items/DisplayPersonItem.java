package telran.persons.controller.items;

import telran.persons.service.IPersons;
import telran.view.InputOutput;

public class DisplayPersonItem extends PersonsItem {

	public DisplayPersonItem(InputOutput io, IPersons persons) {
		super(io, persons);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		
		return "Display all persons data";
	}

	@Override
	public void perform() {
		persons.getAllPersons().forEach(System.out:: println);

	}

}
