package telran.cars.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ReturnCar implements Serializable{
	String carNumber;
	LocalDate returnDate;
	int gasTankPersecent;
	int damages;
	public ReturnCar(String carNumber, LocalDate returnDate, int gasTankPersecent, int damages) {
		super();
		this.carNumber = carNumber;
		this.returnDate = returnDate;
		this.gasTankPersecent = gasTankPersecent;
		this.damages = damages;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public int getGasTankPersecent() {
		return gasTankPersecent;
	}
	public int getDamages() {
		return damages;
	}
	
	
}
