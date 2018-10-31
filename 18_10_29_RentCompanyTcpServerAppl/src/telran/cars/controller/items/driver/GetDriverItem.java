package telran.cars.controller.items.driver;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import telran.cars.controller.items.CarsItem;
import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetDriverItem extends CarsItem {

	public GetDriverItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		
	}

	@Override
	public String displayedName() {
		return "Get driver";
	}

	@Override
	public void perform() {
		List<String> allLicenseIdDriver = rentCompany.getAllDrivers().
				map(driver -> String.valueOf(driver.getLicenseId()))
				.collect(Collectors.toList());
				
		String licenseId = io.inputString("Input license ID from list", allLicenseIdDriver);
		io.outputLine(rentCompany.getDriver(Long.parseLong(licenseId)));
		
	}

}
