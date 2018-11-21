package telran.cars.controller.items.statist;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostProfitableModelItem extends CarsItem{

	public DisplayMostProfitableModelItem(InputOutput io, IRentCompany company) {
		super(io, company);
		
	}

	@Override
	public String displayedName() {
		return "display most profit models";
	}

	@Override
	public void perform() {
		company.getMostProfitModelNames().forEach(io::outputLine);
	}

}
