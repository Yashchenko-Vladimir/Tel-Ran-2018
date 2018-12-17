package telran.cars.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RentRecord;
import telran.cars.dto.State;
import telran.cars.jpa.entities.CarJpa;
import telran.cars.jpa.entities.DriverJpa;
import telran.cars.jpa.entities.ModelJpa;
import telran.cars.jpa.entities.RentRecordJpa;
import telran.cars.jpa.repo.*;
@Service
public class RentCompanyJpa extends AbstractRentCompany {
	@Autowired
	ModelRepository modelRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	DriverRepository driverRepository;
	@Autowired
	RentRecordRepository recordRepository;
	
	

	@Override
	@Transactional
	public CarsReturnCode addModel(Model model) {
		if(modelRepository.existsById(model.getModelName())) 
			return CarsReturnCode.MODEL_EXISTS;
		ModelJpa modelJpa = new ModelJpa(model.getModelName(), model.getGasTank(), model.getCompany(),
				model.getCountry(), model.getPriceDay());
		modelRepository.save(modelJpa);
		return CarsReturnCode.OK;
	}

	@Override
	@Transactional
	public CarsReturnCode addCar(Car car) {
		if(carRepository.existsById(car.getRegNumber()))
				return CarsReturnCode.CAR_EXISTS;
		
		ModelJpa modelJpa = (ModelJpa) modelRepository.findById(car.getModelName()).orElse(null);
		if(modelJpa == null) 
			return CarsReturnCode.NO_MODEL;
		
		CarJpa carJpa = new CarJpa(car.getRegNumber(),modelJpa,
				car.getColor(), car.getState(), false);
		carRepository.save(carJpa);
		
		return CarsReturnCode.OK;
	}

	@Override
	@Transactional
	public CarsReturnCode addDriver(Driver driver) {
		if(driverRepository.existsById(driver.getLicenseId()))
			return CarsReturnCode.DRIVER_EXISTS;
		DriverJpa driverJpa = new DriverJpa(driver.getLicenseId(), driver.getName(),
				driver.getBirthYear(), driver.getPhone());
		driverRepository.save(driverJpa);
		return CarsReturnCode.OK;
	}

	@Override
	public Model getModel(String modelName) {
		ModelJpa modelJpa = modelRepository.findById(modelName).orElse(null);
		if(modelJpa == null) return null;
		return toModel(modelJpa);
	}

	private Model toModel(ModelJpa modelJpa) {
		return new Model(modelJpa.getModelName(), modelJpa.getGasTank(),modelJpa.getCompany(),
				modelJpa.getCountry(), modelJpa.getPriceDay());
	}

	@Override
	@Transactional
	public Car getCar(String carNumber) {
		CarJpa carJpa = carRepository.findById(carNumber).orElse(null);
		if(carJpa == null) return null;
		return toCar(carJpa);
	}

	private Car toCar(CarJpa carJpa) {
		Car car = new Car(carJpa.getCarNumber(), carJpa.getColor(), carJpa.getModel().getModelName());
		car.setFlRemoved(carJpa.isFlRemoved());
		car.setState(carJpa.getState());
//		car.setInUse(recordRepository.existsByCarCarNumberAndReturnDateIsNull(carJpa.getCarNumber()));
		return car;
	}

	@Override
	public Driver getDriver(long licenseId) {
		DriverJpa driverJpa = driverRepository.findById(licenseId).orElse(null);
		if(driverJpa == null) return null;
		return toDriver(driverJpa);
	}

	private Driver toDriver(DriverJpa driverJpa) {
		return new Driver(driverJpa.getLicenseId(), driverJpa.getName(),
				driverJpa.getBirthYear(), driverJpa.getPhone());
	}

