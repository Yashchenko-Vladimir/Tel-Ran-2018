	package telran.cars.service;
	
	import java.util.List;

import telran.cars.dao.repo.ModelCount;
import telran.cars.dto.CarDto;
	import telran.cars.dto.CarModelDto;
	import telran.cars.dto.OwnerDto;
	
	public interface ICars {
	
		boolean addCar(CarDto c);
		boolean addOwner(OwnerDto o);
		boolean addModel(CarModelDto m);
		CarModelDto getModel(String modelName);
		CarDto getCar(String regNumber);
		OwnerDto getOwner(long ownerId);
		OwnerDto getCarOwner(String regNumber);
		List<CarDto> getOwnerCars(long ownerId);
		boolean updateOwner(String regNumber, long newOwnerId);
		boolean removeCar(String regNumber);
		boolean removeOwner(long ownerId);
		List<OwnerDto> getModelOwners(String modelName);
		List<CarDto> getModelCars(String modelName);
		List<OwnerDto> getOwnersMoreThanCars(int nCars);
		List<ModelCount> getModelCounts(int year);
		List<String> getMostPopularCarModels(int year, int ageFrom, int ageTo);
	
	}
