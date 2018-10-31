package telran.cars.controller.items.manager;

import java.time.LocalDate;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ClearItem extends CarsItem{

	public ClearItem(InputOutput io,
			IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Clear company";
	}

	@Override
	public void perform() {
		LocalDate 	currentDate = 
		io.inputDate("Input date", "dd-MM-yy");
		if(currentDate==null)
			currentDate=LocalDate.now();
		Integer 	days 		=
				io.inputInteger("Input days");
		if (days==null)
			return;
		company.clear(currentDate, days)
		.forEach(io::outputLine);
	}
}
