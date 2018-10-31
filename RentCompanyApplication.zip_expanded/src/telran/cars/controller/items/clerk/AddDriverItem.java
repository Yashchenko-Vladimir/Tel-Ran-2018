package telran.cars.controller.items.clerk;

import java.time.LocalDate;

import telran.cars.controller.items.CarsItem;
import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddDriverItem extends CarsItem{

	public AddDriverItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		
	}

	@Override
	public String displayedName() {
		
		return "Add Driver";
	}

	@Override
	public void perform() {
		Long licenseId = io.inputLong("Input license ID");
		if(licenseId == null) {
			return;
		}
		String name = io.inputString("Input driver's name");
		int min = LocalDate.now().minusYears(70).getYear();
		int max = LocalDate.now().minusYears(18).getYear();
		Integer birthYear = io.inputInteger("Input birth day",min, max);
		if(birthYear == null) {
			return;
		}
//		String regex = "(\\+975|0)[02-8]{2}-?\\d{7}";
		String regex = "\\d{10}";
		String phone = io.inputObject("Input phone number", s-> s.matches(regex)?s:null );
		if(phone == null) {
			return;
		}
		Driver driver = new Driver(licenseId, name, birthYear, phone);
		io.outputLine(rentCompany.addDriver(driver ));
		
	}

}
