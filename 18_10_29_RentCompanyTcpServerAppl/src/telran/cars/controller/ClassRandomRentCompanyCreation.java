package telran.cars.controller;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.HashMap;
import java.util.List;

import telran.cars.dto.Car;
import telran.cars.model.IRentCompany;
import telran.cars.model.RentCompanyEmbedded;
import telran.utils.Persistable;

public class ClassRandomRentCompanyCreation {
	private static final LocalDate DATE_ONE = null;

	private static final ChronoLocalDate LAST_DATE = null;

	static IRentCompany rentCompany = new RentCompanyEmbedded();
	
	private HashMap<LocalDate, List<Car>> shouldretuned = new HashMap<>();
	
	public static void main(String[] args) {
		
		
		fillModelsCarsDrivers(rentCompany);
		RentcompanyImmitation(rentCompany);
		saveToFile(rentCompany);
		System.out.println(rentCompany.getAllRecords().count());
		
		rentCompany.getAllRecords().forEach(driver -> System.out.println(driver));

	}

	private static void saveToFile(IRentCompany rentCompany) {
		if(rentCompany instanceof Persistable) {
			((Persistable) rentCompany).saveToFile("company.data");
		}
		
	}

	private static void RentcompanyImmitation(IRentCompany rentCompany) {
		LocalDate current = DATE_ONE;
		while(current.isAfter(LAST_DATE)) {
			rentCarsDay(current);
			returnCarsDay(current);
			current = current.plusDays(1);
			
			
		}
		
	}

	private static void returnCarsDay(LocalDate current) {
		// TODO Auto-generated method stub
		
	}

	private static void rentCarsDay(LocalDate current) {
		int nRentsDay = getRentsToday();
		for(int i = 0; i < nRentsDay; i++) {
			rentCar(current);
		}
		
	}

	private static int getRentsToday() {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void rentCar(LocalDate current) {
		String carNumber;
	
		
	}

	private static void fillModelsCarsDrivers(IRentCompany rentCompany) {
		// TODO Auto-generated method stub
		
	}
}
