package telran.cars.controller.items.clerk;

import java.time.LocalDate;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ReturnCarItem extends CarsItem{

	public ReturnCarItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		
		return "Return car in RentCompany";
	}

	@Override
	public void perform() {
		String carNumber = io.inputString("Input car's reg number");
		if(carNumber == null)return;
		LocalDate returnDate = io.inputDate("Enter return date", "dd/MM/yy");
		if(returnDate==null)returnDate = LocalDate.now();
		Integer gasTankPercent = io.inputInteger("Input gas tank percent", 0, 100);
		if(gasTankPercent == null)return;
		Integer damages = io.inputInteger("Input car's damage", 0, 100);
		if(damages == null)return;
		
		io.outputLine(rentCompany.returnCar(carNumber, returnDate, gasTankPercent, damages));
		
	}

}
