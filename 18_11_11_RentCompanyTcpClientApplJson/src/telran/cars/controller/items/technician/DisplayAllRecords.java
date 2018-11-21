package telran.cars.controller.items.technician;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllRecords extends CarsItem{

	public DisplayAllRecords(InputOutput io, IRentCompany company) {
		super(io, company);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		
		return "Display all records";
	}

	public void perform() {	
		company.getAllRecords().forEach(io::outputLine);
	}

}
