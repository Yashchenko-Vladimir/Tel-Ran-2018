package telran.cars.controller.items.driver;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayDriverCarsItem extends CarsItem {

	public DisplayDriverCarsItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Display all cars of driver";
	}

	@Override
	public void perform() {
		Long driver = io.inputLong("Enter driver license ID");
		if (driver != null)
			company.getDriverCars(driver).forEach(io::outputLine);
	}

}
