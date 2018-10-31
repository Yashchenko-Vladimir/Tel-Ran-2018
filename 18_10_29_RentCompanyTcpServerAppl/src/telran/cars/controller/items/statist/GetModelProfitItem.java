package telran.cars.controller.items.statist;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetModelProfitItem extends CarsItem{

	public GetModelProfitItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
	}

	@Override
	public String displayedName() {
		
		return "Display car is most profit";
	}

	@Override
	public void perform() {
		String modelName = io.inputString("Input model name");
		if(modelName == null)return;
		io.outputLine(modelName + " = " + rentCompany.getModelProfit(modelName));
		
	}

}
