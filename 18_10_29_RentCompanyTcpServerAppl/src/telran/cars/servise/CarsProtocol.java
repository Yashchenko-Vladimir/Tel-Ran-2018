package telran.cars.servise;


import static telran.cars.dto.CarsApiConstants.*;

import java.util.stream.Collectors;

import telran.cars.dto.*;
import telran.cars.model.IRentCompany;
import telran.cars.model.RentCompanyEmbedded;
import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;
import telran.utils.Persistable;

public class CarsProtocol implements Protocol {
	private static final String FILE_NAME = "company.data";
	IRentCompany rentCompany;

		public CarsProtocol(IRentCompany rentCompany) {
		this.rentCompany = rentCompany;
	}

		@Override
		public Response getResponse(Request request) {
			String requestType = request.getRequestType();
			switch(requestType) {
			case SAVE: return _save(request);
			case ADD_MODEL: return _model_add(request);
			case ADD_CAR: return _car_add(request);
			case ADD_DRIVER:return _driver_add(request);
			case GET_CAR: return _car_get(request);
			case RENTCAR: return _rentcar(request);
			case RETURNCAR:return _returncar(request);
			case REMOVE_CAR: return _car_remove(request);
			case CLEAR: return _clear(request);
			case GET_CARDRIVERS:return _get_car_drivers(request);
			case GET_DRIVERCARS: return _get_driver_cars(request);
			case GET_ALL_RECORDS: return _get_all_records(request);
			case GET_ALL_CARS:return _get_all_cars(request);
			case GET_ALL_DRIVERS: return _get_all_drivers(request);
			case GET_ALL_MODEL_NAME: return _get_all_model_name(request);
			case GET_MOST_POPULAR_MODEL_NAME:return _get_most_popular_model_name(request);
			case GET_MODEL_PROFIT: return _get_model_profit(request);
			case GET_MODEL_PROFIT_MODEL_NAME: return _get_model_profit_model_name(request);
			case GET_RETURN_RECORD_BYDATES:return _get_return_record_by_dates(request);
			default: return wrongRequest();
		}

	}

		private Response _get_return_record_by_dates(Request request) {
			try {
				RecordsByDates recordsByDates = (RecordsByDates) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.getReturnedRecordsByDates(recordsByDates.getFrom(),
					recordsByDates.getTo()));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_model_profit_model_name(Request request) {
			try {
			return new Response(ResponseCode.OK, rentCompany.getMostProfitModelNames());
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_model_profit(Request request) {
			try {
			String modelName = (String) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.getModelProfit(modelName ));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_most_popular_model_name(Request request) {
			try {
			return new Response(ResponseCode.OK, rentCompany.getMostPopularModelNames());
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_all_model_name(Request request) {
			try {
			return new Response(ResponseCode.OK, rentCompany.getAllModelNames());
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_all_drivers(Request request) {
			try {
			return new Response(ResponseCode.OK, rentCompany.getAllDrivers().collect(Collectors.toList()));
			} catch(Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_all_cars(Request request) {
			try {
				return new Response(ResponseCode.OK, rentCompany.getAllCars().collect(Collectors.toList()));
			} catch(Exception e) {
			return wrongRequest();
			}
		}

		private Response _get_all_records(Request request) {
			try {
			return new Response(ResponseCode.OK, rentCompany.getAllRecords().collect(Collectors.toList()));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_driver_cars(Request request) {
			try {
			long licenseId = (long) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.getDriverCars(licenseId ));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _get_car_drivers(Request request) {
			try {
			String carNumber = (String) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.getCarDrivers(carNumber ));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _clear(Request request) {
			try {
				Clear clear1 = (Clear) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.clear( clear1.getCurrentDate(), clear1.getDays()));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _car_remove(Request request) {
			try {
			String carNumber = (String) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.removeCar(carNumber ));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _returncar(Request request) {
			try {
			ReturnCar returnCar = (ReturnCar) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.returnCar(returnCar.getCarNumber()
					, returnCar.getReturnDate(), returnCar.getGasTankPersecent(), returnCar.getDamages()));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _rentcar(Request request) {
			try {
			RentCar rentCar = (RentCar) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.rentCar(rentCar.getCarNumber(),
					rentCar.getLicenseId(), rentCar.getRentDate(), rentCar.getRentDays()));
			} catch (Exception e ) {
				return wrongRequest();
			}
		}

		private Response _car_get(Request request) {
			try {
			String carNumber = (String) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.getCar(carNumber ));
			} catch(Exception e) {
				return wrongRequest();
			}
		}

		private Response _driver_add(Request request) {
			try {
				Driver driver = (Driver) request.getRequestData();
				return new Response(ResponseCode.OK, rentCompany.addDriver(driver ));
				} catch (Exception e) {
					return wrongRequest();
				}
		}

		private Response _car_add(Request request) {
			try {
			Car car = (Car) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.addCar(car ));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response _model_add(Request request) {
			try {
			Model model = (Model) request.getRequestData();
			return new Response(ResponseCode.OK, rentCompany.addModel(model));
			} catch (Exception e) {
				return wrongRequest();
			}
		}

		private Response wrongRequest() {
			return new Response(ResponseCode.WRONG_REQUEST,null);
		}	
		
		private Response _save(Request request) {
			String saved ="";
			if(rentCompany instanceof Persistable) {
				((Persistable)rentCompany).saveToFile(FILE_NAME);
			}
			saved = "saved";
			return new Response(ResponseCode.OK, saved);
		}
}



