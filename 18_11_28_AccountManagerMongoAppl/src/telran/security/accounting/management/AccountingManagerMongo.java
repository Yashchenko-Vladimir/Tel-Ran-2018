package telran.security.accounting.management;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.security.accounting.configuration.BCPasswordEncoder;
import telran.security.accounting.mongo.Account;
import telran.security.accounting.repo.AccountingMongoRepository;
import telran.security.dto.AccountDto;

@Service
public class AccountingManagerMongo implements IAccountingManagement {
	final private int CAPITAL_LETTER = 1<<0;
	final private int LOWER_LETTER = 1 <<1;
	final private int DIGIT = 1 << 2;
	final private int SPEC_SYMBOL = 1<<3;
	final private List<Character> specSymbol = Arrays.asList
			('!', '@', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '#', '~');
	
	
	@Autowired
	AccountingMongoRepository accountRepository;
	
	@Autowired
	BCPasswordEncoder coder;
	
	@Override
	public boolean addAccount(AccountDto account) {

		if ((account.getUsername().isEmpty()) && !checkUsername(account.getUsername())) {
			return false;
		}
		String username = account.getUsername();

		if (!checkPassword(account.getPassword())) {
			return false;
		}
		String password_hash = coder.getPasswordEncoder().encode(account.getPassword());
		Queue<String> last_password_hashes = new LinkedList<>(Arrays.asList(password_hash));
		LocalDate activation_date = null;
		boolean revoked = false;
		Set<String> roles = getRoles(account.getRoles());
		Account res = new Account(username, password_hash, last_password_hashes, activation_date, revoked, roles);
		accountRepository.save(res);
		return true;
	}

	private boolean checkUsername(String username) {
		return accountRepository.findById(username).equals(null);
		
		
	}

	private boolean checkPassword(String password) {
		if(password.length() < 8) return false;
		int flagNumber = 0;
		for(char ch : password.toCharArray()) {
			if (Character.isUpperCase(ch)) 
				flagNumber |=CAPITAL_LETTER;
			else if (Character.isLowerCase(ch))		
				flagNumber |= LOWER_LETTER;
			else if (Character.isDigit(ch))	 	
				flagNumber |= DIGIT;
			else if (specSymbol.contains(ch))   
				flagNumber |= SPEC_SYMBOL;
		    
	}
		
		return flagNumber == 15;
	}

	
	private Set<String> getRoles(String [] roles) {
		Set<String> res = new HashSet<>();
		res.addAll(Arrays.asList(roles));
		return res;
	}

	@Override
	public boolean removeAccount(String username) {
		if(username.isEmpty()&&checkUsername(username)) {
			return false;
		}
		Account res = accountRepository.findByUsername(username);
		accountRepository.delete(res);
		return true;
	}

	@Override
	public boolean addRole(String username, String role) {
		if(!checkUsername(username)) {
		Account res = accountRepository.findByUsername(username);
			Set<String> set = res.getRoles();
			if (!set.contains(role)) {
				set.add(role);
				res.setRoles(set);
				accountRepository.save(res);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeRole(String username, String role) {
		if(!checkUsername(username)) {
			Account res = accountRepository.findByUsername(username);
			Set<String> set = res.getRoles();
			boolean fl = set.remove(role);
			if(fl) {
				res.setRoles(set);
				accountRepository.save(res);
			}
			return fl;
		}
		return false;
	}

	@Override
	public boolean updatePassword(String username, String password) {
		if(!checkUsername(username)) {
			Account res = accountRepository.findByUsername(username);
			
			if (checkPassword(password)) {
				System.out.println(res.getLast_password_hashes());
				Queue<String> pass = res.getLast_password_hashes();
				System.out.println("OK");
				PasswordEncoder cod = coder.getPasswordEncoder();
				for (String qu : pass) {
					if (cod.matches(password, qu)) 
						System.out.println("OK");
						return false;
				}
				res.setPassword_hash(cod.encode(password));
				pass.offer(res.getPassword_hash());
				if(pass.size()> 3)
					pass.remove();
//				for (int i = pass.length-1; i > 0; i--) {
//					pass[i] = pass[i - 1];
//				}
//				pass[0] = res.getPassword_hash();
				res.setLast_password_hashes(pass);
				accountRepository.save(res);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean revokeAccount(String username) {
		boolean fl = false;
		Account res = accountRepository.findByUsername(username);
		if(res!=null) {
			if(!res.isRevoked()) {
				res.setRevoked(true);
				accountRepository.save(res);
				fl = true;
			}
		}
		return fl;
	}

	@Override
	public boolean activateAccount(String username) {
		Account res = accountRepository.findByUsername(username);
		if(res!=null) {
			LocalDate activDate = res.getActivation_date();
			if(activDate==null) {
				res.setActivation_date(LocalDate.now());
				accountRepository.save(res);
				return true;
			}
		}
		return false;
	}

}
