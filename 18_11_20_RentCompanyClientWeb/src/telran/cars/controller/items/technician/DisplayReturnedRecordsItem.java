package telran.cars.controller.items.technician;

import java.time.LocalDate;

import telran.view.InputOutput;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;

public class DisplayReturnedRecordsItem extends CarsItem {

	public DisplayReturnedRecordsItem(InputOutput io, IRentCompany company) {
		super(io, company);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Display Records by Dates";
	}

	@Override
	public void perform() {
		
		LocalDate from = io.inputDate
				("FROM", "dd/MM/yyyy");
		if(from==null)
			return;
		LocalDate to =
				io.inputDate("TO", "dd/MM/yyyy");
		if(to==null)
			to=LocalDate.now();
		company.getReturnedRecordsByDates(from, to).forEach(io::outputLine);

	}

	

}
