package telran.cars.controller.items.driver;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetCarDriversItem extends CarsItem{

	public GetCarDriversItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		
		return "Display all driver use car";
	}

	@Override
	public void perform() {
		rentCompany.getCarDrivers(io.inputString("Input car's reg number"))
		.forEach(io::outputLine);
		
	}

}
