package telran.cars.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static telran.cars.dto.RentCompanyConstants.*;
import telran.net.*;
import telran.utils.Persistable;
import telran.cars.dto.*;
public class RentCompanyProxy extends Client implements IRentCompany,Persistable {

    public RentCompanyProxy(String hostname, int port) throws UnknownHostException, IOException {
        super(hostname, port);
    }

    @Override
    public CarsReturnCode addModel(Model model) {
        return sendRequest(ADD_MODEL, model, new TypeReference<CarsReturnCode>() {});
    }

    @Override
    public CarsReturnCode addCar(Car car) {
        return sendRequest(ADD_CAR,car,new TypeReference<CarsReturnCode>() {});
    }

    @Override
    public CarsReturnCode addDriver(Driver driver) {
        return sendRequest(ADD_DRIVER, driver, new TypeReference<CarsReturnCode>() {});
    }

    @Override
    public Model getModel(String modelName) {
        return sendRequest(GET_MODEL, modelName, new TypeReference<Model>() {});
    }

    @Override
    public Car getCar(String carNumber) {
        return sendRequest(GET_CAR, carNumber, new TypeReference<Car>() {});
    }

    @Override
    public Driver getDriver(long licenseId) {
        return sendRequest(GET_DRIVER, licenseId, new TypeReference<Driver>() {});
    }

    @Override
    public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
    	 return sendRequest(RentCompanyConstants.RENT_CAR,
    			 new DatesForRent(carNumber, licenseId, rentDate, rentDays), new TypeReference<CarsReturnCode>() {});
    }

    @Override
    public int getGasPrice() {
        return 0;
    }

    @Override
    public void setGasPrice(int price) {

    }

    @Override
    public int getFinePercent() {
        return 0;
    }

    @Override
    public void setFinePercent(int finePercent) {

    }

    @Override
    public CarsReturnCode returnCar(String carNumber, LocalDate returnDate, int gasTankPercent, int damages) {
    	 return sendRequest
    (RentCompanyConstants.RETURN_CAR,
   new DatesForReturn(carNumber, returnDate, gasTankPercent,
		   damages), new TypeReference<CarsReturnCode>() {});
    }

    @Override
    public CarsReturnCode removeCar(String carNumber) {
        return sendRequest(REMOVE_CAR, carNumber, new TypeReference<CarsReturnCode>() {});
    }

    @Override
    public List<Car> clear(LocalDate currentDate, int days) {
        return sendRequest(CLEAR, days, new TypeReference<List<Car>>() {});
    }

    @Override
    public List<Driver> getCarDrivers(String carNumber) {
        return sendRequest(GET_CAR_DRIVERS, carNumber, new TypeReference<List<Driver>>() {});
    }

    @Override
    public List<Car> getDriverCars(long licenseId) {
        return sendRequest(GET_DRIVER_CARS, licenseId, new TypeReference<List<Car>>() {});
    }

    @Override
    public Stream<RentRecord> getAllRecords() {
        List<RentRecord> request = sendRequest(GET_ALL_RECORDS, null, new TypeReference<List<RentRecord>>() {});
        return request.stream();
    }

    @Override
    public Stream<Car> getAllCars() {
        List<Car> request = sendRequest(GET_ALL_CARS, null, new TypeReference<List<Car>>() {});
        return request.stream();
    }

    @Override
    public Stream<Driver> getAllDrivers() {
        List<Driver> request = sendRequest(GET_ALL_DRIVERS, null, new TypeReference<List<Driver>>() {});
        return request.stream();
    }

    @Override
    public List<String> getAllModelNames() {
        return sendRequest(GET_ALL_MODELS, null, new TypeReference<List<String>>() {});
    }

    @Override
    public List<String> getMostPopularModelNames() {
        return sendRequest(GET_MOST_POPULAR_MODEL_NAMES, null, new TypeReference<List<String>>() {});

    }

    @Override
    public double getModelProfit(String modelName) {
        return sendRequest(GET_MODEL_PROFIT, modelName, new TypeReference<Double>() {});
    }

    @Override
    public List<String> getMostProfitModelNames() {
        return sendRequest(GET_MOST_PROFITABLE_MODEL_NAMES, null, new TypeReference<List<String>>() {});
    }

    @Override
    public Stream<RentRecord> getReturnedRecordsByDates(LocalDate from, LocalDate to) {
    	 List<RentRecord> request = sendRequest(GET_ALL_DRIVERS, null, new TypeReference<List<RentRecord>>() {});
         return request.stream();
        }

    

	@Override
	public void saveToFile(String fileName) {
		sendRequest(SAVE, null, new TypeReference<List<String>>() {});
		
	}
}
