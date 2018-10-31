package telran.cars.controller.items.clerk;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetAllCarsItem extends CarsItem{

	public GetAllCarsItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Display all cars";
	}

	@Override
	public void perform() {
		rentCompany.getAllCars().forEach(io::outputLine);
		
	}

}
