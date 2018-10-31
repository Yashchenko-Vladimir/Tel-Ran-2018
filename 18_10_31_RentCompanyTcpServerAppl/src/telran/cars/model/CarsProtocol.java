
package telran.cars.model;
import java.util.stream.Collectors;
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
	

    @Override
    public Response getResponse(Request request) {
        String requestType = request.getRequestType();
        switch (requestType) {
        case SAVE: return _save(request);
            case ADD_MODEL:
                return _model_add(request);
            case ADD_DRIVER:
                return _driver_add(request);
            case ADD_CAR:
                return _car_add(request);
            case GET_MODEL:
                return _model_get(request);
            case GET_CAR:
                return _car_get(request);
            case GET_DRIVER:
                return _driver_get(request);
            case RENT_CAR:
                return _car_rent(request);
            case RETURN_CAR:
                return _car_return(request);
            case REMOVE_CAR:
                return _car_remove(request);
            case GET_ALL_CARS:
                return _cars_get(request);
            case GET_ALL_DRIVERS:
                return _drivers_get(request);
            case GET_ALL_MODELS:
                return _models_get(request);
            case GET_ALL_RECORDS:
                return _records_get(request);
            case GET_CAR_DRIVERS:
                return _carDrivers_get(request);
            case GET_DRIVER_CARS:
                return _driverCars_get(request);
            case GET_MODEL_PROFIT:
                return _modelProfit_get(request);
            case GET_MOST_PROFITABLE_MODEL_NAMES:
                return _profitableModelNames_get(request);
            case GET_MOST_POPULAR_MODEL_NAMES:
                return _popularModel_get(request);
            case CLEAR:
                return _clear(request);
            case GET_RETURNED_RECORDS_BY_DATES:
                return _returnedRecords_get(request);
            default:
                return wrongRequest();
        }
    }

    private Response _returnedRecords_get(Request request) {
        try {
            return new Response(ResponseCode.OK,
                    iRentCompany.getAllRecords());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _clear(Request request) {
        try {
           ClearFactor cf = (ClearFactor) request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.clear(cf.getCurrentDate(), cf.getDays()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _popularModel_get(Request request) {
        try {
            return new Response(ResponseCode.OK,
                    iRentCompany.getMostPopularModelNames());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _profitableModelNames_get(Request request) {
        try {
            return new Response(ResponseCode.OK,
                    iRentCompany.getMostProfitModelNames());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _modelProfit_get(Request request) {
        try {
            String modelName = (String)request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.getModelProfit(modelName));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _driverCars_get(Request request) {
        try {
            long driverId = (long)request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.getDriverCars(driverId));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _carDrivers_get(Request request) {
        try {
            String carNumber = (String)request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.getCarDrivers(carNumber));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _records_get(Request request) {
        try {
            return new Response(ResponseCode.OK,
                    iRentCompany.getAllRecords()
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }
private Response _save(Request request) {
	String saved="";
	if(iRentCompany instanceof Persistable) {
		((Persistable)iRentCompany).saveToFile(FILE_NAME);
		saved="saved";
	}
	try {
        return new Response(ResponseCode.OK,
                saved);
    } catch (Exception e) {
        return wrongRequest();
    }
	
}
    private Response _models_get(Request request) {
        try {
            return new Response(ResponseCode.OK,
                    iRentCompany.getAllModelNames());
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _drivers_get(Request request) {
        try {
            return new Response(ResponseCode.OK,
                    iRentCompany.getAllDrivers().collect(Collectors.toList()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _cars_get(Request request) {
        try {
            return new Response(ResponseCode.OK,
                    iRentCompany.getAllCars().collect(Collectors.toList()));
        } catch (Exception e) {
            return wrongRequest();
        }

    }

    private Response _car_remove(Request request) {
        try {
            String carNumber = (String)request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.removeCar(carNumber));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _car_return(Request request) {
        try {
            DatesForReturn dtf = (DatesForReturn) request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.returnCar(dtf.getCarNumber(),dtf.getReturnDate(),dtf.getGasTankPercent(),dtf.getDamages()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _car_rent(Request request) {
        try {
           DatesForRent dtr = (DatesForRent) request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.rentCar(dtr.getCarNumber(),dtr.getDriverId(),dtr.getRentDate(),dtr.getDays()));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _driver_get(Request request) {
        try {
        long driverId = (long)request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.getDriver(driverId));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _car_get(Request request) {
        try {
        String carNumber = (String)request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.getCar(carNumber));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _model_get(Request request) {
        try {
        String modelName = (String)request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.getModel(modelName));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _car_add(Request request) {
        try {
            Car car = (Car) request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.addCar(car));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _driver_add(Request request) {
        try {
            Driver driver = (Driver) request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.addDriver(driver));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response _model_add(Request request) {
        try {
            Model model = (Model) request.getRequestData();
            return new Response(ResponseCode.OK,
                    iRentCompany.addModel(model));
        } catch (Exception e) {
            return wrongRequest();
        }
    }

    private Response wrongRequest() {
        return new Response(ResponseCode.WRONG_REQUEST, null);
    }
}
