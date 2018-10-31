package telran.cars.controller.items.manager;

import telran.cars.controller.items.CarsItem;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Model;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddModelItem extends CarsItem{

	public AddModelItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		
	}

	@Override
	public String displayedName() {
		return "Add Model";
	}

	@Override
	public void perform() {
		
		
		Model model = io.inputObject("Enter model to add list in the format:<Model name>#<gasTanke>#<Name company>#"
				+ "<country>#<price in day>", AddModelItem::getModelFromString);
		
		if(model != null) {
			if(rentCompany.getAllModelNames().contains(model.getModelName())) {
				io.outputLine(CarsReturnCode.MODEL_EXISTS);
			} else {
				rentCompany.addModel(model);
				io.outputLine(CarsReturnCode.OK);
			}
		}
		
	}

	private static Model getModelFromString(String strModel) {
		try {
			
			String[] tokens = strModel.split("#");
			String modelName = tokens[0];
			int gasTank = Integer.parseInt(tokens[1]);
			String companyName = tokens[2];
			String country = tokens[3];
			int priceDay = Integer.parseInt(tokens[4]);
			return new Model(modelName, gasTank, companyName, country, priceDay);
		}catch (Exception e) {
			return null;
		}
		
	}
	

}
