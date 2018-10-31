package telran.cars.controller.items.technician;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetAllRecordsItem extends CarsItem {

	public GetAllRecordsItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		
		return "Get all records";
	}

	@Override
	public void perform() {
		io.outputLine("----------");
		rentCompany.getAllRecords().forEach(io :: outputLine);
		
	}

}
