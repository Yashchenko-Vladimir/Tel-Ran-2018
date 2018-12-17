package telran.security.accounting.management;

import telran.security.dto.AccountDto;

public interface IAccountingManagement {
	
	public boolean addAccount(AccountDto account);
	public boolean removeAccount(String username);
	public boolean addRole(String username,String role);
	public boolean removeRole(String username, String role);
	public boolean updatePassword(String username, String password);
	public boolean revokeAccount(String username);
	public boolean activateAccount(String username);

}
