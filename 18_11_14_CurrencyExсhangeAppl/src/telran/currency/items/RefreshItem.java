package telran.currency.items;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import telran.currency.CurrencyDate;
import telran.view.InputOutput;

public class RefreshItem extends CurrencyItem {

	public RefreshItem(InputOutput io, CurrencyDate currencyDate) {
		super(io, currencyDate);
		
	}

	@Override
	public String displayedName() {
		
		return "Update database";
	}

	@Override
	public void perform() {
		updateDatabase();
		io.outputLine("Database updated");

	}

	private void updateDatabase() {
		
		RestTemplate rest = new RestTemplate();
		String url = "http://data.fixer.io/api/latest?access_key=81ebf276e1ed808b58591b5fb05c34eb";
		ResponseEntity<CurrencyDate> response = rest.exchange(url,  HttpMethod.GET, null, CurrencyDate.class);
		currencyDate.setDate(response.getBody().getDate());
		currencyDate.setRates(response.getBody().getRates());
	}

}
