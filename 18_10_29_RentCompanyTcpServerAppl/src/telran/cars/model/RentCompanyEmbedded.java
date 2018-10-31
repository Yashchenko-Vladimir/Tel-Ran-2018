package telran.cars.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.corba.se.pept.encoding.InputObject;

import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RentRecord;
import telran.cars.dto.State;
import telran.utils.Persistable;

public class RentCompanyEmbedded extends AbstractRenrCompany implements Persistable{
	private HashMap<String, Car> cars = new HashMap<>();
	private HashMap<Long, Driver> drivers = new HashMap<>();
	private HashMap<String, Model> models = new HashMap<>();
	private HashMap<Long, List<RentRecord>> driverRecords = new HashMap<>();
	private HashMap<String, List<RentRecord>> carRecords = new HashMap<>();
	private TreeMap<LocalDate, List<RentRecord>> returnedRecords = new TreeMap<>();

	@Override
	public CarsReturnCode addModel(Model model) {
		// if (models.containsKey(model.getModelName())) {
		// return CarsReturnCode.MODEL_EXISTS;
		// }
		// models.put(model.getModelName(), model);
		// return CarsReturnCode.OK;
		if (model == null)
			throw new IllegalArgumentException("null");
		return models.putIfAbsent(model.getModelName(), model) == null ? CarsReturnCode.OK
				: CarsReturnCode.MODEL_EXISTS;
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		if (car == null)
			throw new IllegalArgumentException("null");
		if (!models.containsKey(car.getModelName()))
			return CarsReturnCode.NO_MODEL;

		return cars.putIfAbsent(car.getRegNumber(), car) == null ? CarsReturnCode.OK : CarsReturnCode.CAR_EXISTS;
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		if (driver == null)
			throw new IllegalArgumentException("null");
		return drivers.putIfAbsent(driver.getLicenseId(), driver) == null ? CarsReturnCode.OK
				: CarsReturnCode.DRIVER_EXISTS;
	}

	@Override
	public Model getModel(String modelName) {
		return models.get(modelName);
	}

	@Override
	public Car getCar(String carNumber) {
		return cars.get(carNumber);
	}

	@Override
	public Driver getDriver(long licenseId) {
		return drivers.get(licenseId);
	}

	@Override
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {

		CarsReturnCode code = checkRentCar(carNumber, licenseId);
		if (code != CarsReturnCode.OK)
			return code;
		RentRecord record = new RentRecord(licenseId, carNumber, rentDate, rentDays);
		Car car = getCar(carNumber);

		addRecordCarRecords(record);
		addRecordDriverRecords(record);
		car.setInUse(true);
		return code;
	}

	private void addRecordDriverRecords(RentRecord record) {
		driverRecords.putIfAbsent
		(record.getLicenseId(),
		new ArrayList<>());
        List<RentRecord> list =
        driverRecords.get(record.getLicenseId());
        list.add(record);

		
	}

	private void addRecordCarRecords(RentRecord record) {

		String carNumber = record.getCarNumber();
		List<RentRecord> list = 
	carRecords.computeIfAbsent
	(carNumber, x -> new ArrayList<RentRecord>());
		list.add(record);
	}

	private CarsReturnCode checkRentCar(String carNumber, long licenseId) {
		Car car = getCar(carNumber);

		if (car == null || car.isFlRemoved())
			return CarsReturnCode.NO_CAR;
		if (!drivers.containsKey(licenseId)) {
			return CarsReturnCode.NO_DRIVER;
		}
		if (car.isInUse()) {
			return CarsReturnCode.CAR_IN_USE;
		}

		return CarsReturnCode.OK;

	}

