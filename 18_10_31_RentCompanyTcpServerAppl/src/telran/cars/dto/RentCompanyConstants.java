package telran.cars.dto;

public interface RentCompanyConstants {
    String ADD_MODEL = "/model/add"; //addModel(Model model);//(OK,MODEL_EXISTS)
    String ADD_CAR = "/car/add";
    String ADD_DRIVER = "/driver/add";
    String GET_MODEL = "/model/get";
    String GET_CAR = "/car/get";
    String GET_DRIVER = "/driver/get";
    String RENT_CAR = "/car/rent";
    String RETURN_CAR = "/car/return";
    String REMOVE_CAR = "/car/remove";
    String GET_ALL_DRIVERS = "/driver/getAll";
    String GET_ALL_MODELS = "/model/getAll";
    String GET_ALL_CARS = "/car/getAll";
    String GET_CAR_DRIVERS = "/car/getDrivers";
    String GET_DRIVER_CARS = "/driver/getCars";
    String GET_ALL_RECORDS = "/common/getAllRecords";
    String GET_MODEL_PROFIT = "/common/getProfit";
    String GET_MOST_PROFITABLE_MODEL_NAMES = "/common/getMostProfitableModelNames";
    String GET_MOST_POPULAR_MODEL_NAMES = "/common/getMostPopularModelNames";
    String CLEAR = "/common/clear";
    String SAVE="/save";
    String GET_RETURNED_RECORDS_BY_DATES = "/common/getRecordsByDate";
}
