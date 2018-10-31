package telran.cars.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.cars.model.*;
import telran.cars.dto.*;

class CarsTestAppl2 {

	private static final String REG_NUMBER1 = "123";
	private static final String COLOR1 = "green";
	private static final long LICENSEID1 = 123;
	private static final String COMPANY1 = "company1";
	private static final String NAME1 = "Moshe";
	private static final int BIRTH_YEAR1 = 1970;
	private static final String PHONE1 = "1111111";
	private static final LocalDate RENT_DATE1=
			LocalDate.of(2018, 10, 15);
	
	private static final int DELAY_DAYS = 2;
	private static final int RENT_DAYS1 = 5;
	private static final LocalDate RETURN_DATE_TRUE =
			RENT_DATE1.plusDays(RENT_DAYS1);
	private static final LocalDate RETURN_DATE_DELAY =
			RETURN_DATE_TRUE.plusDays(DELAY_DAYS);
	private static final LocalDate RETURN_DATE_WRONG =
			RENT_DATE1.minusDays(1);
	
	
	
	IRentCompany rentCompany;
	String modelName1 = "modelName_1";
	static final int PRICE1 = 100;
	int gasPrice;
	static final int GAS_TANK1 = 30;
	private static final int DAMAGE20 = 20;
	private static final int DAMAGE50 = 50;
	private static final LocalDate ld_2018_11_15 = LocalDate.of(2018, 11, 15);
	
	
	
	private String company = "company1";
	private String country = "contry1";
	Model model1 = new Model(modelName1, GAS_TANK1, company, country, PRICE1);
	private Car car1 = new Car(REG_NUMBER1, COLOR1, modelName1);
	private Driver driver1 = new Driver(LICENSEID1, NAME1, BIRTH_YEAR1, PHONE1);
	private int finePercent;

	@BeforeEach
	void setUp() throws Exception {
		rentCompany = new RentCompanyEmbedded();
		rentCompany.addModel(model1);
		rentCompany.addCar(car1);
		rentCompany.addDriver(driver1);
		rentCompany.rentCar
		(REG_NUMBER1, LICENSEID1, RENT_DATE1, RENT_DAYS1);
		gasPrice = rentCompany.getGasPrice();
		finePercent = rentCompany.getFinePercent();

	}

	@Test
	void ModelsTest() {
		int gasTank = 50;
		int price = 50;
		Model model_1 = new Model("modelName_2", gasTank, "company_1", "country_1", price);
		Model model_2 = new Model("modelName_3", gasTank, "company_1", "country_1", price);

		assertEquals(CarsReturnCode.OK, rentCompany.addModel(model_1));
		assertEquals(CarsReturnCode.MODEL_EXISTS, rentCompany.addModel(model_1));

		assertEquals(CarsReturnCode.OK, rentCompany.addModel(model_2));

		assertEquals(null, rentCompany.getModel("NotExistingModel"));
		assertEquals(model_1, rentCompany.getModel("modelName_2"));
	}

	@Test
	void CarsTest() {
		String color = "color1";
		Car car1 = new Car("111 111 111", color, modelName1);
		Car car2 = new Car("111 111 112", color, modelName1 + "notExisted");

		assertEquals(CarsReturnCode.OK, rentCompany.addCar(car1));
		assertEquals(CarsReturnCode.CAR_EXISTS, rentCompany.addCar(car1));
		assertEquals(CarsReturnCode.NO_MODEL, rentCompany.addCar(car2));

		assertEquals(car1, rentCompany.getCar("111 111 111"));
	}

	@Test
	void DriversTest() {
		Driver driver = new Driver(1231l, "name1", 1970, "phone1");
		assertEquals(CarsReturnCode.OK, rentCompany.addDriver(driver));
		assertEquals(CarsReturnCode.DRIVER_EXISTS, rentCompany.addDriver(driver));
	}

