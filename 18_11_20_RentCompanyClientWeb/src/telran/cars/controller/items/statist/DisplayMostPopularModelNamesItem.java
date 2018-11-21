package telran.cars.controller.items.statist;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostPopularModelNamesItem extends CarsItem {

	public DisplayMostPopularModelNamesItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Display Most Popular Model Names";
	}

	@Override
	public void perform() {
		company.getMostPopularModelNames().forEach(io::outputLine);
	}


}