	@Override
	public CarsReturnCode returnCar(String carNumber, LocalDate returnDate, int gasTankPercent,
			int damages) {
			if(!carRecords.containsKey(carNumber)){
				return CarsReturnCode.CAR_NOT_RENTED;
			}else{
				List<RentRecord> records=carRecords
						.get(carNumber);
				RentRecord tmp = 
						records.get(records.size()-1);//
				
				if(tmp.getReturnDate()!=null){
					return CarsReturnCode.CAR_NOT_RENTED;
				}
				if(tmp.getRentDate().isAfter(returnDate)){
					return CarsReturnCode.RETURN_DATE_WRONG;
				}
				
				tmp.setReturnDate(returnDate);
				tmp.setGasTankPercent(gasTankPercent);
				tmp.setDamages(damages);
				Model modelName=models.get(getCar(carNumber).getModelName());
				tmp.setCost(calculate_cost(tmp,modelName));
				cars.get(carNumber).setInUse(false);
				damages_processing(damages,carNumber);
				
				addReturnedRecord(returnDate, tmp);
				
			}
			return CarsReturnCode.OK;
		}

	private void addReturnedRecord(LocalDate returnDate, RentRecord tmp) {
		returnedRecords.putIfAbsent(returnDate,
				new ArrayList<>());
		returnedRecords.get(returnDate).add(tmp);
	}
		
		

		private void damages_processing(int damages,String carNumber) {
			Car my_car = cars.get(carNumber);
			if (damages==0)
				return;
			if(damages<=10){
				my_car.setState(State.GOOD);
			}
			else {
				my_car.setState(State.BAD);
			}
			if(damages>30){
				my_car.setFlRemoved(true);
			}
			
			
			
		}

		

		

	@Override
	public CarsReturnCode removeCar(String carNumber) {
		Car car = getCar(carNumber);
	    
		  if(car == null || car.isFlRemoved()) {
		   return CarsReturnCode.NO_CAR;
		  }
		  if(car.isInUse()) {
		   return CarsReturnCode.CAR_IN_USE;
		  }
		  car.setFlRemoved(true);
		  return CarsReturnCode.OK;
		
	}

	@Override
    public List<Car> clear(LocalDate currentDate, int days) {
        LocalDate removeDate = currentDate.minusDays(days);

        NavigableMap<LocalDate, List<RentRecord>> recordsBefore = returnedRecords.headMap(removeDate, true);

        List<RentRecord> records = recordsBefore.values()
        		.stream()
                .flatMap(List::stream)
                .filter(r -> getCar(r.getCarNumber())
                		.isFlRemoved())
                .collect(Collectors.toList());

        Set <Long> licenseIds = new HashSet<>();//?
        Set <String> regNumbers = new HashSet<>();//?

        records.forEach(r->addToSet(r, licenseIds, regNumbers));

        List<Car> carsForDelete = new ArrayList<>();
        regNumbers.forEach(n -> carsForDelete
        		.add(cars.remove(n)));

        removeRecordsFromAllMap(licenseIds, regNumbers);
        return carsForDelete;
    }
    
    private void addToSet(RentRecord record, Set<Long> licenseId, Set<String> regNumbers) {
        licenseId.add(record.getLicenseId());
        regNumbers.add(record.getCarNumber());
    }
    
    private void removeRecordsFromAllMap(Set<Long> licenseId,
    		Set<String> regNumbers) {
        removeFromCars(regNumbers);
        removeFromDrivers(licenseId, regNumbers);
        removeFromReturn(regNumbers);
    }

    private void removeFromReturn(Set<String> regNumbers) {
        Iterator<List<RentRecord>> it=
        	returnedRecords.values().iterator();
        while(it.hasNext()) {
        	List<RentRecord> list=it.next();
        	list.removeIf(r->regNumbers
        			.contains(r.getCarNumber()));
        	if(list.isEmpty())
        		it.remove();
        }
        
    }

    private void removeFromDrivers(Set<Long> licenseId,
    		Set<String> regNumbers) {
        licenseId.forEach(li-> {
        	List<RentRecord> records=driverRecords.get(li);
        	records.removeIf(r->regNumbers
        			.contains(r.getCarNumber()));
        	if(records.isEmpty()) {
        		driverRecords.remove(li);
        	}
        }
        		);
    }

    private void removeFromCars(Set<String> regNumbers) {
        regNumbers.forEach(c->carRecords.remove(c));
    }
	@Override
	public List<Driver> getCarDrivers(String carNumber) {		
		List<RentRecord> records = carRecords.get(carNumber);
		if (records == null) return new ArrayList<Driver>();

		return records
				.stream()
				.map(x -> getDriver(x.getLicenseId()))
				.distinct()
				.collect(Collectors.toList());
	}

