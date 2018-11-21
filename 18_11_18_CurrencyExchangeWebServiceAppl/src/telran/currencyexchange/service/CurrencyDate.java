package telran.currencyexchange.service;

import java.util.Map;

public class CurrencyDate {
	
	public Map<String, Double> rates;
		
	
	public CurrencyDate() {
		
	}


	@Override
	public String toString() {
		return "CurrencyDate [rates=" + rates + "]";
	}



	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}


	public Map<String, Double> getRates() {
		return rates;
	}


	
	
}
