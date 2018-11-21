package telran.cars.controller.items.clerk;

import java.time.LocalDate;
import java.util.stream.Collectors;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class RentCarItem extends CarsItem {

	public RentCarItem(InputOutput io, IRentCompany company) {
		super(io, company);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Add rent record";
	}

	@Override
	public void perform() {
		String carNumber = 
		io.inputString("Enter car to rent reg number");
		if (carNumber == null||carNumber.isEmpty()) {
			return;
		}
		Long licenseId = io.inputLong("Input license ID");
		if (licenseId==null) {
			return;
		}
		LocalDate rentDate = io.inputDate("Enter rent date",
				"dd/MM/yy");
		if(rentDate==null)
			rentDate=LocalDate.now();
		Integer rentDays = io.inputInteger("Enter rent days");
		if (rentDays == null) return;
		io.outputLine
		(company.rentCar(carNumber, licenseId, rentDate, rentDays));
		
		
	}

}
