package telran.currency.domain;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "currencies")
public class Currency {
	
	@Indexed(unique = false)
	String currencyName;
	LocalDate date;
	float value;
	
	public Currency() {
		
	}
	
	public Currency(String currencyName, LocalDate date, float value) {
		super();
		this.currencyName = currencyName;
		this.date = date;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Currency [currencyName=" + currencyName + ", date=" + date + ", value=" + value + "]";
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public LocalDate getDate() {
		return date;
	}
	public float getValue() {
		return value;
	}
	
	
	
	
}
