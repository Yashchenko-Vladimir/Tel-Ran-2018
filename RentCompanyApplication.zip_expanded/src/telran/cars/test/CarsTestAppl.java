package telran.cars.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RentRecord;
import telran.cars.model.IRentCompany;
import telran.cars.model.RentCompanyEmbedded;

class CarsTestAppl {
	private static final LocalDate RENT_DATE_1 = LocalDate.of(2018, 10, 15);
	private static final LocalDate RETURN_DATE_TRUE = LocalDate.of(2018, 10, 20);
	private static final LocalDate RETURN_DATE_WRONG = LocalDate.of(2018, 10, 20);
	private static final int RENT_DAYS_1 = 5;
	private static final String REG_NUMBER_1 = "regNumber_1";
	private static final long DRIVER_LICENSE_1 = 1111111L;
	private static final String DRIVER_NAME_1 = "driverName_1";
	private static final int PRICE_1 = 150;
	private static final int GAS_PRICE_1 = 50;
	private static final int GAS_TANK_1 = 50;
	
	
	IRentCompany rentCompany;
//	String modelName = "modelName_1";
//	int price1 = 50;
//	int gasTank1 = 30;
//	private String company1 = "company_1";
//	private String country1 = "country_1";
//	private int priceDay1 = 150;
	
	
	Model model1 = new Model("modelName_1", GAS_TANK_1, "company_1", "country_1", PRICE_1);
	Model model2 = new Model("modelName_2", 60, "company_2", "country_2", 150);
	Car car1 = new Car(REG_NUMBER_1, "color1", model1.getModelName());
	Car car2 = new Car("regNumber_2", "color2", model1.getModelName());
	Driver driver1 = new Driver(DRIVER_LICENSE_1, DRIVER_NAME_1, 1998, "(050) 1234567");
	Driver driver2 = new Driver(2222L, "driverName_2", 1976, "(050) 7654321");
	int gasPrice;
	int finePercent;
	
//	RentCompanyEmbedded models; 
//	Model model1 = new Model("Fiiat_Bravo", 35, "EvCars", "Israel", 100);
//	Model model2 = new Model("BMW_X5", 60, "EvCars", "Israel", 250);
//	Model model3 = new Model("Ford_Focus", 35, "EvCars", "Israel", 175);

	@BeforeEach
	void setUp() throws Exception {
		
		rentCompany = new RentCompanyEmbedded();
		rentCompany.addModel(model1);
		rentCompany.addCar(car1);
		rentCompany.addDriver(driver1);
		rentCompany.rentCar(REG_NUMBER_1, DRIVER_LICENSE_1, RENT_DATE_1, RENT_DAYS_1);
		gasPrice = rentCompany.getGasPrice();
		 finePercent = rentCompany.getFinePercent();
	}
	@Test
	void addCarTest() {
		assertEquals(rentCompany.addCar(car2), CarsReturnCode.OK);
		assertEquals(rentCompany.addCar(car1), CarsReturnCode.CAR_EXISTS);
	}
	
	@Test
	void getCarTest() {
		assertEquals(rentCompany.getCar("Not_exsist"), null);
		assertEquals(rentCompany.getCar(car1.getRegNumber()), car1);
	}
	@Test
	void addDriverTest() {
		assertEquals(rentCompany.addDriver(driver2), CarsReturnCode.OK);
		assertEquals(rentCompany.addDriver(driver2), CarsReturnCode.DRIVER_EXISTS);
	}
	
	@Test
	void getDriverTest() {
		assertEquals(rentCompany.getDriver(0), null);
		assertEquals(rentCompany.getDriver(driver1.getLicenseId()), driver1);
	}

	@Test
	void addModelTest() {
		assertEquals(rentCompany.addModel(model2), CarsReturnCode.OK);
		assertEquals(rentCompany.addModel(model2), CarsReturnCode.MODEL_EXISTS);
	}
	
	@Test
	void getModelTest() {
		assertEquals(rentCompany.getModel("Not_exsist"), null);
		assertEquals(rentCompany.getModel(model1.getModelName()), model1);
	}
	
