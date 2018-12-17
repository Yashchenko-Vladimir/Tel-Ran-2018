package telran.security.dto;

public class AccountDto {
	
	String username;
	String password;
	String [] roles;
	
	public AccountDto() {}
	
	public AccountDto(String username, String password, String [] roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String [] getRoles() {
		return roles;
	}
	
	

}
