package telran.cars.controller.items.manager;

import java.util.Arrays;

import telran.cars.controller.items.CarsItem;
import telran.cars.dto.Car;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddCarItem extends CarsItem {

	public AddCarItem(InputOutput io, IRentCompany company) {
		super(io, company);
		
	}

	@Override
	public String displayedName() {
		return "Add car data";
	}

	@Override
	public void perform() {
		String regNumber=io.inputString("Enter car number");
		if(regNumber==null) return;
		String color=io.inputString("Enter color",
				Arrays.asList("red","white","black","gray","Silver","yllow"));
		if(color==null) return;
		String modelName=inputModelName();
		if(modelName==null)return;
		Car car=new Car(regNumber, color, modelName);
		io.outputLine(company.addCar(car));
		
	}

}
