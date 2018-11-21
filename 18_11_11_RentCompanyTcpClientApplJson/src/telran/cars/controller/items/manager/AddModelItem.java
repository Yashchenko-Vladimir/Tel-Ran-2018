package telran.cars.controller.items.manager;

import telran.cars.controller.items.CarsItem;
import telran.cars.dto.Model;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddModelItem extends CarsItem{

	public AddModelItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	 public String displayedName() {
		return "Add New Model";
	}

	@Override
	public void perform() {
		String modelName = io.inputString("Enter Model Name");
		int gasTank =  io.inputInteger("Enter Supplied Gas Tank");
		String country= io.inputString("Enter Country of Production");
		int priceDay = io.inputInteger("Enter Rentring Price For A Day");
		String companyName = io.inputString("Enter Company Name");
		company.addModel(new Model(modelName, gasTank,  companyName,  country,  priceDay));
	}

}
