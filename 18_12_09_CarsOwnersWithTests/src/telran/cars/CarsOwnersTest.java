	package telran.cars;
	
	import static org.junit.jupiter.api.Assertions.*;
	
	import java.time.LocalDate;
	import java.util.*;

	import org.junit.jupiter.api.AfterEach;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.context.ConfigurableApplicationContext;
	import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
	
	import telran.cars.dto.CarDto;
	import telran.cars.dto.CarModelDto;
	import telran.cars.dto.OwnerDto;
	import telran.cars.service.ICars;
	@SpringBootApplication
	@EnableJpaRepositories("telran.cars.dao.repo")
	class CarsOwnersTest {
	private static final String MODEL_NAME1 = "model1";
	private static final String REG_NUMBER1 = "123";
	private static final long OWNER_ID = 123;
	private static final String MODEL_NAME2 = "model2";
	private static final long OWNER_ID2 = 124;
	private static final String REG_NUMBER2 = "124";
	static ConfigurableApplicationContext ctx;
	ICars cars;
	CarDto carArray[]= {new CarDto
			(REG_NUMBER1, "color", MODEL_NAME1, OWNER_ID, LocalDate.now()),
			new CarDto(REG_NUMBER2, "green", MODEL_NAME1, OWNER_ID, LocalDate.now())
	};
	OwnerDto ownerArray[]= {
	new OwnerDto(OWNER_ID, "name", 1980)
	};
	CarModelDto modelArray[]= {
	new CarModelDto(MODEL_NAME1, 1000, "company", "country")
	};
		@BeforeEach
		void setUp() throws Exception {
			ctx=SpringApplication.run
			(CarsOwnersTest.class);
			cars=ctx.getBean(ICars.class);
			createModels();
			createOwners();
			createCars();
		}
	
		private void createCars() {
			for(CarDto c:carArray) {
				cars.addCar(c);
			}
			
		}
	
		private void createOwners() {
			for(OwnerDto o:ownerArray) {
				cars.addOwner(o);
			}
			
		}
	
		private void createModels() {
			for(CarModelDto m:modelArray) {
				cars.addModel(m);
			}
			
		}
	
		@AfterEach
		void tearDown() throws Exception {
			ctx.close();
		}
	
		@Test
		void getDataTest() {
			CarModelDto model=cars.getModel(MODEL_NAME1);
			assertEquals(modelArray[0],model);
			CarDto car=cars.getCar(REG_NUMBER1);
			assertEquals(carArray[0],car);
			OwnerDto owner=cars.getOwner(OWNER_ID);
			assertEquals(ownerArray[0],owner);
		}
		@Test
		void addDataTest() {
			assertFalse(cars.addModel(modelArray[0]));
			assertTrue(cars.addModel
			(new CarModelDto(MODEL_NAME2, 1000, "company", "country")));
			assertFalse(cars.addOwner(ownerArray[0]));
			assertTrue(cars.addOwner
			(new OwnerDto(OWNER_ID2, "name", 1990)));
			assertFalse(cars.addCar(carArray[0]));
			assertTrue(cars.addCar
		(new CarDto(REG_NUMBER1 + "111", "color", MODEL_NAME2, OWNER_ID2, LocalDate.now())));
		}
		
		@Test
		void getCarOwnerTest() {
			OwnerDto owner = cars.getCarOwner(REG_NUMBER1);
			assertEquals(ownerArray[0], owner);
		}
		@Test
		void getOwnerCarsTest() {
			List<CarDto> carList = cars.getOwnerCars(OWNER_ID);
			assertEquals(2, carList.size());
			assertTrue(carList.containsAll(Arrays.asList(carArray)));
		}
		@Test
		void updateOwnerTest() {
			OwnerDto owner2 = new OwnerDto(OWNER_ID2, "name", 1990);
			cars.addOwner(owner2);
			assertFalse(cars.updateOwner(REG_NUMBER1, OWNER_ID + 100));
			assertFalse(cars.updateOwner(REG_NUMBER1 + "111", OWNER_ID2));
			assertFalse(cars.updateOwner(REG_NUMBER1, OWNER_ID));
			assertTrue(cars.updateOwner(REG_NUMBER1, owner2.getId()));
			OwnerDto owner = cars.getCarOwner(REG_NUMBER1);
			assertEquals(owner2, owner);
		}
		@Test
		void removeCarTest() {
			assertFalse(cars.removeCar(REG_NUMBER1+ "111"));
			assertTrue(cars.removeCar(REG_NUMBER1));
			assertNull(cars.getCar(REG_NUMBER1));
		}
		@Test
		void getModelOwnersTest() {
			assertTrue(cars.getModelOwners(MODEL_NAME2).isEmpty());
			List<OwnerDto> owners = cars.getModelOwners(MODEL_NAME1);
			assertEquals(1, owners.size());
			assertEquals(ownerArray[0], owners.get(0));
		}
		@Test
		void removeOwnerTest() {
			assertFalse(cars.removeOwner(OWNER_ID + 100));
			assertTrue(cars.removeOwner(OWNER_ID));
			assertNull(cars.getOwner(OWNER_ID));
			assertNotNull(cars.getCar(REG_NUMBER1));
			assertNotNull(cars.getCar(REG_NUMBER2));
		}
		@Test 
		void getModelCarsTest() {
			List<CarDto> carsList = cars.getModelCars(MODEL_NAME1 + 100);
			assertTrue(carsList.isEmpty());
			carsList = cars.getModelCars(MODEL_NAME1);
			assertEquals(2, carsList.size());
			assertTrue(carsList.containsAll(Arrays.asList(carArray)));
		}
		@Test
		void getOwnersMoreThanCarsTest() {
			List<OwnerDto> ownersList = cars.getOwnersMoreThanCars(2);
			assertTrue(ownersList.isEmpty());
			ownersList = cars.getOwnersMoreThanCars(1);
			assertEquals(1, ownersList.size());
			assertEquals(ownerArray[0], ownersList.get(0));
		}
	
	}
