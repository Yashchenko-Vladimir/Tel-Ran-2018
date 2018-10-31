package telran.cars.model;

	import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
	import java.time.temporal.ChronoUnit;
	import java.util.*;
	import java.util.stream.Collector.Characteristics;
	import java.util.stream.Collectors;
	import java.util.stream.Stream;

	import telran.cars.dto.Car;
	import telran.cars.dto.CarsReturnCode;
	import telran.cars.dto.Driver;
	import telran.cars.dto.Model;
	import telran.cars.dto.RentRecord;
	import telran.cars.dto.State;

public class MyRentCompanyEmbedded extends AbstractRenrCompany {
	

		private HashMap<String, Car> cars = new HashMap<>();
		private HashMap<Long, Driver> drivers = new HashMap<>();
		private HashMap<String, Model> models = new HashMap<>();
		private HashMap<Long, List<RentRecord>> driverRecords = new HashMap<>();
		private HashMap<String, List<RentRecord>> carRecords = new HashMap<>();
		private TreeMap<LocalDate, List<RentRecord>> returnedRecords = new TreeMap<>();
		
		@Override
		public CarsReturnCode addModel(Model model) {
			if(model == null) { throw new IllegalArgumentException("Wrong data");}
//			if (models.containsKey(model.getModelName())) {
//				return CarsReturnCode.MODEL_EXISTS;
//			}
//			models.put(model.getModelName(), model);
//			return CarsReturnCode.OK;
			return models.putIfAbsent(model.getModelName(), model) == null? CarsReturnCode.OK: CarsReturnCode.MODEL_EXISTS;
				
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
		public int getFinePercent() {
			// TODO Auto-generated method stub
			return super.getFinePercent();
		}

		@Override
		public void setFinePercent(int finePercent) {
			// TODO Auto-generated method stub
			super.setFinePercent(finePercent);
		}

		@Override
		public int getGasPrice() {
			// TODO Auto-generated method stub
			return super.getGasPrice();
		}

		@Override
		public void setGasPrice(int gasPrice) {
			// TODO Auto-generated method stub
			super.setGasPrice(gasPrice);
		}

		@Override
		public CarsReturnCode addDriver(Driver driver) {
			if(driver == null) { throw new IllegalArgumentException("Wrong data");}
			return drivers.putIfAbsent(driver.getLicenseId(), driver) == null? CarsReturnCode.OK: CarsReturnCode.DRIVER_EXISTS;
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
			
//			
//			if(!cars.containsKey(carNumber)) {return CarsReturnCode.NO_CAR;}
//			if(!drivers.containsKey(licenseId)) {return CarsReturnCode.NO_DRIVER;}
//			
//			List<RentRecord> listReordsCar = new ArrayList<>();
//			listReordsCar.add(record);
//			carRecords.put(carNumber, listReordsCar);
//			List<RentRecord> listReordsDriver = new ArrayList<>();
//			listReordsDriver.add(record);
//			driverRecords.put(licenseId, listReordsCar);
			
			
			return code;
		}

		private void addRecordDriverRecords(RentRecord record) {
//			List<RentRecord> listReordsDriver = driverRecords.get(record.getLicenseId());
//			if(listReordsDriver == null) {
//				listReordsDriver = new ArrayList<>();
//			}
//			listReordsDriver.add(record);
//			driverRecords.put(record.getLicenseId(), listReordsDriver);
			
			driverRecords.putIfAbsent(record.getLicenseId(), new ArrayList<>());
	        List<RentRecord> list = driverRecords.get(record.getLicenseId());
	        list.add(record);
			
		}

		private void addRecordCarRecords(RentRecord record) {
//			List<RentRecord> listReordsCar = carRecords.get(record.getCarNumber());
//			if(listReordsCar == null) {
//				listReordsCar = new ArrayList<>();
//			}
//			listReordsCar.add(record);
//			carRecords.put(record.getCarNumber(), listReordsCar); // это лишнее, так ссылка сама меняет
			
			List<RentRecord> list = carRecords.computeIfAbsent(record.getCarNumber(), x -> new ArrayList<RentRecord>());
			list.add(record);

			
			
		}

		private CarsReturnCode checkRentCar(String carNumber, long licenseId) {
			Car car = getCar(carNumber);
			
			if(car == null|| car.isFlRemoved() ) {return CarsReturnCode.NO_CAR;}
			if(!drivers.containsKey(licenseId)) {return CarsReturnCode.NO_DRIVER;}
			if(car.isInUse()) {	return CarsReturnCode.CAR_IN_USE;	}
			
			return CarsReturnCode.OK;
		}

		@Override
		public CarsReturnCode returnCar(String carNumber, LocalDate returnDate, int gasTankPercent,
				int damages) {
			List<RentRecord> lisrRecord = carRecords.get(carNumber);
			if(lisrRecord == null) {
				return CarsReturnCode.CAR_NOT_RENTED;
			}
			RentRecord record = lisrRecord.get(lisrRecord.size()-1);
			
			if(record.getReturnDate() != null ) {
				return CarsReturnCode.CAR_NOT_RENTED;
			}
			if(ChronoUnit.DAYS.between(record.getRentDate(), returnDate ) < record.getRentDays()) {
				return CarsReturnCode.RETURN_DATE_WRONG;
			}
			
			Model model = models.get(cars.get(carNumber).getModelName());
			
			record.setGasTankPercent(gasTankPercent);
			record.setReturnDate(returnDate);
			record.setDamages(damages);
			cars.get(carNumber).setInUse(false);
			
//			float carCostRent = getCarCostRent(record.getRentDate(), returnDate, 
//					record.getRentDays(), model.getPriceDay(), model.getGasTank(), gasTankPercent);
			
			float carCostRent = calculate_cost(record, model);
			
			record.setCost(carCostRent);
			
			
					
			getStateCar(carNumber, damages);
			
			addRecordsTreeMapReturn(returnDate, record);
					
			return CarsReturnCode.OK;
		}

		private void addRecordsTreeMapReturn(LocalDate returnDate, RentRecord record) {
//			List<RentRecord> list = returnedRecords.computeIfAbsent(returnDate, x -> new ArrayList<RentRecord>());
			returnedRecords.putIfAbsent(returnDate, new ArrayList<RentRecord>());
			List<RentRecord> list = returnedRecords.get(returnDate);
			list.add(record);
			
		}

		private void getStateCar(String carNumber, int damages) {
			Car car = cars.get(carNumber);
			if (damages == 0) {
				car.setState(State.EXCELLENT);
			} 
			if (damages < 10) {
				car.setState(State.GOOD);
			} else {
				car.setState(State.BAD);
			} 
			if (damages > 30){
				car.setFlRemoved(true);
			}
			
		}

		@Override
		public CarsReturnCode removeCar(String carNumber) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Car> clear(LocalDate currentDate, int days) {
			LocalDate removeDate = currentDate.minusDays(days);
			
			 NavigableMap<LocalDate, List<RentRecord>>  recordsBefor = returnedRecords.headMap(removeDate, true);
			
			
			System.out.println("Start");
			List<Car> records = new ArrayList<>();
		
//			List<Car> flRemove = cars.values().stream().filter(x -> x.isFlRemoved()).collect(Collectors.toList());
		
			
//			System.out.println(removeDate.isBefore(currentDate));
			
//			List<RentRecord> listRecords = returnedRecords.entrySet().stream().filter(x -> removeDate.isAfter(x.getKey())).
//			flatMap(x -> x.getValue().stream()).collect(Collectors.toList());
//			System.out.println(listRecords.size());
//			records = returnedRecords.entrySet().stream().filter(x -> removeDate.isAfter(x.getKey())).
//					flatMap(x -> x.getValue().stream()).map(x -> x.getCarNumber()).distinct().map(x -> cars.get(x))
//					.filter(x -> x.isFlRemoved()).collect(Collectors.toList());
			
			
			List<RentRecord> recordss = recordsBefor.values().stream().// stream of List
					flatMap(List::stream).  // stream of records
					filter(x -> getCar(x.getCarNumber()).isFlRemoved()).collect(Collectors.toList());
			
			List<Car> carsForDelete = recordss.stream()
	                .map(r -> getCar(r.getCarNumber()))
	                .distinct()
	                .collect(Collectors.toList());
			List<String> regNumbers = new ArrayList<>();
			
			carsForDelete.stream().map(x -> x.getRegNumber()).forEach(n -> {
				regNumbers.add(n);
				cars.remove(n);
				carRecords.remove(n);
			});
			
//			driverRecords.values().stream().filter(x -> );
			
			Iterator<List<RentRecord>> it = returnedRecords.values().iterator();
			while(it.hasNext()) {
				List<RentRecord> list = it.next();
				list.removeIf(r -> regNumbers.contains(r.getCarNumber()));
				if(list.isEmpty()) {
					it.remove();
				}
			}
			
//			LocalDate dayOne = returnedRecords.firstKey();
//			LocalDate lastDay = returnedRecords.lastKey();
//			LocalDate tmp = dayOne;
//			while(!tmp.isAfter(lastDay)) {
//				returnedRecords.compute(tmp, (k, v) -> {
//					if(v!= null) {
//						v.removeIf(r -> regNumbers.contains(r.getCarNumber()));
//						if(v.isEmpty()) {
//							v=null;
//						}
//					}
//					return v;
//				});
//				tmp = tmp.plusDays(1);
//			}
			removeRecordsFromAllMap(recordss);
		        return carsForDelete;
		    }
		
	private void removeRecordsFromAllMap(List<RentRecord> recordss) {
//		removeFromMap(recordss, carRecords);
	    removeFromMap(recordss, driverRecords);
	    removeFromMap(recordss, returnedRecords);
	    
		}

	private <T> void removeFromMap(List<RentRecord> records, Map<T, List<RentRecord>> map) {
	    map.values().forEach(
	            l -> l.removeIf(records::contains));
	}   
			
//					entrySet().stream().filter(x -> removeDate.isAfter(x.getKey())).
//					flatMap(x -> x.getValue().stream()).map(x -> x.getCarNumber()).distinct().map(x -> cars.get(x))
//					.filter(x -> x.isFlRemoved()).collect(Collectors.toList());
//			if(records.size() != 0) {
//				removeAllRecordsList(records);
//				}

			
		
		

		private void removeAllRecordsList(List<Car> listCar) {
			List<String> numbersCar = listCar.stream().map(x -> x.getRegNumber()).collect(Collectors.toList());
			
			listCar.stream().map(x -> x.getRegNumber()).forEach(x -> {
					cars.remove(x);
					carRecords.remove(x);
			});
		
				List<RentRecord> listRecords = returnedRecords.values().stream().flatMap(a -> a.stream()).
				filter(a-> numbersCar.contains(a.getCarNumber())).collect(Collectors.toList());
				returnedRecords.values().forEach(b-> b.removeIf(listRecords::contains));
				driverRecords.values().forEach(b-> b.removeIf(listRecords::contains));
		}
//				
//				returnedRecords.values().remove(listRecords);
//				driverRecords.values().remove(listRecords);
//				
//				
//				
//			
//			listCar.stream().forEach(x -> {
//				if(cars.containsKey(x.getRegNumber())) {
//					cars.remove(x.getRegNumber());
//				}
//				if(carRecords.containsKey(x.getRegNumber())) {
//					carRecords.remove(x.getRegNumber());
//				}
////				returnedRecords.values().stream().flatMap(x -> x.stream()).filter(x -> listCar.)
//				
//				returnedRecords.
//				
				
			
//			cars.remove(listCar.get(0).getRegNumber());
			
		

		@Override
		public List<Driver> getCarDrivers(String carNumber) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Car> getDriverCars(long licenseId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Stream<RentRecord> getAllRecords() {
			
			return carRecords.values().stream().flatMap(x -> x.stream());
		}

		@Override
		public Stream<Car> getAllCars() {
			
			return cars.values().stream();
		}

		@Override
		public Stream<Driver> getAllDrivers() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<String> getAllModelNames() {
			// TODO Auto-generated method stub
			return null;
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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveToFile(String fileName) {
			try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))){
				
				output.writeObject(this);
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}



}
