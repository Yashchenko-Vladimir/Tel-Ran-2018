package telran.cars;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.cars.dao.repo.ModelCount;
import telran.cars.dto.*;
import telran.cars.service.ICars;

@SpringBootApplication
class CarOwnersStatistics {
	OwnerDto[] owners;
	CarModelDto [] models;
	CarDto [] cars;
	
	ICars carsService;
	
	ConfigurableApplicationContext ctx;
	 

	@BeforeEach
	void setUp() throws Exception {
		ctx = SpringApplication.run(CarOwnersStatistics.class);
		carsService = ctx.getBean(ICars.class);
		createModels();
		createOwners();
		cteareCars();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		ctx.close();
	}

	private void cteareCars() {
		CarModelDto [] modelCars = {
			models[0], models[0], models[0], models[0],
			models[1], models[1], models[1],
			models[2], models[2], models[2]
		};
		OwnerDto [] ownersCars = {
			owners[0], owners[0], owners[1], owners[1],
			owners[2], owners[2], owners[3],
			owners[3], owners[4], owners[4]
		};
		
		cars = new CarDto[ownersCars.length];
		for(int i=0; i < ownersCars.length; i++) {
			CarDto car = new CarDto("IL-" + i, "white", modelCars[i].getModel_name(), ownersCars[i].getId(), LocalDate.now());
			cars[i] = car;
			carsService.addCar(car);
		}
		
	}

	private void createOwners() {
		int ownerYears[] = {1995, 1997, 1960, 1965, 1970};
		owners = new OwnerDto[ownerYears.length];
		for (int i = 0; i < ownerYears.length; i++) {
			OwnerDto owner = new OwnerDto(i + 1, "name" + 1 , ownerYears[i]);
			owners[i] = owner;
			carsService.addOwner(owner);
		}
		
	}

	private void createModels() {
		models = new CarModelDto[]{
			new CarModelDto("model1", 1600, "company", "country"),
			new CarModelDto("model2", 2000, "company", "country"),
			new CarModelDto("model3", 2500, "company", "country")
		};
		for(CarModelDto model : models) {
			carsService.addModel(model);
		}
		
	}

	@Test
	void ModelCountTest() {
		List<ModelCount> modelCounts = carsService.getModelCounts(LocalDate.now().getYear());
		ModelCount mc1 = modelCounts.get(0);
		assertEquals(models[0].getModel_name(), mc1.getModelName());
		assertEquals(4, mc1.getCount().intValue());
		ModelCount mc2 = modelCounts.get(1);
		assertEquals(models[1].getModel_name(), mc2.getModelName());
		assertEquals(3, mc2.getCount().intValue()); 
		ModelCount mc3 = modelCounts.get(2);
		assertEquals(models[2].getModel_name(), mc3.getModelName());
		assertEquals(3, mc3.getCount().intValue()); 
	}
	
	@Test
	void mostPopularModelstest() {
		List<String> popularModels20_30 = carsService.getMostPopularCarModels(LocalDate.now().getYear(), 20, 30);
		assertEquals(models[0].getModel_name(), popularModels20_30.get(0));
		assertEquals(1, popularModels20_30.size());
	}

}
