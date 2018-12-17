package telran.currency.domain;

import java.time.LocalDate;
import java.util.Map;

public class CurrencyDate {
	public LocalDate date;
	public Map<String, Float> rates;
	
	
	public CurrencyDate() {
		
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public void setRates(Map<String, Float> rates) {
		this.rates = rates;
	}


	public Map<String, Float> getRates() {
		return rates;
	}


	@Override
	public String toString() {
		return "CurrencyDate [date=" + date + ", rates=" + rates + "]";
	}
	
	
}
