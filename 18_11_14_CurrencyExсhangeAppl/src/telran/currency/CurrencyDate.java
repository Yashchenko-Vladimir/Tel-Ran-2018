package telran.currency;

import java.util.Map;

public class CurrencyDate {
	public String date;
	public Map<String, Double> rates;
	
	
	public CurrencyDate() {
		
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}


	public Map<String, Double> getRates() {
		return rates;
	}


	@Override
	public String toString() {
		return "CurrencyDate [date=" + date + ", rates=" + rates + "]";
	}
	
	
}
