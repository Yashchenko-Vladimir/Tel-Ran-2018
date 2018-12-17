package telran.cars.model;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;

import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.ClearFactor;
import telran.cars.dto.DatesForRent;
import telran.cars.dto.DatesForReturn;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RecordsByDates;
import telran.cars.dto.RentRecord;
import telran.utils.ILogin;
import telran.utils.Persistable;

import static telran.cars.dto.RentCompanyConstants.*;

public class RentCompanyWebProxy implements IRentCompany, ILogin {
	private String hostname;
	private int port;
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers=new HttpHeaders();

	public RentCompanyWebProxy(String hostname, int port) {
		
		this.hostname = hostname;
		this.port = port;
	}
	
	private String makeUrl(String ... str) {
		String url = null;
		if(port == 0) {
			url = "https://" + hostname  + str[0];
		} else {
			url = "http://" + hostname + ":" + port + str[0];
		}
		for(int i = 1; i < str.length; i++) {
			url +="/" + str[i];
		}
		return url;
	}
	
	private <E, T> T sendRequest(String url,  HttpMethod httMethod, E body, ParameterizedTypeReference<T> typeT){
		if(body == null) {
			return sendGETandDELETE(url, httMethod, typeT);
		} else {
			return sendPOST(url, httMethod, body, typeT);
		}
	}

	private <T, E> T sendPOST(String url, HttpMethod httMethod, E body, ParameterizedTypeReference<T> typeT) {
		HttpEntity<E> requestEntity = new HttpEntity<E>(body, headers);
		ResponseEntity<T> response  = restTemplate.exchange(url, httMethod, requestEntity, typeT);
		return  response.getBody();
	}

	private <T> T sendGETandDELETE(String url, HttpMethod httMethod, ParameterizedTypeReference<T> typeT) {
		ResponseEntity<T> response = restTemplate.exchange(url,  httMethod, new HttpEntity<>(headers), typeT);
		return  response.getBody();
	}

	@Override
	public CarsReturnCode addModel(Model model) {
		String url = makeUrl(ADD_MODEL);
		return sendRequest(url, HttpMethod.POST, model, new ParameterizedTypeReference<CarsReturnCode>() {});
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		String url = makeUrl(ADD_CAR);
		return sendRequest(url, HttpMethod.POST, car, new ParameterizedTypeReference<CarsReturnCode>() {});
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		String url = makeUrl(ADD_DRIVER);
		return sendRequest(url, HttpMethod.POST, driver, new ParameterizedTypeReference<CarsReturnCode>() {});
	}

	@Override
	public Model getModel(String modelName) {
		String url = makeUrl(GET_MODEL, modelName);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<Model>() {});
	}

	/* (non-Javadoc)
	 * @see telran.cars.model.IRentCompany#getCar(java.lang.String)
	 */
	@Override
	public Car getCar(String carNumber) {
		String url = makeUrl(GET_MODEL, carNumber);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {});
	}

	@Override
	public Driver getDriver(long licenseId) {
		String url = makeUrl(GET_DRIVER, String.valueOf(licenseId));
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<Driver>() {});
	}

	@Override
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		DatesForRent rent = new DatesForRent(carNumber, licenseId, rentDate, rentDays);
		String url = makeUrl(RENT_CAR);
		return sendRequest(url, HttpMethod.POST, rent, new ParameterizedTypeReference<CarsReturnCode>() {});
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

	@Override
	public CarsReturnCode returnCar(String carNumber, LocalDate returnDate, int gasTankPercent, int damages) {
		DatesForReturn returnCar = new DatesForReturn(carNumber, returnDate, gasTankPercent, damages);
		String url = makeUrl(RETURN_CAR);
		return sendRequest(url, HttpMethod.POST, returnCar, new ParameterizedTypeReference<CarsReturnCode>() {});
	}

	@Override
	public CarsReturnCode removeCar(String carNumber) {
		String url = makeUrl(REMOVE_CAR, carNumber);
		return sendRequest(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<CarsReturnCode>() {});
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		String url = makeUrl(CLEAR);
		ClearFactor cf = new ClearFactor(currentDate, days);
		return sendRequest(url, HttpMethod.POST, cf, new ParameterizedTypeReference<List<Car>>() {});
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
		String url = makeUrl(GET_CAR_DRIVERS, carNumber);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {});
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		String url = makeUrl(GET_DRIVER_CARS, String.valueOf(licenseId));
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		String url = makeUrl(GET_ALL_RECORDS);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<RentRecord>>() {}).stream();
	}

	@Override
	public Stream<Car> getAllCars() {
		String url = makeUrl(GET_ALL_CARS);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {}).stream();
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		String url = makeUrl(GET_ALL_DRIVERS);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {}).stream();
	}

	@Override
	public List<String> getAllModelNames() {
		String url = makeUrl(GET_ALL_MODELS);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
	}

	@Override
	public List<String> getMostPopularModelNames() {
		String url = makeUrl(GET_MOST_POPULAR_MODEL_NAMES);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
	}

	@Override
	public double getModelProfit(String modelName) {
		String url = makeUrl(GET_MODEL_PROFIT, modelName);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<Double>() {});
	}

	@Override
	public List<String> getMostProfitModelNames() {
		String url = makeUrl(GET_MOST_PROFITABLE_MODEL_NAMES);
		return sendRequest(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
	}

	@Override
	public Stream<RentRecord> getReturnedRecordsByDates(LocalDate from, LocalDate to) {
		String url = makeUrl(GET_RETURNED_RECORDS_BY_DATES);
		RecordsByDates records = new RecordsByDates(from, to);
		return sendRequest(url, HttpMethod.POST, records, new ParameterizedTypeReference<List<RentRecord>>() {}).stream();
	}

	@Override
	public boolean login(String username, String password) {
		String userPassword = username + ":" + password;//"moshe:54321^Com";
		headers = new HttpHeaders();
        headers.add("Authorization","Basic "+Base64.getEncoder().encodeToString
        		(userPassword.getBytes()));
		return true;
	}


}
