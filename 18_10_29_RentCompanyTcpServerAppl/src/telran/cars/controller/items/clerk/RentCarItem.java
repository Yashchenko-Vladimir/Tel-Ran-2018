package telran.cars.controller.items.clerk;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import telran.cars.controller.items.CarsItem;
import telran.cars.dto.Car;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class RentCarItem extends CarsItem{

	public RentCarItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Rent car in RentCompany ";
	}

	@Override
	public void perform() {
		
		String carNumber = inputCarInNoUse();
		Long licenseId = io.inputLong("Input lisense ID");
		LocalDate rentDate = io.inputDate("Enter rent day", "dd/MM/yy");
		if(rentDate == null) {
			rentDate = LocalDate.now();
		}
		Integer rentDays = io.inputInteger("Input count days of rent car");
		if(rentDays == null) {
			return;
		}
		io.outputLine(rentCompany.rentCar(carNumber, licenseId, rentDate, rentDays));
		
	}

	private String inputCarInNoUse() {
		String modelName = io.inputString("Input model name");
		List<Car> cars = rentCompany.getAllCars().filter(car -> !car.isInUse()).
		filter(car -> car.getModelName().equals(modelName)).
		collect(Collectors.toList());
		
		for(Car car: cars) {
			io.outputLine(car);
		}
		String regnumber = io.inputString("Select car's reg number");
		
		return regnumber;
	}

}
