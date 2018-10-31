package telran.cars.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.cars.dto.Car;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.model.IRentCompany;
import telran.cars.model.RentCompanyEmbedded;

class RentCompanyStatTest {
	IRentCompany rentCompany;

	@BeforeEach
	void setUp() throws Exception {
		setUpStatistics();
	}

	@Test
	void getModelProfitTest() {
		assertEquals(1500.0, rentCompany.getModelProfit("BMW"));
		assertEquals(1500.0, rentCompany.getModelProfit("Mersedes"));
		assertEquals(750.0, rentCompany.getModelProfit("Niva"));
		
	}
	
	@Test
	void getMostPopularModelNamesTest() {
		
		List<String> expected = Arrays.asList("Niva");
		assertEquals(expected, rentCompany.getMostPopularModelNames());
		
	}
	
	@Test
	void getMostProfitModelNamesTest() {
		
		List<String> expected = Arrays.asList("BMW", "Mersedes");
		assertEquals(expected, rentCompany.getMostProfitModelNames());
		
	}
	
	@Test
	void test() {
		
		rentCompany.getAllRecords().forEach(x -> System.out.println(x.getCarNumber() +" " + x.getCost()));
		
	}
	
	private void setUpStatistics() {
		rentCompany = new RentCompanyEmbedded();
	 	  
	Model bmwModel = new Model("BMW", 60, "company1", "country1", 100);
	Model mersedesModel = new Model("Mersedes", 60, "company1", "country1", 150);
	Model nivaModel = new Model("Niva", 50, "company1", "country1", 30);
	
	Car bmwCar = new Car("12345", "black", "BMW");
	Car mersedesCar = new Car("23456", "red", "Mersedes");
	Car nivaCar = new Car("34567", "green", "Niva");
	
	Driver driver1 = new Driver(111l, "Petr", 1990, "(050) 1234567");
	Driver driver2 = new Driver(222l, "Masha", 1988, "(055) 1234321");
	Driver driver3 = new Driver(333l, "Kolya", 2003, "(053) 4445555");
	
	rentCompany.addModel(bmwModel); rentCompany.addModel(mersedesModel); rentCompany.addModel(nivaModel);
	rentCompany.addCar(bmwCar); rentCompany.addCar(mersedesCar); rentCompany.addCar(nivaCar);
	rentCompany.addDriver(driver1); rentCompany.addDriver(driver2); rentCompany.addDriver(driver3);
	
	rentCompany.rentCar(bmwCar.getRegNumber(), driver1.getLicenseId(), LocalDate.of(2018, 9, 1), 5);
	rentCompany.returnCar(bmwCar.getRegNumber(), LocalDate.of(2018, 9, 6), 100, 0);
	rentCompany.rentCar(bmwCar.getRegNumber(), driver2.getLicenseId(), LocalDate.of(2018, 9, 7), 5);
	rentCompany.returnCar(bmwCar.getRegNumber(), LocalDate.of(2018, 9, 12), 100, 0);
	rentCompany.rentCar(bmwCar.getRegNumber(), driver3.getLicenseId(), LocalDate.of(2018, 9, 15), 5);
	rentCompany.returnCar(bmwCar.getRegNumber(), LocalDate.of(2018, 9, 20), 100, 0);
	rentCompany.rentCar(mersedesCar.getRegNumber(), driver3.getLicenseId(), LocalDate.of(2018, 9, 1), 5);
	rentCompany.returnCar(mersedesCar.getRegNumber(), LocalDate.of(2018, 9, 6), 100, 0);
	rentCompany.rentCar(mersedesCar.getRegNumber(), driver1.getLicenseId(), LocalDate.of(2018, 9, 20), 5);
	rentCompany.returnCar(mersedesCar.getRegNumber(), LocalDate.of(2018, 9, 25), 100, 0);
	rentCompany.rentCar(nivaCar.getRegNumber(), driver1.getLicenseId(), LocalDate.of(2018, 9, 10), 5);
	rentCompany.returnCar(nivaCar.getRegNumber(), LocalDate.of(2018, 9, 15), 100, 0);
	rentCompany.rentCar(nivaCar.getRegNumber(), driver2.getLicenseId(), LocalDate.of(2018, 9, 18), 5);
	rentCompany.returnCar(nivaCar.getRegNumber(), LocalDate.of(2018, 9, 23), 100, 0);
	rentCompany.rentCar(nivaCar.getRegNumber(), driver2.getLicenseId(), LocalDate.of(2018, 9, 25), 5);
	rentCompany.returnCar(nivaCar.getRegNumber(), LocalDate.of(2018, 9, 30), 100, 0);
	rentCompany.rentCar(nivaCar.getRegNumber(), driver1.getLicenseId(), LocalDate.of(2018, 10, 1), 5);
	rentCompany.returnCar(nivaCar.getRegNumber(), LocalDate.of(2018, 10, 6), 100, 0);
	rentCompany.rentCar(nivaCar.getRegNumber(), driver3.getLicenseId(), LocalDate.of(2018, 10, 10), 5);
	rentCompany.returnCar(nivaCar.getRegNumber(), LocalDate.of(2018, 10, 15), 100, 0);
	
	
	
	
	
	
		}

}