	@Test 
	void rentCarTest() {
		LocalDate rentDay = LocalDate.now();
		
		assertEquals(rentCompany.rentCar(car1.getRegNumber(), driver1.getLicenseId(), rentDay, 4), CarsReturnCode.OK);
		assertEquals(rentCompany.rentCar(car1.getRegNumber(), driver1.getLicenseId(), rentDay, 4), CarsReturnCode.CAR_IN_USE);
		assertEquals(rentCompany.rentCar(car1.getRegNumber(), driver2.getLicenseId(), rentDay, 4), CarsReturnCode.NO_DRIVER);
		
		assertEquals(rentCompany.rentCar(car2.getRegNumber(), driver1.getLicenseId(), rentDay, 4), CarsReturnCode.NO_CAR);
		rentCompany.addCar(car2);
		car2.setFlRemoved(true);
		assertEquals(rentCompany.rentCar(car2.getRegNumber(), driver1.getLicenseId(), rentDay, 4), CarsReturnCode.NO_CAR);
		
	}
	
	
	@Test
	void returnCarFullTankOnTime() {
//		RentRecord  rentRecord =   rentCompany.getAllRecords().filter(x -> (REG_NUMBER_1.equals(x.getCarNumber()) 
//				&& (DRIVER_LICENSE_1 == x.getLicenseId())&&( RENT_DATE_1.equals(x.getReturnDate())).findFirst().get();
		
	
		assertEquals(CarsReturnCode.CAR_NOT_RENTED, rentCompany.returnCar("regNumber_2", RETURN_DATE_TRUE, 100, 0));
		assertEquals(CarsReturnCode.RETURN_DATE_WRONG, rentCompany.returnCar(REG_NUMBER_1,  RETURN_DATE_WRONG, 100, 0));
		assertEquals(CarsReturnCode.OK, rentCompany.returnCar(REG_NUMBER_1, RETURN_DATE_TRUE, 100, 0));
		
		RentRecord actual = rentCompany.getAllRecords().findFirst().get();
		
		RentRecord expected =
				new RentRecord(DRIVER_LICENSE_1, REG_NUMBER_1, RENT_DATE_1, RENT_DAYS_1);
				expected.setReturnDate(RETURN_DATE_TRUE);
				expected.setGasTankPercent(100);
				expected.setDamages(0);
				expected.setCost(getCostFullTankOnTime());
				assertEquals(expected, actual);
//		RentRecord expected = new RentRecord(DRIVER_LICENSE_1, 
	
		
//		assertEquals(expected, actual);
		
	}
	@Test
	public void getAllRecords() {
		int countOfRecords = 1;
		assertEquals(countOfRecords, rentCompany.getAllRecords().count());
		RentRecord actual = rentCompany.getAllRecords().findFirst().get();
		
		assertEquals(actual.getCarNumber(), REG_NUMBER_1);
		assertEquals(actual.getLicenseId(), DRIVER_LICENSE_1);
		assertEquals(actual.getReturnDate(), RENT_DATE_1);
		assertEquals(actual.getRentDate(), RENT_DAYS_1);
		
	}
	
	@Test
	void returnCarEmptyTankOnTime() {
		
		RentRecord actual = rentCompany.getAllRecords().findFirst().get();
		
		assertEquals(getCostFullTankOnTime()+ GAS_TANK_1* GAS_PRICE_1, actual.getCost());
		assertEquals(0, actual.getGasTankPercent());
		
	}
	private int getCostFullTankOnTime() {
		return PRICE_1*RENT_DAYS_1;
	}
	
	@Test
	void returnCarHalfTankDelay() {
//		
//		assertEquals(CarsReturnCode.OK, rentCompany.rentCar(REG_NUMBER_1, RETURN_DATE_DELAY, 50, 0));
//		RentRecord actual = rentCompany.getAllRecords().findFirst().get();
//		float cost = (float) (getCostFullTankOnTime()+ 0.5 * GAS_TANK_1* GAS_PRICE_1 + DELAY_DAYS*(PRICE_1*0.15);
//		assertEquals(cost, actual.getCost());
		
//		RentRecord actual = rentCompany.getAllRecords().findFirst().get();
//		assertFalse(actual.getReturnDate().equals(actual.getRentDate().plusDays(actual.getRentDays())));
//		long days = ChronoUnit.DAYS.between(actual.getRentDate(), actual.getReturnDate());
//		float cost = (float) (getCostFullTankOnTime()+ 0.5 * GAS_TANK_1* GAS_PRICE_1 + days*PRICE_1*0.15);
//		assertEquals(cost, actual.getCost());
	}
	@Test 
	void returnCarWithDamages20() {
		
		
	}
	@Test
	void returnCarWithDamages50() {
		
	}
	

}