	@Test
	void rentTest() {
		LocalDate ld_2018_10_14 = LocalDate.of(2018, 10, 14);
		int rentDays = 10;
		Car car_2 = new Car("555555", null, null);
		Driver driver_2 = new Driver(1234567, null, 0, null);
		LocalDate ld_2018_10_15 = LocalDate.of(2018, 10, 15);
		LocalDate ld_2019_10_14 = LocalDate.of(2019, 10, 14);
		Car car2=new Car("124","red",modelName1);
		rentCompany.addCar(car2);
		car2.setFlRemoved(true);
//		assertEquals(CarsReturnCode.OK,
//		rentCompany.rentCar(car1.getRegNumber(),
//				driver1.getLicenseId(), ld_2018_10_14, rentDays));
		

		assertEquals(CarsReturnCode.CAR_IN_USE,
	    rentCompany.rentCar(car1.getRegNumber(),
	    		driver1.getLicenseId(), ld_2018_10_15, rentDays));
//car_2 doesn't exist
		assertEquals(CarsReturnCode.NO_CAR,
				rentCompany.rentCar(car_2.getRegNumber(),
			driver1.getLicenseId(), ld_2019_10_14, rentDays));
	//car2 exists but in the state removed;
		assertEquals(CarsReturnCode.NO_CAR,
				rentCompany.rentCar(car2.getRegNumber(),
			driver1.getLicenseId(), ld_2019_10_14, rentDays));

		assertEquals(CarsReturnCode.NO_DRIVER,
				rentCompany.rentCar(car1.getRegNumber(),
		driver_2.getLicenseId(), ld_2019_10_14, rentDays));
	}
	
	@Test
	void returnCarFullTankOnTime() {
		
		returnCodesTest();
		assertEquals(CarsReturnCode.OK,
				rentCompany.returnCar(REG_NUMBER1,
						RETURN_DATE_TRUE, 100, 0));
		getAllRecords();
		RentRecord actual =  rentCompany.getAllRecords()
				.findFirst().get();
		RentRecord expected =
		new RentRecord(LICENSEID1, REG_NUMBER1, RENT_DATE1, RENT_DAYS1);
		expected.setReturnDate(RETURN_DATE_TRUE);
		expected.setGasTankPercent(100);
		expected.setDamages(0);
		expected.setCost(getCostFullTankOnTime());
		assertEquals(expected, actual);
	}

	private int getCostFullTankOnTime() {
		return PRICE1*RENT_DAYS1;
	}

	private void returnCodesTest() {
		assertEquals(CarsReturnCode.CAR_NOT_RENTED,
				rentCompany.returnCar(REG_NUMBER1 + "1",
						RETURN_DATE_TRUE, 100, 0));
		assertEquals(CarsReturnCode.RETURN_DATE_WRONG,
			rentCompany.returnCar
			(REG_NUMBER1, RETURN_DATE_WRONG, 100, 0));
	}
	@Test
	public void getAllRecords() {
		List<RentRecord> allRecords =  rentCompany.getAllRecords()
				.collect(Collectors.toList());
				
		  assertEquals(1,allRecords.size());
		  assertEquals(allRecords.get(0).getCarNumber()
				  ,REG_NUMBER1);
		  assertEquals(allRecords.get(0).getLicenseId(),LICENSEID1);
		  assertEquals(allRecords.get(0).getRentDate(),RENT_DATE1);
		  assertEquals(allRecords.get(0).getRentDays(),RENT_DAYS1);
		
	}
	@Test
	void returnCarEmptyTankOnTime() {
		assertEquals(CarsReturnCode.OK,
				rentCompany.returnCar
				(REG_NUMBER1, RETURN_DATE_TRUE, 0, 0));
		RentRecord actual = rentCompany.getAllRecords()
				.findFirst().get();
		assertEquals(getCostFullTankOnTime() +
				gasPrice * GAS_TANK1,actual.getCost());
		assertEquals(0,actual.getGasTankPercent());
		
	}
	@Test
	void returnCarHalfTankDelay() {
		assertEquals(CarsReturnCode.OK,
				rentCompany.returnCar
				(REG_NUMBER1, RETURN_DATE_DELAY, 50, 0));
		RentRecord actual = rentCompany.getAllRecords()
				.findFirst().get();
		assertEquals(getCostFullTankOnTime() +
				gasPrice * GAS_TANK1/2+DELAY_DAYS*
				(PRICE1+PRICE1*finePercent/100),actual.getCost());
	}
	@Test
	void returnCarWithDamages20() {
		
			assertEquals(CarsReturnCode.OK, rentCompany.returnCar(REG_NUMBER1, RETURN_DATE_DELAY, 100, DAMAGE20));
			RentRecord actual = rentCompany.getAllRecords().findFirst().get();
			assertEquals(DAMAGE20,	actual.getDamages());
			assertEquals(State.BAD, car1.getState());
			assertFalse(car1.isFlRemoved());
		
	}
	@Test
	void returnCarWithDamages50() {
		assertEquals(CarsReturnCode.OK, rentCompany.returnCar(REG_NUMBER1, RETURN_DATE_DELAY, 100, DAMAGE50));
		RentRecord actual = rentCompany.getAllRecords().findFirst().get();
		assertEquals(DAMAGE50,	actual.getDamages());
		assertEquals(State.BAD, car1.getState());
		assertTrue(car1.isFlRemoved());
	}
	
