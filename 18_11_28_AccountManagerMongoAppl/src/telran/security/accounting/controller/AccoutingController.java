package telran.security.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.security.accounting.management.IAccountingManagement;
import telran.security.dto.AccountRole;
import telran.security.dto.AccountUpdatePassword;
import telran.security.dto.AccountUsername;
import telran.security.dto.AccountDto;

@RestController
public class AccoutingController {

	@Autowired
	IAccountingManagement accountManager;
	
	@PostMapping("/account/add")
	boolean addAcount(@RequestBody AccountDto account) {
		return accountManager.addAccount(account);
	}
	
	@DeleteMapping("/account/delete/{username}")
	boolean removeAccount(@PathVariable("username") String username) {
		return accountManager.removeAccount(username);
	}
	
	@PostMapping("/account/add_role")
	boolean addRole(@RequestBody AccountRole roleAc) {
		return accountManager.addRole(roleAc.getUsername(), roleAc.getRole());
	}
	
	@PostMapping("/account/delete_role")
	boolean removeRole(@RequestBody AccountRole roleaAc) {
		return accountManager.removeRole(roleaAc.getUsername(), roleaAc.getRole());
	}
	
	@PostMapping("/account/update_password")
	boolean updatePassword(@RequestBody AccountUpdatePassword up) {
		return accountManager.updatePassword(up.getUsername(), up.getPassword());
	}
	
	@PostMapping("/account/revoke")
	boolean revokeAccount(@RequestBody AccountUsername user) {
		return accountManager.revokeAccount(user.getUsername());
	}
	
	@PostMapping("/account/activate")
	boolean activateAccount(@RequestBody AccountUsername user) {
		return accountManager.activateAccount(user.getUsername());
	}
	
}
