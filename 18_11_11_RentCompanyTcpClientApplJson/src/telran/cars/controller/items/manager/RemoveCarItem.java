package telran.cars.controller.items.manager;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class RemoveCarItem extends CarsItem {

	public RemoveCarItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Remove car";
	}

	@Override
	public void perform() {
		String carNumber=io.inputString("Enter number car");
		if(carNumber==null)
			return;
		io.outputLine(company.removeCar
				(carNumber));
	}

}