	public List<Car> getDriverCars(long licenseId) {
		List<RentRecord> records = driverRecords.get(licenseId);
		if (records == null) return new ArrayList<Car>();

		return records
				.stream()
				.map(x -> getCar(x.getCarNumber()))
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		return carRecords.values().stream().flatMap(List::stream);
	}

	@Override
	public Stream<Car> getAllCars() {
		
		return cars.values().stream();
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		
		return drivers.values().stream();
	}

	@Override
	public List<String> getAllModelNames() {
		
		return models.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public List<String> getMostPopularModelNames() {
//		Map<Object, Long> popularModel = getAllRecords()
//				.collect(Collectors.groupingBy(x-> cars.get(x.getCarNumber()).getModelName(), Collectors.counting()));
//		long max = popularModel.values().stream().mapToLong(x -> x).max().orElse(0);
//		
//		List<String> res = new ArrayList<>();
//		popularModel.forEach((k,v)-> {if(v==max)res.add((String) k);});
//		return popularModel.entrySet().stream().filter(x -> (x.getValue() == max)).map(x -> x.getKey().toString())
//				.collect(Collectors.toList());
		Map<String, Long> collect = getAllRecords().map(x -> getCar(x.getCarNumber()))
                .collect(Collectors.groupingBy(Car::getModelName, Collectors.counting()));
        long size = collect.values().stream().max(Long::compare).orElse(0l);
        
        List <String> res=new ArrayList<>();
        
        collect.forEach((k,v)->{if(v==size)res.add(k);});
        
        return res;
		
	}

	@Override
	public double getModelProfit(String modelName) {
//		double result = getAllRecords().filter(x -> cars.get(x.getCarNumber()).
//				getModelName().equals(modelName)).
//				mapToDouble(x-> x.getCost()).sum();
//		
//		return result;
		 if(!models.containsKey(modelName))
			 return -1;
		 
	     return getProfitFromRecords(getAllRecords().filter(r->getModelName(r).equals(modelName)),modelName);
	}

	private double getProfitFromRecords(
			Stream<RentRecord> records,String modelName) {
		
		return records.collect(Collectors.summingDouble(r->getProfitFromRecord(r,modelName)));
	}
	private double getProfitFromRecord
	(RentRecord record,String modelName) {
		return record.getRentDays()*getModel(modelName).getPriceDay();
	}
	private String getModelName(RentRecord record) {
		Car car=getCar(record.getCarNumber());
		return car.getModelName();
	}
	

	@Override
	public List<String> getMostProfitModelNames() {
//		Map<Object, Double> profitModel = getAllRecords()
//				.collect(Collectors.groupingBy(x-> cars.get(x.getCarNumber()).getModelName(), Collectors.summingDouble(x-> x.getCost())));
//		double max = profitModel.values().stream().mapToDouble(x -> x).max().orElse(0);
//		
//		return profitModel.entrySet().stream().filter(x -> (x.getValue() == max)).map(x -> x.getKey().toString()).
//				sorted().
//				collect(Collectors.toList());
		Map<String,Double>mapProfits= 
				getAllRecords()
				.collect(Collectors.groupingBy(this::getModelName,Collectors.summingDouble(r->getProfitFromRecord(r,getModelName(r)))));
		double max=mapProfits.values().stream().max(Double::compare).orElse(0.0);
		
		List <String> res=new ArrayList<>();
        mapProfits.forEach((k,v)->{if(v==max)res.add(k);});
        return res;
	}

	@Override
	public Stream<RentRecord> getReturnedRecordsByDates(LocalDate from, LocalDate to) {
		
		return returnedRecords.subMap(from, to).values().stream().flatMap(List::stream);
	}

	@Override
	public void saveToFile(String fileName) {
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))){
			
			output.writeObject(this);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static RentCompanyEmbedded restoreFromFile(String fileName) {
		RentCompanyEmbedded res = null;
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))){
			res = (RentCompanyEmbedded) input.readObject();
		} catch (Exception e) {
			res = new RentCompanyEmbedded();
			System.out.println("Don't work");
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	

}
