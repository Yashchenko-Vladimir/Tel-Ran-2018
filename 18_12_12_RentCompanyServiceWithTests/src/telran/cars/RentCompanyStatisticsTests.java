package telran.cars;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import telran.cars.dto.*;
import telran.cars.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class RentCompanyStatisticsTests {

    IRentCompany rentCompany;

    @BeforeEach
    void setUp() throws Exception {

        rentCompany = new RentCompanyEmbedded();
        setUpStatistics();

    }

    @Test
    void ModelsTest() {
        int gasTank = 50;
        int price = 50;
        Model model_1 = new Model("modelName_2",
        		gasTank, "company_1", "country_1",
        		price);
        Model model_2 = new Model("modelName_3",
        		gasTank, "company_1", "country_1", price);

        assertEquals(CarsReturnCode.OK, rentCompany.addModel(model_1));
        assertEquals(CarsReturnCode.MODEL_EXISTS, rentCompany.addModel(model_1));

        assertEquals(CarsReturnCode.OK, rentCompany.addModel(model_2));

        assertEquals(null, rentCompany.getModel("NotExistingModel"));
        assertEquals(model_1, rentCompany.getModel("modelName_2"));
    }

    @Test
    void DriversTest() {
        Driver driver = new Driver(1231l, "name1", 1970, "phone1");
        assertEquals(CarsReturnCode.OK, rentCompany.addDriver(driver));
        assertEquals(CarsReturnCode.DRIVER_EXISTS, rentCompany.addDriver(driver));
    }

    private void setUpStatistics() {
        String modelName_zap = "A965";
        String modelName_mers = "s221";
        String modelName_bmv = "X5";

        createModels(modelName_zap, modelName_mers, modelName_bmv);
        createCars(modelName_zap, modelName_mers, modelName_bmv);
        createDrivers();
        rentReturn();
    }

    private void rentReturn() {
        rentCompany.rentCar("z123-1", 54562545,
        		LocalDate.of(2010, 5, 12), 15);
        rentCompany.returnCar("z123-1",
        		LocalDate.of(2010, 5, 27), 15, 10);

        rentCompany.rentCar("z123-3",
        		54562545, LocalDate.of(2014, 8, 3),
        		25);
        rentCompany.returnCar("z123-3",
        		LocalDate.of(2014, 8, 27), 100, 19);

        rentCompany.rentCar("m324-2",
        		54562545, LocalDate.of(2017, 12, 27), 4);
        rentCompany.returnCar("m324-2",
        		LocalDate.of(2017, 12, 30), 5, 0);
// --------------------------------------------------------------------------------------------------------------------------
        rentCompany.rentCar("b566", 12354789,
        		LocalDate.of(2012, 4, 1), 25);
        rentCompany.returnCar("b566",
        		LocalDate.of(2012, 4, 26), 75, 10);

        rentCompany.rentCar("m324-1", 12354789,
        		LocalDate.of(2015, 7, 7), 15);
        rentCompany.returnCar("m324-1",
        		LocalDate.of(2015, 7, 22), 100, 5);
// --------------------------------------------------------------------------------------------------------------------------
        rentCompany.rentCar("z123-2", 78978979, LocalDate.of(2011, 7, 3), 150);
        rentCompany.returnCar("z123-2", LocalDate.of(2011, 12, 4), 100, 5);

        rentCompany.rentCar("m324-1", 78978979, LocalDate.of(2012, 2, 15), 92);

        rentCompany.returnCar("m324-1", LocalDate.of(2012, 2, 15).plus(92, ChronoUnit.DAYS), 69, 29);

        rentCompany.rentCar("b566", 78978979, LocalDate.of(2018, 1, 1), 350);
    }

    private void createDrivers() {
        Driver petya = new Driver(78978979, "Petya", 1988, "+97253555555");
        Driver moshe = new Driver(12354789, "Moshe", 1978, "+97254325233");
        Driver ivan = new Driver(54562545, "Ivan", 1999, "+972844748748");

        rentCompany.addDriver(petya);
        rentCompany.addDriver(moshe);
        rentCompany.addDriver(ivan);
    }

    private void createCars(String modelName_zap, String modelName_mers, String modelName_bmv) {
        Car zap1 = new Car("z123-1", "white", modelName_zap);
        Car zap2 = new Car("z123-2", "red", modelName_zap);
        Car zap3 = new Car("z123-3", "yellow", modelName_zap);
        Car mercedes1 = new Car("m324-1", "black", modelName_mers);
        Car mercedes2 = new Car("m324-2", "green", modelName_mers);
        Car bmw1 = new Car("b566", "blue", modelName_bmv);

        rentCompany.addCar(zap1);
        rentCompany.addCar(zap2);
        rentCompany.addCar(zap3);
        rentCompany.addCar(mercedes1);
        rentCompany.addCar(mercedes2);
        rentCompany.addCar(bmw1);
    }

    private void createModels(String modelName_zap, String modelName_mers, String modelName_bmv) {

        Model zap = new Model(modelName_zap, 40, "ZAZ", "Russia", 35);
        Model mers = new Model(modelName_mers, 60, "Mercedes", "Germany", 65);
        Model bmw = new Model(modelName_bmv, 80, "BMW", "Germany", 100);
        rentCompany.addModel(zap);
        rentCompany.addModel(mers);
        rentCompany.addModel(bmw);
    }

    @Test
    void getAllModelNamesTest() {
        assertEquals(3, rentCompany.getAllModelNames().size());
        assertEquals("A965", rentCompany.getAllModelNames().get(0));
        assertEquals("A965", rentCompany.getAllModelNames().get(0));

        List<String> modelList = rentCompany.getAllModelNames();

        assertEquals(modelList, rentCompany.getAllModelNames());

    }

    @Test
    void getModelProfitTest() {
        assertEquals(6650, rentCompany.getModelProfit("A965"));
        assertEquals(7215, rentCompany.getModelProfit("s221"));
        assertEquals(37500, rentCompany.getModelProfit("X5"));
    }

    @Test
    void getMostPopularModelNamesTests() {
        assertEquals(2, rentCompany.getMostPopularModelNames().size());
        assertEquals("A965", rentCompany.getMostPopularModelNames().get(0));
        assertEquals("s221", rentCompany.getMostPopularModelNames().get(1));
    }

    @Test
    void getMostProfitModelNamesTest() {
        assertEquals(1, rentCompany.getMostProfitModelNames().size());
        assertEquals("X5", rentCompany.getMostProfitModelNames().get(0));
        System.out.println(rentCompany.getMostProfitModelNames());
    }
}