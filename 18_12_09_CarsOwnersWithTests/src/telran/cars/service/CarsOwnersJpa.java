	package telran.cars.service;
	
	import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;
	
	import telran.cars.dao.repo.CarModelRepository;
	import telran.cars.dao.repo.CarRepository;
import telran.cars.dao.repo.ModelCount;
import telran.cars.dao.repo.OwnerRepository;
import telran.cars.dao.repo.PopularModelsRepository;
import telran.cars.dto.CarDto;
	import telran.cars.dto.CarModelDto;
	import telran.cars.dto.OwnerDto;
	import telran.cars.jpa.entities.*;
	
	@Service
	public class CarsOwnersJpa implements ICars {
		@Autowired
		CarRepository carRepository;
		@Autowired
		CarModelRepository modelRepository;
		@Autowired
		OwnerRepository ownerRepository;
		@Autowired
		PopularModelsRepository popularModels;
	
		@Override
		@Transactional
		public boolean addCar(CarDto c) {
			if (carRepository.existsById(c.reg_number))
				return false;
			OwnerJpa owner = ownerRepository.findById(c.ownerId).orElse(null);
			if (owner == null)
				return false;
			CarModel model = modelRepository.findById(c.modelName).orElse(null);
			if (model == null)
				return false;
			CarJpa car = new CarJpa(c.reg_number, c.color, c.purchase_date, model, owner);
			carRepository.save(car);
			return true;
	
		}
	
		@Override
		@Transactional
		public boolean addOwner(OwnerDto o) {
			if (ownerRepository.existsById(o.id))
				return false;
			OwnerJpa owner = new OwnerJpa(o.id, o.name, o.birthyear);
			ownerRepository.save(owner);
			return true;
	
		}
	
		@Override
		@Transactional
		public boolean addModel(CarModelDto m) {
			if (modelRepository.existsById(m.modelName))
				return false;
			CarModel model = new CarModel(m.modelName, m.volume, m.country, m.company);
			modelRepository.save(model);
			return true;
	
		}
	
		@Override
		public CarModelDto getModel(String modelName1) {
			CarModel modelJpa = modelRepository.findById(modelName1).orElse(null);
	
			return getModelDto(modelJpa);
		}
	
		private CarModelDto getModelDto(CarModel modelJpa) {
			if (modelJpa == null)
				return null;
			return new CarModelDto(modelJpa.getModelName(), modelJpa.getVolume(), modelJpa.getCompany(),
					modelJpa.getCountry());
		}
	
		@Override
		public CarDto getCar(String regNumber1) {
			CarJpa carJpa = carRepository.findById(regNumber1).orElse(null);
			return getCarDto(carJpa);
		}
	
		private CarDto getCarDto(CarJpa carJpa) {
	
			return carJpa == null ? null
					: new CarDto(carJpa.getReg_number(), carJpa.getColor(), carJpa.getCar_model().getModelName(),
							carJpa.getOwner()==null?0:carJpa.getOwner().getId(), carJpa.getPurchase_date());
		}
	
		@Override
		public OwnerDto getOwner(long ownerId) {
			OwnerJpa ownerJpa = ownerRepository.findById(ownerId).orElse(null);
			return getOwnerDto(ownerJpa);
		}
	
		private OwnerDto getOwnerDto(OwnerJpa ownerJpa) {
			return ownerJpa == null ? null : new OwnerDto(ownerJpa.getId(), ownerJpa.getName(), ownerJpa.getBirthyear());
		}

		@Override
		public OwnerDto getCarOwner(String regNumber) {
			CarJpa car = carRepository.findById(regNumber).orElse(null);
			return car == null? null: getOwnerDto(car.getOwner());
		}

		@Override
		@Transactional(readOnly=true)
		public List<CarDto> getOwnerCars(long ownerId) {
			OwnerJpa ownerJpa = ownerRepository.findById(ownerId).orElse(null);
			
			return ownerJpa == null? new ArrayList<>(): toListCarDto(ownerJpa.getCars());
		}

		private List<CarDto> toListCarDto(Set<CarJpa> cars) {
			return cars.stream().map(this::getCarDto).collect(Collectors.toList());
		}

		@Override
		@Transactional
		public boolean updateOwner(String regNumber, long newOwnerId) {
			CarJpa car = carRepository.findById(regNumber).orElse(null);
			
			if(car == null || (car.getOwner().getId()==newOwnerId)) return false;
			
			OwnerJpa owner = ownerRepository.findById(newOwnerId).orElse(null);
			if(owner == null) return false;
			
			car.setOwner(owner);
			return true;
		}

		@Override
		@Transactional
		public boolean removeCar(String regNumber) {
			CarJpa car = carRepository.findById(regNumber).orElse(null);
			if(car == null) return false;
			carRepository.delete(car);
			return true;
		}

		@Override
		@Transactional
		public boolean removeOwner(long ownerId) {
			OwnerJpa owner = ownerRepository.findById(ownerId).orElse(null);
			if(owner == null) return false;
			Set<CarJpa> cars = owner.getCars();
			cars.forEach(x -> x.setOwner(null));
			ownerRepository.delete(owner);
			return true;
		}

		@Override
		public List<OwnerDto> getModelOwners(String modelName) {
			Set<CarJpa> carJpa = getModelCarsJpa(modelName);
			return carJpa.stream().map(c -> getOwnerDto(c.getOwner())).distinct().collect(Collectors.toList());
		}

		private Set<CarJpa> getModelCarsJpa(String modelName) {
			return carRepository.findByCarModelModelName(modelName);
		}

		@Override
		public List<CarDto> getModelCars(String modelName) {
			return toListCarDto(getModelCarsJpa(modelName));
		}

		@Override
		public List<OwnerDto> getOwnersMoreThanCars(int nCars) {
			return toListOwnerDto(ownerRepository.findByCarsSizeGreaterThan(nCars));
		}

		private List<OwnerDto> toListOwnerDto(List<OwnerJpa> ownersJpa) {
			return ownersJpa.stream().map(this::getOwnerDto).collect(Collectors.toList());
		}

		@Override
		public List<ModelCount> getModelCounts(int year) {
			return carRepository.selectModelCounts(year);
		}

		@Override
		public List<String> getMostPopularCarModels(int year, int ageFrom, int ageTo) {
			long maxOccurences = popularModels.getMax(year, ageFrom, ageTo);
			
			return popularModels.getModels(year, ageFrom, ageTo, maxOccurences);
		}
	
	}
