package telran.cars.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import telran.cars.dto.*;
import telran.cars.model.*;


public class MyWorkRandomRentComanyCreation {
	private static final int MIN_GAS_TANK = 35;
	private static final int MAX_GAS_TANK = 60;
	private static final int N_COMPANY = 3;
	private static final int N_MODEL = 20;
	private static final int MIN_PRICE_DAY = 50;
	private static final int MAX_PRICE_DAY = 250;
	private static final int N_CARS = 1000;
	private static final int N_DRIVERS = 10000;
	private static final LocalDate DATE_START = LocalDate.of(2008, 8, 1);
	private static final int MIN_RENT_DAYS_CAR = 3;
	private static final int MAX_RENT_DAYS_CAR = 20;
	private static final int MIN_DELAY_DAY = 0;
	private static final int MAX_DELAY_DAYS = 0;
	private static final int COFF_DALAY = 0;
	private static final int MIN_GASTANK_PERCENT = 0;
	private static final int MAX_GASTANK_PERCENT = 40;
	
	private static final int MIN_DAMAGES = 0;
	private static final int MAX_DAMAGES = 5;
	
	static Random gen = new Random();
	static IRentCompany rentCompany;

	public static void main(String[] args) {
		rentCompany = new RentCompanyEmbedded();
		long start = System.currentTimeMillis();
		fillModelsCarsDrivers(rentCompany);
		RentcompanyImmitation(rentCompany);
		saveToFile((RentCompanyEmbedded) rentCompany);
		long finish = System.currentTimeMillis();
		System.out.println(rentCompany.getAllRecords().count());
		System.out.println("Time run fill Appl = " + (finish - start)/1000);
		
//		rentCompany.getAllRecords().forEach(driver -> System.out.println(driver));

	}

	private static void saveToFile(RentCompanyEmbedded rentCompany) {
		try {
			
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("RentCompany1"));
			
			output.writeObject(rentCompany);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void RentcompanyImmitation(IRentCompany rentCompany) {
		LocalDate dateStart = DATE_START;
		for(LocalDate date = dateStart; date.isBefore(LocalDate.now()); date = date.plusDays(1)) {
			getAllCarsRent(date, rentCompany);
			
		}
				
	}

	private static void getAllCarsRent(LocalDate date, IRentCompany rentCompany) {
		List<Car> rentCars = rentCompany.getAllCars().filter(car -> !car.isInUse() &&!car.isFlRemoved()).collect(Collectors.toList());
		startRentCars(date, rentCompany, rentCars);
		
		List<RentRecord> returnCar = rentCompany.getAllRecords().filter(record -> record.getReturnDate() == null).
				filter(record -> 
				(record.getRentDate().plusDays(record.getRentDays()).isEqual(date)) || 
						(record.getRentDate().plusDays(record.getRentDays()).isBefore(date))).
				collect(Collectors.toList());
		returnRecordsCars(returnCar, rentCompany);
				
	}

	

	private static void returnRecordsCars(List<RentRecord> returnCar, IRentCompany rentCompany) {
		returnCar.stream().forEach(record -> {
			String carNumber = record.getCarNumber();
			int delayDays = 0;//COFF_DALAY * getRandomNumber(MIN_DELAY_DAY, MAX_DELAY_DAYS);
			LocalDate returnDate = record.getRentDate().plusDays(record.getRentDays() + 
					delayDays);
			int gasTankPercent = getRandomNumber(MIN_GASTANK_PERCENT, MAX_GASTANK_PERCENT);
			int damages = getRandomNumber(MIN_DAMAGES, MAX_DAMAGES);
			rentCompany.returnCar(carNumber, returnDate, gasTankPercent, damages);
//			if(delayDays > 0) {
//				rentCompany.getCar(record.getCarNumber()).setInUse(true);
//			}
		});
		
	}

	private static void startRentCars(LocalDate date, IRentCompany rentCompany, List<Car> rentCars) {
		int countNoRentCars = getRandomNumber(0, rentCars.size() - 1);
		for(int i =0 ; i < countNoRentCars; i ++) {
			rentCars.remove(getRandomNumber(0,rentCars.size() - 1));
		}
		rentCars.stream().forEach(car -> {
			long licenseId = getLicenseIdRandom(rentCompany);
			int rentDays = getRandomNumber(MIN_RENT_DAYS_CAR, MAX_RENT_DAYS_CAR);
			rentCompany.rentCar(car.getRegNumber(), licenseId, date, rentDays);
		});
		
	}

	private static long getLicenseIdRandom(IRentCompany rentCompany) {
		long [] licenseIds = rentCompany.getAllDrivers().mapToLong(driver -> driver.getLicenseId()).toArray();
		return licenseIds[getRandomNumber(0, licenseIds.length-1)];
	}

	private static void fillModelsCarsDrivers(IRentCompany rentCompany) {
		List<Model> models = getRandomModelList();
		models.forEach(model -> rentCompany.addModel(model));
		List<Car> cars = getRandomCarsList(models);
		cars.forEach(car -> rentCompany.addCar(car));
		List<Driver> drivers = getRandomDriversList();
		drivers.forEach(driver -> rentCompany.addDriver(driver));
		
	}

	private static List<Driver> getRandomDriversList() {
		List<Driver> result = new ArrayList<>();
		for(int i=0; i < N_DRIVERS; i++) {
			result.add(getRandomDriver(i));
		}
		return result;
	}

	private static Driver getRandomDriver(int i) {
		long licenseId = (long) 10000000L + i * 123456;
		String name = "name_" + i;
		int birthYear = getRandomNumber(1950, 2000);
		String phone = getRandomPhone();
		Driver driver = new Driver(licenseId, name, birthYear, phone);
		return driver;
	}

	private static String getRandomPhone() {
		String[] prefixes = {"050", "051", "052", "053", "054", "055", "056", "057", "058"};
		return String.format("%s-%d",prefixes[getRandomNumber(0, prefixes.length-1)], getRandomNumber(1000000, 9999999));
	}

	private static List<Car> getRandomCarsList(List<Model> models) {
		List<Car> result = new ArrayList<>();
		for(int i = 0; i < N_CARS; i++) {
			result.add(getRandomCar(i, models));
		}
		return result;
	}

	private static Car getRandomCar(int i, List<Model> models) {
		 String regNumber = String.valueOf(10000 + i*31);
		 List<String> colors = getListColors();
		 String color = colors.get(getRandomNumber(0, colors.size()-1));
		 String modelName = models.get(getRandomNumber(0, models.size()-1)).getModelName();
		 Car car = new Car(regNumber, color, modelName);
		 return car;
	}

	private static List<String> getListColors() {
		List<String> result = new ArrayList<>();
		result.add("black");
		result.add("red");
		result.add("green");
		result.add("white");
		result.add("blue");
		result.add("orange");
		return result;
	}

	private static List<Model> getRandomModelList() {
		List<Model> result = new ArrayList<>();
		for(int i = 0; i < N_MODEL; i++) {
			result.add(getRandomModel(i));
		}
		return result;
	}

	private static Model getRandomModel(int i) {
		String modelName = "model_" + (1 + i); 
		int gasTank = getRandomNumber(MIN_GAS_TANK, MAX_GAS_TANK);
		String company = "company_" + getRandomNumber(1, N_COMPANY);
		String country = "Israel";
		int priceDay = getRandomNumber(MIN_PRICE_DAY, MAX_PRICE_DAY);
		Model model = new Model(modelName, gasTank, company, country, priceDay);
		return model;
	}

	private static int getRandomNumber(int min, int max) {
		return gen.ints(1, min, max+1).findFirst().getAsInt();
	}

}
