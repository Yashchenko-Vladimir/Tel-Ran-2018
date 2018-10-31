package telran.cars.controller.items.clerk;

import java.time.LocalDate;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.*;

public class ReturnCarItem extends CarsItem{
	public ReturnCarItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		return "Return a car";
	}

	@Override
	public void perform() {
		String carNumber = io.inputString("Input car number");
		if(carNumber==null)
			return;
		Integer tank = io.inputInteger("Input gas tank %",0,100);
		if(tank==null)
			return;
		Integer damages = io.inputInteger("Input damages %",0,100);
		
		if ( damages!=null) {
				
			LocalDate returnDate = io.inputDate("Enterreturne date", "dd/MM/yy");
			if(returnDate==null)
				returnDate=LocalDate.now();
			io.outputLine(company.returnCar(carNumber, returnDate, tank, damages));
		
		}
		
	}
}
