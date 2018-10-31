package telran.cars.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Clear implements Serializable{
	LocalDate currentDate;
	int days;
	public Clear(LocalDate currentDate, int days) {
		super();
		this.currentDate = currentDate;
		this.days = days;
	}
	public LocalDate getCurrentDate() {
		return currentDate;
	}
	public int getDays() {
		return days;
	}
	
	
}
