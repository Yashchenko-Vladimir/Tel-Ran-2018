package telran.currencyexchange.dto;

public class CurrencyDataJson {
	public int amount;
	public String from;
	public String to;
	
	public CurrencyDataJson() {
		
	}
	
	public int getAmoount() {
		return amount;
	}
	@Override
	public String toString() {
		return "CurrencyDataJson [amount=" + amount + ", from=" + from + ", to=" + to + "]";
	}

	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
}