	@Test
	void getAllCarsTest() {
//		List<Car> cars = rentCompany.getAllCars().collect(Collectors.toList());
		assertEquals(1, rentCompany.getAllCars().count());
		Car car2 = new Car("regNumber_2", "color2", model1.getModelName());
		rentCompany.addCar(car2);
		assertEquals(2, rentCompany.getAllCars().count());
	}
	
	@Test
	void clearTest() {
		assertEquals(0, rentCompany.clear(RENT_DATE1, 0).size());
//		assertEquals(0, rentCompany.clear(RENT_DATE1.plusDays(3), 2).size());
		assertEquals(1, rentCompany.getAllRecords().count());
		
		rentCompany.returnCar(car1.getRegNumber(), RETURN_DATE_TRUE, 100, DAMAGE50);
		
		
		 List<Car> list = rentCompany.clear(RETURN_DATE_TRUE.plusDays(10), 5);
		  
		  assertEquals(1, list.size());
		  assertEquals(car1, list.get(0));
		  assertEquals(0, rentCompany.getAllRecords().count());
		  assertNull(rentCompany.getCar(REG_NUMBER1));
		
//		
//		Car car2 = new Car("regNumber_2", "color2", model1.getModelName());
//		
//		rentCompany.addCar(car2);
//		rentCompany.rentCar(car2.getRegNumber(), LICENSEID1, LocalDate.of(2017, 10, 10), 3);
//		rentCompany.returnCar(car2.getRegNumber(), LocalDate.of(2017, 10, 13), 100, 50);
//		assertEquals(1, rentCompany.clear(ld_2018_11_15, 100).size());
//		assertEquals(1, rentCompany.getAllCars().collect(Collectors.toList()).size());
//		assertEquals(1, rentCompany.getAllRecords().collect(Collectors.toList()).size());
		
	}
	
	@Test
	void getCarDriversTest() {
		assertEquals(1, rentCompany.getCarDrivers(REG_NUMBER1).size());
		Driver driver2 = new Driver(2222L, "driverName_2", 1976, "(050) 7654321");
		rentCompany.addDriver(driver2);
		rentCompany.rentCar(REG_NUMBER1, driver2.getLicenseId(), RENT_DATE1.plusDays(RENT_DAYS1 + 1), RENT_DAYS1);
		rentCompany.returnCar(REG_NUMBER1, RETURN_DATE_TRUE.plusDays(RENT_DAYS1+1), 100, DAMAGE20);
		assertEquals(2, rentCompany.getCarDrivers(REG_NUMBER1));
		
	}
	
	@Test
	void getDriverCarsTest() {
//		assertEquals(1, rentCompany.);
	}
	
	private void setUpStatistics() {
		Model model10 = new Model();
	}
	
	

}






