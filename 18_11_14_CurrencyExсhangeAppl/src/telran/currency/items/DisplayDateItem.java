package telran.currency.items;

import telran.currency.CurrencyDate;
import telran.view.InputOutput;

public class DisplayDateItem extends CurrencyItem {

	public DisplayDateItem(InputOutput io, CurrencyDate currencyDate) {
		super(io, currencyDate);
		
	}

	public String displayedName() {
		
		return "Display currunt date";
	}

	public void perform() {
		io.outputLine(currencyDate.getDate());

	}

}
