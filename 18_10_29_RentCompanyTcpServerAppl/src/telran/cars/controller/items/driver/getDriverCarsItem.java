package telran.cars.controller.items.driver;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class getDriverCarsItem extends CarsItem{

	public getDriverCarsItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Display all cars which use driver";
	}

	@Override
	public void perform() {
		Long lisenceID = io.inputLong("Input license ID");
		if(lisenceID != null) {
			rentCompany.getDriverCars(lisenceID).forEach(io::outputLine);
		}
		
		
	}

}
