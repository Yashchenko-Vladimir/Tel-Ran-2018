package telran.cars.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static telran.cars.dto.RentCompanyConstants.*;
import telran.net.*;
import telran.utils.Persistable;
import telran.cars.dto.*;
public class RentCompanyProxy extends Client
implements IRentCompany,Persistable {

    public RentCompanyProxy(String hostname, int port) throws UnknownHostException, IOException {
        super(hostname, port);
    }

    @Override
    public CarsReturnCode addModel(Model model) {
        return sendRequest(ADD_MODEL, model);
    }

    @Override
    public CarsReturnCode addCar(Car car) {
        return sendRequest(ADD_CAR,car);
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
    	 return sendRequest(RentCompanyConstants.RENT_CAR,
    			 new DatesForRent
    			 (carNumber, licenseId, rentDate, rentDays));
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
		   damages));
    }

    @Override
    public CarsReturnCode removeCar(String carNumber) {
        return sendRequest(REMOVE_CAR, carNumber);
    }

    @Override
    public List<Car> clear(LocalDate currentDate, int days) {
        return sendRequest(CLEAR, days);
    }

    @Override
    public List<Driver> getCarDrivers(String carNumber) {
        return sendRequest(GET_CAR_DRIVERS, carNumber);
    }

    @Override
    public List<Car> getDriverCars(long licenseId) {
        return sendRequest(GET_DRIVER_CARS, licenseId);
    }

    @Override
    public Stream<RentRecord> getAllRecords() {
        List<RentRecord> request = sendRequest(GET_ALL_RECORDS, null);
        return request.stream();
    }

    @Override
    public Stream<Car> getAllCars() {
        List<Car> request = sendRequest(GET_ALL_CARS, null);
        return request.stream();
    }

    @Override
    public Stream<Driver> getAllDrivers() {
        List<Driver> request = sendRequest(GET_ALL_DRIVERS, null);
        return request.stream();
    }

    @Override
    public List<String> getAllModelNames() {
        return sendRequest(GET_ALL_MODELS, null);
    }

    @Override
    public List<String> getMostPopularModelNames() {
        return sendRequest(GET_MOST_POPULAR_MODEL_NAMES, null);

    }

    @Override
    public double getModelProfit(String modelName) {
        return sendRequest(GET_MODEL_PROFIT, modelName);
    }

    @Override
    public List<String> getMostProfitModelNames() {
        return sendRequest(GET_MOST_PROFITABLE_MODEL_NAMES, null);
    }

    @Override
    public Stream<RentRecord> getReturnedRecordsByDates(LocalDate from, LocalDate to) {
        return sendRequest(GET_RETURNED_RECORDS_BY_DATES, null);
    }

    private <T> T sendRequest(String requestType, Object requestData) {

        Request request = new Request(requestType, requestData);

        try {
            output.writeObject(request);
            Response response = (Response) input.readObject();
            ResponseCode code = response.getResponseCode();
            if (code != ResponseCode.OK) {
                throw new RuntimeException(code.toString());
            }
            return (T) response.getResponseData();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

	@Override
	public void saveToFile(String fileName) {
		sendRequest(SAVE, null);
		
	}
}
