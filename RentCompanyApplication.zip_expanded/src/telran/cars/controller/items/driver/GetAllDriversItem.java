package telran.cars.controller.items.driver;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetAllDriversItem extends CarsItem{

	public GetAllDriversItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		
	}

	@Override
	public String displayedName() {
		
		return "Display all drivers";
	}

	@Override
	public void perform() {
		rentCompany.getAllDrivers().forEach(io:: outputLine);
		
	}

}
