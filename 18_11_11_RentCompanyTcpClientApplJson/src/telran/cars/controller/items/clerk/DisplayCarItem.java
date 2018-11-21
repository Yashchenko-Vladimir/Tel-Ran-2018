package telran.cars.controller.items.clerk;


import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayCarItem extends CarsItem {

	public DisplayCarItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Display Car Data";
	}

	@Override
	public void perform() {
		String carNumber = io.inputString("Input car number to get car");
		if(carNumber==null) return;
		io.output(company.getCar(carNumber));
	}

}
