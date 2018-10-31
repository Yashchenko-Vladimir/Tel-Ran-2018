package telran.cars.controller.items.manager;

import java.util.Arrays;

import telran.cars.controller.items.CarsItem;
import telran.cars.dto.Car;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddCarItem extends CarsItem{

	public AddCarItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
	}

	@Override
	public String displayedName() {
		
		return "Add Car";
	}

	@Override
	public void perform() {
		
		String regNumber = io.inputString("Enter car Number");
		if(regNumber==null) return;
		String color = io.inputString("Enter color", Arrays.asList("red", "green", "gray", "white", "black", "silver"));
		if(color.isEmpty()) return;
//		String modelName = inputModelName();
		String modelName = io.inputString("Select model from the list", rentCompany.getAllModelNames());
		if(modelName == null) return;
		
		Car car = new Car(regNumber, color, modelName);
		io.outputLine(rentCompany.addCar(car));
		
	}
	

}
