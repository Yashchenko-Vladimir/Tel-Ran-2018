package telran.account.dto;

public class AccountRole {
	public String username;
	public String role;
	
	public AccountRole() {}

	public AccountRole(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}
	
	
}
