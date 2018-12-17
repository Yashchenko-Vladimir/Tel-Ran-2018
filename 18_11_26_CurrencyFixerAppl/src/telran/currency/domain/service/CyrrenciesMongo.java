package telran.currency.domain.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;

import telran.currency.domain.Currency;
import telran.currency.repo.CurrenciesMongoRepository;
@Service
public class CyrrenciesMongo implements ICurrencyAnalyze {
	@Autowired
	CurrenciesMongoRepository currenciesRepository;

	@Override
	public void addCurrencies(Iterable<Currency> currencies) {
		currenciesRepository.saveAll(currencies);

	}

	@Override
	public Map<Integer, Float> getCurrencyAvgYears(String currency) {
//		Map<Integer, Double> dd = currenciesRepository.findByCurrencyName(currency).stream().
//				collect(Collectors.groupingBy(x -> x.getDate().getYear(), 
//				Collectors.averagingDouble(x-> x.getValue())));
		Map<Integer, Double> dd = currenciesRepository.findByCurrencyName(currency).stream().
				collect(Collectors.groupingBy(x-> x.getDate().getYear(), TreeMap::new, Collectors.averagingDouble(x-> x.getValue())));
		
		return dd.entrySet().stream().collect(Collectors.toMap(x-> x.getKey(), x -> x.getValue().floatValue()));
		
	}

	@Override
	public Map<String, Float> getCurrencyValues(String currency) {
		List<Currency> listCur = currenciesRepository.findByCurrencyName(currency);
		return listCur.stream().collect(Collectors.toMap(x -> x.getDate().toString(), Currency::getValue));
	}

	@Override
	public Map<String, Float> getCurrencyValuesDates(String currency, LocalDate from, LocalDate to) {
		List<Currency> list = currenciesRepository.findByCurrencyNameAndDateBetween(currency, from, to);
		return list.stream().collect(Collectors.toMap(x->x.getDate().toString(), x -> x.getValue(), (a,b)-> a, TreeMap::new));
	}

	@Override
	public LocalDate getDateMinValue(String currency) {
		List<Currency> listCur = currenciesRepository.findByCurrencyName(currency);
		Double min = listCur.stream().mapToDouble(Currency::getValue).min().getAsDouble();
		Currency cur = listCur.stream().filter(x -> x.getValue() == min).findFirst().get();
		return cur.getDate();
	}

	@Override
	public LocalDate getDateMaxValue(String currency) {
		List<Currency> listCur = currenciesRepository.findByCurrencyName(currency);
		Double min = listCur.stream().mapToDouble(x -> x.getValue()).max().getAsDouble();
		Currency cur = listCur.stream().filter(x -> x.getValue() == min).findFirst().get();
		return cur.getDate();
	}

	@Override
	public double getDeltaBetweenDates(String currency, LocalDate from, LocalDate to) {
		List<Currency> listCur = currenciesRepository.findByCurrencyName(currency);
		double fromValue = listCur.stream().filter(x -> x.getDate().equals(from)).
				mapToDouble(x -> x.getValue()).findFirst().getAsDouble();
		double toValue = listCur.stream().filter(x -> x.getDate().equals(to)).
				mapToDouble(x -> x.getValue()).findFirst().getAsDouble();
		return fromValue - toValue;
	}

	@Override
	public Map<String, Float> getMaxValues(String currency) {
		List<Currency> listCur = currenciesRepository.findByCurrencyName(currency);
		Double max = listCur.stream().mapToDouble(x -> x.getValue()).max().getAsDouble();
		Map<String, Float> cur = listCur.stream().filter(x -> x.getValue() == max).
				collect(Collectors.toMap(x-> x.getDate().toString(), x -> x.getValue(), (a,b)-> a, TreeMap::new));
		return cur ;
	}

	@Override
	public Map<String, Float> getMinValues(String currency) {
		List<Currency> listCur = currenciesRepository.findByCurrencyName(currency);
		Double min = listCur.stream().mapToDouble(x -> x.getValue()).min().getAsDouble();
		Map<String, Float> cur = listCur.stream().filter(x -> x.getValue() == min).
				collect(Collectors.toMap(x-> x.getDate().toString(), x -> x.getValue(), (a,b)-> a, TreeMap::new));
		return cur ;
		
	}

}
