package telran.security.accounting.mongo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Queue;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accountSecurity")
public class Account {
	
	@Id
	String username;
	String password_hash;
	Queue<String> last_password_hashes; //three last hashes
	LocalDate activation_date;
	boolean revoked;
	Set<String> roles;
	
	public Account() {}
	public Account(String username, String password_hash, Queue<String> last_password_hashes, LocalDate activation_date,
			boolean revoked, Set<String> roles) {
		this.username = username;
		this.password_hash = password_hash;
		this.last_password_hashes = last_password_hashes;
		this.activation_date = activation_date;
		this.revoked = revoked;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public Queue<String> getLast_password_hashes() {
		return last_password_hashes;
	}
	public LocalDate getActivation_date() {
		return activation_date;
	}
	public boolean isRevoked() {
		return revoked;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public void setLast_password_hashes(Queue<String> last_password_hashes) {
		this.last_password_hashes = last_password_hashes;
	}
	public void setActivation_date(LocalDate activation_date) {
		this.activation_date = activation_date;
	}
	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", password_hash=" + password_hash + ", last_password_hashes="
				+ last_password_hashes + ", activation_date=" + activation_date + ", revoked="
				+ revoked + ", roles=" + roles + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activation_date == null) ? 0 : activation_date.hashCode());
		result = prime * result + ((last_password_hashes == null) ? 0 : last_password_hashes.hashCode());
		result = prime * result + ((password_hash == null) ? 0 : password_hash.hashCode());
		result = prime * result + (revoked ? 1231 : 1237);
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (activation_date == null) {
			if (other.activation_date != null)
				return false;
		} else if (!activation_date.equals(other.activation_date))
			return false;
		if (last_password_hashes == null) {
			if (other.last_password_hashes != null)
				return false;
		} else if (!last_password_hashes.equals(other.last_password_hashes))
			return false;
		if (password_hash == null) {
			if (other.password_hash != null)
				return false;
		} else if (!password_hash.equals(other.password_hash))
			return false;
		if (revoked != other.revoked)
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	

}
