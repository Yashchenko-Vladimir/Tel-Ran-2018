package telran.cars.controller.items.driver;
import telran.cars.controller.items.CarsItem;
import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayDriverItem extends CarsItem {
    public DisplayDriverItem(InputOutput io, IRentCompany company) {
        super(io, company);
    }

    @Override
    public String displayedName() {
        return "Get driver info by licenseId";
    }

    @Override
    public void perform() {
        Long licenseId = io.inputLong("Enter driver licenseId");
        if (licenseId == null)
            return;
        Driver driver = company.getDriver(licenseId);
        if (driver != null)
            io.outputLine(driver);
        else io.outputLine("No driver with that licenseId: " + licenseId);
    }
}
