package telran.cars.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ReturnCar implements Serializable{
	String carNumber;
	LocalDate returnDate;
	int gasTankPercent;
	int damages;
	public ReturnCar(String carNumber, LocalDate returnDate, int gasTankPercent, int damages) {
		super();
		this.carNumber = carNumber;
		this.returnDate = returnDate;
		this.gasTankPercent = gasTankPercent;
		this.damages = damages;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public int getGasTankPercent() {
		return gasTankPercent;
	}
	public int getDamages() {
		return damages;
	}
	
	
}
