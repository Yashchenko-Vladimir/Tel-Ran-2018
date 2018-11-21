package telran.currency.items;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import telran.currency.CurrencyDate;
import telran.view.InputOutput;

public class CurrencyExchangeItem extends CurrencyItem {

	public CurrencyExchangeItem(InputOutput io, CurrencyDate currencyDate) {
		super(io, currencyDate);
	}

	public String displayedName() {
		return "Convert currency";
	}

	public void perform() {
		Map<String, Double> rates = currencyDate.getRates();
		List<String> currencyList = rates.entrySet().stream().map(e -> e.getKey()).
				collect(Collectors.toList());
		
		String firstCurrency = io.inputString("Input currency from exchange", currencyList);
		String secondCurrency = io.inputString("Input currency to exchange", currencyList);
		int quantity = io.inputInteger("Input quantity currency");
		double res = quantity / rates.get(firstCurrency) * rates.get(secondCurrency);
		io.outputLine(String.format("%.2f", res));
		

	}

}
