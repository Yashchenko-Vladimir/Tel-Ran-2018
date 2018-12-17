package telran.security.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import telran.security.dto.AccountDto;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.*;
import static telran.security.dto.AccountingApiConstants.*;


public class AccountingManagementImpl implements IAccountingManagement {


    private RestTemplate template = new RestTemplate();
    private String hostname;
    private int port;
    private HttpHeaders headers=new HttpHeaders();
    
	public AccountingManagementImpl(String hostname, int port) {
	        this.hostname = hostname;
	        this.port = port;
	}
	
	
    private <T, E> T sendRequest(E e, HttpMethod httpMethod, String url, ParameterizedTypeReference<T> typeReference) {
        HttpEntity<E> httpEntity = null;
        
        httpEntity=e!=null?new HttpEntity<>(e,headers):
        	new HttpEntity<>(headers);
        return template.exchange(hostname + port + url, httpMethod, httpEntity, typeReference).getBody();
    }

    @Override
    public boolean addAccount(AccountDto account) {
        return sendRequest(account, POST, ADD_ACCOUNT,
        		new ParameterizedTypeReference<Boolean>() {
        });
    }

    @Override
    public boolean removeAccount(String username) {
        return sendRequest(null, DELETE, REMOVE_ACCOUNT +"?"+
    USERNAME+"="+username,
    new ParameterizedTypeReference<Boolean>() {
        });
    }

    @Override
    public boolean addRole(String username, String role) {
        Map<String, String> map = new HashMap<>();
        map.put(USERNAME, username);
        map.put(ROLE_PARAM, role);
        return sendRequest(map, POST, ADD_ROLE, new ParameterizedTypeReference<Boolean>() {
        });
    }

    @Override
    public boolean removeRole(String username, String role) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("role", role);
        return sendRequest(null, DELETE,
        		REMOVE_ROLE+"?"+USERNAME+"="+username+"&"
        +ROLE_PARAM+"="+role,
        		new ParameterizedTypeReference<Boolean>() {
        });
    }

    @Override
    public boolean updatePassword(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put(USERNAME, username);
        map.put("password", password);
        return sendRequest(map, POST, UPDATE_PASSWORD, new ParameterizedTypeReference<Boolean>() {
        });
    }

    @Override
    public boolean revokeAccount(String username) {
    	HttpEntity<String> requestEntity=new HttpEntity<>(username);
        return sendRequest(requestEntity, POST, REVOKE_ACCOUNT , new ParameterizedTypeReference<Boolean>() {
        });
    }

    @Override
    public boolean activateAccount(String username) {
    	HttpEntity<String> requestEntity=new HttpEntity<>(username);
        return sendRequest(requestEntity, POST, ACTIVATE_ACCOUNT, new ParameterizedTypeReference<Boolean>() {
        });
    }

	@Override
	public boolean login(String username, String password) {
		String userPassword = username + ":" + password;//"moshe:54321^Com";
		headers = new HttpHeaders();
        headers.add("Authorization","Basic "+
        		Base64.getEncoder().encodeToString
        		(userPassword.getBytes()));
		return true;
	}
    
    
}
