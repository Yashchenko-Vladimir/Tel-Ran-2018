package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class LoginItem extends CarsItem {

	public LoginItem(InputOutput io, IRentCompany company) {
		super(io, company);
	}

	@Override
	public String displayedName() {
		
		return "Login";
	}

	@Override
	public void perform() {
		String username = io.inputString("Enter username");
		String password = io.inputString("Enter password");
		company.login(username, password);

	}

}
