
package telran.cars.model;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.net.*;
import telran.utils.Persistable;
import telran.cars.dto.*;
import static telran.cars.dto.RentCompanyConstants.*;
public class CarsProtocol implements Protocol {

    private static final String FILE_NAME = "company.data";

	public CarsProtocol(IRentCompany iRentCompany) {
        super();
        this.iRentCompany = iRentCompany;
    }

    IRentCompany iRentCompany;
    
    public ResponseJson getResponse(RequestJson request) {
        String requestType = request.getRequestType();
        try {      
          Method method = this.getClass().getDeclaredMethod(requestType.replaceAll("/", "_"), RequestJson.class);
          method.setAccessible(true);
          return (ResponseJson) method.invoke(this, request);
        } catch (Exception e) {
          return wrongRequest();
        }
    }
	

//    @Override
//    public ResponseJson getResponse(RequestJson request) {
//        String requestType = request.getRequestType();
//        switch (requestType) {
//        case SAVE: return _save(request);
//            case ADD_MODEL:
//                return _model_add(request);
//            case ADD_DRIVER:
//                return _driver_add(request);
//            case ADD_CAR:
//                return _car_add(request);
//            case GET_MODEL:
//                return _model_get(request);
//            case GET_CAR:
//                return _car_get(request);
//            case GET_DRIVER:
//                return _driver_get(request);
//            case RENT_CAR:
//                return _car_rent(request);
//            case RETURN_CAR:
//                return _car_return(request);
//            case REMOVE_CAR:
//                return _car_remove(request);
//            case GET_ALL_CARS:
//                return _cars_get(request);
//            case GET_ALL_DRIVERS:
//                return _drivers_get(request);
//            case GET_ALL_MODELS:
//                return _models_get(request);
//            case GET_ALL_RECORDS:
//                return _records_get(request);
//            case GET_CAR_DRIVERS:
//                return _carDrivers_get(request);
//            case GET_DRIVER_CARS:
//                return _driverCars_get(request);
//            case GET_MODEL_PROFIT:
//                return _modelProfit_get(request);
//            case GET_MOST_PROFITABLE_MODEL_NAMES:
//                return _profitableModelNames_get(request);
//            case GET_MOST_POPULAR_MODEL_NAMES:
//                return _popularModel_get(request);
//            case CLEAR:
//                return _clear(request);
//            case GET_RETURNED_RECORDS_BY_DATES:
//                return _returnedRecords_get(request);
//            default:
//                return wrongRequest();
//        }
//    }

    private ResponseJson _returnedRecords_get(RequestJson request) {
        try {
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getAllRecords());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _clear(RequestJson request) {
        try {
           ClearFactor cf = request.getRequestData(new TypeReference<ClearFactor>() {});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.clear(cf.getCurrentDate(), cf.getDays()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _popularModel_get(RequestJson request) {
        try {
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getMostPopularModelNames());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _profitableModelNames_get(RequestJson request) {
        try {
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getMostProfitModelNames());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _modelProfit_get(RequestJson request) {
        try {
            String modelName = request.getRequestData(new TypeReference<String>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getModelProfit(modelName));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _driverCars_get(RequestJson request) {
        try {
            long driverId = request.getRequestData(new TypeReference<Long>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getDriverCars(driverId));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _carDrivers_get(RequestJson request) {
        try {
            String carNumber = request.getRequestData(new TypeReference<String>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getCarDrivers(carNumber));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _records_get(RequestJson request) {
        try {
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getAllRecords()
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }
private ResponseJson _save(RequestJson request) {
	String saved="";
	if(iRentCompany instanceof Persistable) {
		((Persistable)iRentCompany).saveToFile(FILE_NAME);
		saved="saved";
	}
	try {
        return new ResponseJson(ResponseCode.OK,
                saved);
    } catch (Exception e) {
        return wrongRequest();
    }
	
}
    private ResponseJson _models_get(RequestJson request) {
        try {
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getAllModelNames());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _drivers_get(RequestJson request) {
        try {
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getAllDrivers().collect(Collectors.toList()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _cars_get(RequestJson request) {
        try {
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getAllCars().collect(Collectors.toList()));
        } catch (Exception e) {
            return wrongRequest();
        }

    }

    private ResponseJson _car_remove(RequestJson request) {
        try {
            String carNumber = request.getRequestData(new TypeReference<String>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.removeCar(carNumber));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _car_return(RequestJson request) {
        try {
            DatesForReturn dtf = request.getRequestData(new TypeReference<DatesForReturn>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.returnCar(dtf.getCarNumber(),dtf.getReturnDate(),dtf.getGasTankPercent(),dtf.getDamages()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _car_rent(RequestJson request) {
        try {
           DatesForRent dtr = request.getRequestData(new TypeReference<DatesForRent>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.rentCar(dtr.getCarNumber(),dtr.getDriverId(),dtr.getRentDate(),dtr.getDays()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _driver_get(RequestJson request) {
        try {
        long driverId = request.getRequestData(new TypeReference<Long>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getDriver(driverId));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _car_get(RequestJson request) {
        try {
        String carNumber = request.getRequestData(new TypeReference<String>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getCar(carNumber));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _model_get(RequestJson request) {
        try {
        String modelName = request.getRequestData(new TypeReference<String>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.getModel(modelName));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _car_add(RequestJson request) {
        try {
            Car car = request.getRequestData(new TypeReference<Car>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.addCar(car));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _driver_add(RequestJson request) {
        try {
            Driver driver = request.getRequestData(new TypeReference<Driver>() {	});
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.addDriver(driver));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson _model_add(RequestJson request) {
        try {
        	Model model = request.getRequestData(new TypeReference<Model>() {	});;
            return new ResponseJson(ResponseCode.OK,
                    iRentCompany.addModel(model));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private ResponseJson wrongRequest() {
        return new ResponseJson(ResponseCode.WRONG_REQUEST, null);
    }
}
