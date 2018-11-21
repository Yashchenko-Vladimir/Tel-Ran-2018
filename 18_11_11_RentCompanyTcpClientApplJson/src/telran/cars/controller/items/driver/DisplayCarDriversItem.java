package telran.cars.controller.items.driver;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayCarDriversItem extends CarsItem {

	public DisplayCarDriversItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Display all drivers of car";
	}

	@Override
	public void perform() {
		String carNum = io.inputString
				("Enter car number");
		if (carNum != null)
			company.getCarDrivers(carNum).forEach(io::outputLine);
		
	}

}
