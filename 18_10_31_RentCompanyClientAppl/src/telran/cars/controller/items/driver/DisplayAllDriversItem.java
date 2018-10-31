package telran.cars.controller.items.driver;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllDriversItem extends CarsItem {
    public DisplayAllDriversItem(InputOutput io,
    		IRentCompany rentCompany) {
        super(io, rentCompany);
    }

    @Override
    public String displayedName() {
        return "Display all drivers";
    }

    @Override
    public void perform() {
        company.getAllDrivers()
        .forEach(io::outputLine);
    }
}
