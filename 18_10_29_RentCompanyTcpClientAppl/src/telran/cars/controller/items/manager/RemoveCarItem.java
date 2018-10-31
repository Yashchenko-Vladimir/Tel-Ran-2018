package telran.cars.controller.items.manager;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class RemoveCarItem extends CarsItem{

	public RemoveCarItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		}

	@Override
	public String displayedName() {
		
		return "Remove car";
	}

	@Override
	public void perform() {
		io.outputLine(rentCompany.removeCar(io.inputString("Enter  car's ger number")));
		
	}

}
