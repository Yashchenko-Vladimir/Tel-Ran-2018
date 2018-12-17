package telran.account.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import telran.account.dto.AccountDto;

public class AccountingManagerWebProxy implements IAccountingManagement {
	private String hostname;
	private int port;
	private RestTemplate rest = new RestTemplate();
	
	

	public AccountingManagerWebProxy(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}
	
	private String makeUrl(String ... str) {
		String url = null;
		if(port == 0) {
			url = "https://" + hostname  + str[0];
		} else {
			url = "http://" + hostname + ":" + port + str[0];
		}
		if(str.length == 2) {
			url +="/" + str[1];
		}
		return url;
	}
	
	private <T, E> T sendPOST(String url,  E body, ParameterizedTypeReference<T> typeT) {
		HttpEntity<E> requestEntity = new HttpEntity<E>(body);
		ResponseEntity<T> response  = rest.exchange(url, HttpMethod.POST, requestEntity, typeT);
		return  response.getBody();
	}

	@Override
	public boolean addAccount(AccountDto account) {
		String url = makeUrl("/account/add");
		return sendPOST(url, account, new ParameterizedTypeReference<Boolean>() {});
	}

	@Override
	public boolean removeAccount(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRole(String username, String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRole(String username, String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean revokeAccount(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean activateAccount(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
