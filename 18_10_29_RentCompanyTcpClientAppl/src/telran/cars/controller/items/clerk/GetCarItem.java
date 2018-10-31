package telran.cars.controller.items.clerk;

import java.util.List;
import java.util.stream.Collectors;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetCarItem extends CarsItem{

	public GetCarItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		
	}

	@Override
	public String displayedName() {
		
		return "Get Car";
	}

	@Override
	public void perform() {
		List<String> allCarRegNumber = rentCompany.getAllCars().
				map(car -> car.getRegNumber()).collect(Collectors.toList());
		String regNumber = io.inputString("Input car's reg. number", allCarRegNumber);
		io.outputLine(rentCompany.getCar(regNumber));
		
	}

}
