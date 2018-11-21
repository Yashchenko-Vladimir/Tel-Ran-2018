package telran.currencyexchange.service;

import java.util.Set;

public interface ICurExchange {
	
	public Set<String> getAllCurrency();
	public double currencyBaseOfEuro(String currency);
	public double convert(int amount, String from, String to);
	public void refresh();
	
	
}