	@Override
	@Transactional
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		
		CarJpa carJpa = carRepository.findById(carNumber).orElse(null);
		if(carJpa == null || carJpa.isFlRemoved()) return CarsReturnCode.NO_CAR;
		DriverJpa driverJpa = driverRepository.findById(licenseId).orElse(null);
		if(driverJpa == null) return CarsReturnCode.NO_DRIVER;
		Set<RentRecordJpa> records = recordRepository.findByCarCarNumberAndReturnDateNull(carNumber);
		RentRecordJpa record = records.stream().findFirst().orElse(null);
		if(record != null) return CarsReturnCode.CAR_IN_USE;
		RentRecordJpa rentRecordJpa = new RentRecordJpa(carJpa, driverJpa, rentDate, null, rentDays, 
				carJpa.getModel().getPriceDay(), 0, 0);
		recordRepository.save(rentRecordJpa);
		return CarsReturnCode.OK;
	}

	@Override
	@Transactional
	public CarsReturnCode returnCar(String carNumber, LocalDate returnDate, int gasTankPercent, int damages) {
		CarJpa carJpa = carRepository.findById(carNumber).orElse(null);
		if(carJpa == null) return CarsReturnCode.CAR_NOT_RENTED;
		RentRecordJpa recordJpa = getInUseCar(carNumber);
		if(recordJpa == null) return CarsReturnCode.CAR_IN_USE;
		if(returnDate.isBefore(recordJpa.getRentDate()))
			return CarsReturnCode.RETURN_DATE_WRONG;
		recordJpa.setDamages(damages);
		recordJpa.setGasTankPercent(gasTankPercent);
		recordJpa.setReturnDate(returnDate);
		recordJpa.setCost(calculate_cost(toRentRecord(recordJpa), toModel(carJpa.getModel())));
		damagesProcessing(damages, carJpa);
		return CarsReturnCode.OK;
	}

	private RentRecordJpa getInUseCar(String carNumber) {
		return recordRepository.findByCarCarNumberAndReturnDateNull(carNumber).
				stream().findFirst().orElse(null);
	}

	private RentRecord toRentRecord(RentRecordJpa rec) {
		RentRecord record = new RentRecord(rec.getDriver().getLicenseId(), 
				rec.getCar().getCarNumber(), rec.getRentDate(), rec.getRentDays());
		record.setReturnDate(rec.getReturnDate());
		record.setDamages(rec.getDamages());
		record.setGasTankPercent(rec.getGasTankPercent());
		record.setCost(rec.getCost());
		return record;
	}
	
	private void damagesProcessing(int damages, CarJpa carJpa) {
		if (damages==0)
			return;
		if(damages <= 10){
			carJpa.setState(State.GOOD);
		}
		else {
			carJpa.setState(State.BAD);
		}
		if(damages>30){
			carJpa.setFlRemoved(true);
		}
	}

	@Override
	@Transactional
	public CarsReturnCode removeCar(String carNumber) {
		CarJpa carJpa = carRepository.findById(carNumber).orElse(null);
		if(carJpa == null)
			return CarsReturnCode.NO_CAR;
		if(getInUseCar(carNumber) == null)
			return CarsReturnCode.CAR_IN_USE;
		carRepository.deleteById(carNumber);
		return CarsReturnCode.OK;
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		List<CarJpa> cars = recordRepository.findByCarFlRemoved(true).stream().
		filter(rec -> rec.getReturnDate().isBefore(currentDate.minusDays(days))).map(rec -> rec.getCar()).distinct().
		collect(Collectors.toList());
		carRepository.deleteAll(cars);
		return cars.stream().map(this::toCar).collect(Collectors.toList());
		
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
//		
//		CarJpa carJpa = carRepository.findById(carNumber).orElse(null);
//		if(carJpa == null) return new ArrayList<>();
//		Set<RentRecordJpa> recordsJpa =  carJpa.getRecords();
//		System.out.println(recordsJpa);
//		return recordsJpa.stream().map(rec -> toDriver(rec.getDriver()).distinct().collect(Collectors.toList());
		return recordRepository.findByCarCarNumber(carNumber).stream().map(rec->rec.getDriver()).distinct().
				map(this::toDriver).collect(Collectors.toList());
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		return recordRepository.findByDriverLicenseId(licenseId).stream().map(rec->rec.getCar()).distinct().
				map(this::toCar).collect(Collectors.toList());
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		return recordRepository.findAll().stream().map(this::toRentRecord);
	}

	@Override
	public Stream<Car> getAllCars() {
		return carRepository.findAll().stream().map(this::toCar);
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		return driverRepository.findAll().stream().map(this::toDriver);
	}

	@Override
	public List<String> getAllModelNames() {
		return modelRepository.findAll().stream().map(model -> model.getModelName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getMostPopularModelNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getModelProfit(String modelName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getMostProfitModelNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<RentRecord> getReturnedRecordsByDates(LocalDate from, LocalDate to) {
		return recordRepository.findByReturnDateBetween(from, to).stream().map(this::toRentRecord);
	}

}
