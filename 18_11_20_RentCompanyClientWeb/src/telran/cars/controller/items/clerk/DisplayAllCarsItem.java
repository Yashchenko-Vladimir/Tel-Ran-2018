package telran.cars.controller.items.clerk;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllCarsItem extends CarsItem{

	public DisplayAllCarsItem(InputOutput io,
			IRentCompany company) {
		super(io, company);
	
	}

	@Override
	public String displayedName() {
		
		return "Display all cars";
	}

	@Override
	public void perform() {
		company.getAllCars()
		.forEach(io::outputLine);
		
	}

}
