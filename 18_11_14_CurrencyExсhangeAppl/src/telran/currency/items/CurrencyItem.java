package telran.currency.items;


import telran.currency.CurrencyDate;
import telran.view.InputOutput;
import telran.view.Item;

public abstract class CurrencyItem implements Item {

	protected InputOutput io;
	protected CurrencyDate currencyDate;
	
	
	public CurrencyItem(InputOutput io, CurrencyDate currencyDate) {
		this.io = io;
		this.currencyDate = currencyDate;
	}
	
}
