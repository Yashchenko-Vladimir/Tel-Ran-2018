package telran.cars.controller.items.technician;

import java.time.LocalDate;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ReturnRecordsByRange extends CarsItem{

	public ReturnRecordsByRange(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
	}

	@Override
	public String displayedName() {
		return "Display records by period";
	}

	@Override
	public void perform() {
		LocalDate from = io.inputDate("Input first day", "dd/MM/yy");
		if(from == null)return;
		LocalDate to = io.inputDate("Input second day", "dd/MM/yy");
		if(to == null)return;
		if(from.isAfter(to)) return;
		rentCompany.getReturnedRecordsByDates(from, to).forEach(io::outputLine);
		
	}

}
