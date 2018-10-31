package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class getModelNameItem extends CarsItem{

	public getModelNameItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		
		return "Get model";
	}

	@Override
	public void perform() {
		
		String strModel = io.inputString("Enter model name", rentCompany.getAllModelNames());
		io.outputLine(rentCompany.getModel(strModel));
		
	}

}
