package telran.security.controller.items;

import java.util.Base64;

import org.springframework.http.HttpHeaders;

import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;

public class LoginItem extends AccountItem {

	public LoginItem(InputOutput io, IAccountingManagement service) {
		super(io, service);
		
	}

	@Override
	public String displayedName() {
		return "Login";
	}

	@Override
	public void perform() {
		String username = io.inputString("Enter username");
		String password = io.inputString("Enter password");
		service.login(username, password);
	}

}
