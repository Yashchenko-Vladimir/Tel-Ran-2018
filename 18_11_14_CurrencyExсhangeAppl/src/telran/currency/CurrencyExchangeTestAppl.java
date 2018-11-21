package telran.currency;

import java.util.Currency;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import telran.currency.items.CurrencyExchangeItem;
import telran.currency.items.CurrencyItem;
import telran.currency.items.DisplayDateItem;
import telran.currency.items.RefreshItem;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class CurrencyExchangeTestAppl {

	public static void main(String[] args) {
		RestTemplate rest = new RestTemplate();
		String url = "http://data.fixer.io/api/2018-01-01?access_key=81ebf276e1ed808b58591b5fb05c34eb";
		ResponseEntity<CurrencyDate> response = rest.exchange(url,  HttpMethod.GET, null, CurrencyDate.class);
		
		InputOutput io = new ConsoleInputOutput();
		
		Item[] items = {
				new DisplayDateItem(io, response.getBody()),
				new CurrencyExchangeItem(io, response.getBody()),
				new RefreshItem(io, response.getBody())
				};
		Menu menu = new MenuWithExit(io, items);
		menu.runMenu();
		
	

	}

}
