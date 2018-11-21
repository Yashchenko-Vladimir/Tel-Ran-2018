package telran.cars.controller.items.statist;

import java.util.List;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayModelProfitItem extends CarsItem {

	public DisplayModelProfitItem
	(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Display model profit";
	}

	@Override
	public void perform() {
		String modelName=inputModelName();
		if(modelName!=null)
			io.outputLine
			(company.getModelProfit(modelName));
	}

}
