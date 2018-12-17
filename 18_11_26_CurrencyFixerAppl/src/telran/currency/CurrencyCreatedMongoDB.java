package telran.currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Multiset.Entry;

import telran.currency.domain.Currency;
import telran.currency.domain.CurrencyDate;
import telran.currency.domain.service.ICurrencyAnalyze;
import telran.currency.repo.CurrenciesMongoRepository;

@SpringBootApplication
public class CurrencyCreatedMongoDB {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(CurrencyCreatedMongoDB.class, args);
		List<Currency> currencyList = getListCurrencies();
//		ICurrencyAnalyze currencies = ctx.getBean(ICurrencyAnalyze.class);
//		currencies.addCurrencies(currencyList);
		CurrenciesMongoRepository currencies = ctx.getBean(CurrenciesMongoRepository.class);
		currencies.saveAll(currencyList);
		ctx.close();
		

	}

	private static List<Currency> getListCurrencies() {
		RestTemplate rest = new RestTemplate();
		List<Currency> currencyList = new ArrayList<>();
		LocalDate start = LocalDate.of(2000, 1, 1);
		LocalDate finish = LocalDate.now();
		
		for(LocalDate i = start; i.isBefore(finish); i = i.plusMonths(1)){
			List<Currency> current = getCurreniesListFromFixer(rest, i);
			currencyList.addAll(current);
		}
		
		List<Currency> current = getCurreniesListFromFixer(rest, finish);
		currencyList.addAll(current);
		
		return currencyList;
	}

	private static List<Currency> getCurreniesListFromFixer(RestTemplate rest, LocalDate i) {
		String url = makeUrl(i);
		ResponseEntity<CurrencyDate> response = rest.exchange(url,  HttpMethod.GET, null, CurrencyDate.class);
		List<Currency> current = getCurrentCurrencise(response.getBody());
		return current;
	}

	private static List<Currency> getCurrentCurrencise(CurrencyDate body) {
		List<Currency> list = new ArrayList<>();
		LocalDate date = body.getDate();
		for(Map.Entry<String, Float> set : body.getRates().entrySet()) {
			list.add(new Currency(set.getKey(), date, set.getValue()));
		}
		return list;
	}

	private static String makeUrl(LocalDate date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateCurrent = date.format(format);
		System.out.println(dateCurrent);
		return "http://data.fixer.io/api/" + dateCurrent + "?access_key=5862521b2688beae49078de0575ca140";
	}

}
