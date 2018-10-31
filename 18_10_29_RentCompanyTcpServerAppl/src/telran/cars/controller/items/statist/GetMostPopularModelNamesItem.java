package telran.cars.controller.items.statist;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetMostPopularModelNamesItem extends CarsItem{

	public GetMostPopularModelNamesItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		}

	@Override
	public String displayedName() {
		return "Display most popular model";
	}

	@Override
	public void perform() {
		rentCompany.getMostPopularModelNames().forEach(x -> System.out.println(x + " is most popular model"));
		
	}

}
