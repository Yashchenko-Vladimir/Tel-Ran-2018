package telran.cars.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import telran.cars.dto.*;
import telran.net.*;
import static telran.cars.dto.CarsApiConstants.*;

public class CarsTcpProxy extends Client implements IRentCompany {

	public CarsTcpProxy(String hostname, int port) throws UnknownHostException, IOException {
		super(hostname, port);
		
	}
	
	private <T>T sendRequest (String requestType, Object requestData){
		try {
			Request request = new Request(requestType, requestData);
			output.writeObject(request);
			Response response = (Response) input.readObject();
			ResponseCode code = response.getResponseCode();
			if(code!= ResponseCode.OK) {
				throw new RuntimeException(code.toString());
			}
			return (T) response.getResponseData();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public CarsReturnCode addModel(Model model) {
		return sendRequest(ADD_MODEL, model);
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		return sendRequest(ADD_CAR, car);
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		return sendRequest(ADD_DRIVER, driver);
	}

	@Override
	public Model getModel(String modelName) {
		return sendRequest(GET_MODEL, modelName);
	}

	@Override
	public Car getCar(String carNumber) {
		return sendRequest(GET_CAR, carNumber);
	}

	@Override
	public Driver getDriver(long licenseId) {
		return sendRequest(GET_DRIVER, licenseId);
	}

	@Override
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		RentCar rentcar = new RentCar(carNumber, licenseId, rentDate, rentDays);
		return sendRequest(RENTCAR, rentcar);
	}

	@Override
	public CarsReturnCode returnCar(String carNumber, LocalDate returnDate, int gasTankPercent, int damages) {
		ReturnCar returnCar = new ReturnCar(carNumber, returnDate, gasTankPercent, damages);
		return sendRequest(RETURNCAR, returnCar);
	}

	@Override
	public CarsReturnCode removeCar(String carNumber) {
		return sendRequest(REMOVE_CAR, carNumber);
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		Clear clear = new Clear(currentDate, days);
		return sendRequest(CLEAR, clear);
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
		return sendRequest(GET_CARDRIVERS, carNumber);
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		return sendRequest(GET_DRIVERCARS, licenseId);
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		return ((Collection<RentRecord>) sendRequest(GET_ALL_RECORDS, null)).stream();
	}

	@Override
	public Stream<Car> getAllCars() {
		return ((Collection<Car>) sendRequest(GET_ALL_CARS, null)).stream();
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		return ((Collection<Driver>) sendRequest(GET_ALL_DRIVERS, null)).stream();
	}

	@Override
	public List<String> getAllModelNames() {
		return sendRequest(GET_ALL_MODEL_NAME, null);
	}

	@Override
	public List<String> getMostPopularModelNames() {
		return sendRequest(GET_MOST_POPULAR_MODEL_NAME, null);
	}

	@Override
	public double getModelProfit(String modelName) {
		return sendRequest(GET_MODEL_PROFIT_MODEL_NAME, modelName);
	}

	@Override
	public List<String> getMostProfitModelNames() {
		return sendRequest(GET_MODEL_PROFIT_MODEL_NAME, null);
	}
	
	@Override
	public Stream<RentRecord> getReturnedRecordsByDates(LocalDate from, LocalDate to) {
		return ((Collection<RentRecord>) sendRequest(GET_RETURN_RECORD_BYDATES, null)).stream();
	}


	@Override
	public int getGasPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGasPrice(int price) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFinePercent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFinePercent(int finePercent) {
		// TODO Auto-generated method stub

	}
	
	public void saveToFile(String fileName) {
		sendRequest(SAVE, null);
	}

	
}
