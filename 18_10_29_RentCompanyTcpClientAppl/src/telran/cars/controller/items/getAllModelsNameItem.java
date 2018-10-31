package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class getAllModelsNameItem extends CarsItem {

	public getAllModelsNameItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		
	}

	@Override
	public String displayedName() {
		return "Get information about all models";
	}

	@Override
	public void perform() {
		rentCompany.getAllModelNames().
		forEach(modelName -> io.outputLine(rentCompany.getModel(modelName)));
		
	}

}
