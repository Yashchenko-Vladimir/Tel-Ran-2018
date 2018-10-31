package telran.cars.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RentCar implements Serializable{
	String carNumber;
	long licenseId;
	LocalDate rentDate;
	int rentDays;
	
	public RentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		super();
		this.carNumber = carNumber;
		this.licenseId = licenseId;
		this.rentDate = rentDate;
		this.rentDays = rentDays;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public long getLicenseId() {
		return licenseId;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public int getRentDays() {
		return rentDays;
	}
	
	
	

}
