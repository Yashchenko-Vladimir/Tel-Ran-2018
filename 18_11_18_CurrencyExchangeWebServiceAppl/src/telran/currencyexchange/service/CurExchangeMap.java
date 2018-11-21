package telran.currencyexchange.service;




import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



//@Service

@ManagedResource // managed bean MBean comunicating whith RMI service
public class CurExchangeMap implements ICurExchange{
	private static final String URL = "http://data.fixer.io/api/2018-01-01?access_key=81ebf276e1ed808b58591b5fb05c34eb";
	private Map<String, Double> currencyMap;
	private Instant timestamp;
	
	@Value("${period:3600}")
	long updateTime;
	
	@ManagedAttribute
	public long getUpdateTime() {
		return updateTime;
	}
	
	@ManagedAttribute
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
//	public CurExchangeMap() { // It's not bean yet
//		System.out.println("period= " + updateTime);
//	}
//	
	@PostConstruct
	void displayStartPeriod() {
		System.out.println("start period= " + updateTime);
	}
	
	@PreDestroy
	void displayFinishPeriod() {
		System.out.println("finish period= "+ updateTime);
	}
	
//	static {
//		RestTemplate rest = new RestTemplate();
//		String url = "http://data.fixer.io/api/2018-01-01?access_key=81ebf276e1ed808b58591b5fb05c34eb";
//		ResponseEntity<CurrencyDate> response = rest.exchange(url,  HttpMethod.GET, null, CurrencyDate.class);
//		currencyMap = response.getBody().rates;
//		timestamp = Instant.now();
//	}
	

	@Override
	public Set<String> getAllCurrency() {
		getRates();
		return currencyMap.keySet();
	}

private void getRates() {
	if(timestamp == null || ChronoUnit.SECONDS.between(timestamp, Instant.now()) > updateTime)
		refresh();
}

	@Override
	public double currencyBaseOfEuro(String currency) {
		getRates();
		Double result = currencyMap.get(currency);
		if(result == null)
			throw new RuntimeException(currency + " Unsupported currency");
		return result;
	}

	@Override
	public double convert(int amount, String from, String to) {
		getRates();
		if(!currencyMap.containsKey(from))
			throw new RuntimeException(from + " Unsupported currency");
		if(!currencyMap.containsKey(to))
			throw new RuntimeException(to + " Unsupported currency");
		return ((double) amount) / currencyMap.get(from) * currencyMap.get(to);
	}

	@Override
	public void refresh() {
		System.out.println("Update database from Fixer");
		RestTemplate rest = new RestTemplate();
		String url = URL;
		ResponseEntity<CurrencyDate> response = rest.exchange(url,  HttpMethod.GET, null, CurrencyDate.class);
		currencyMap = response.getBody().rates;
		timestamp = Instant.now();
	}
		
}
