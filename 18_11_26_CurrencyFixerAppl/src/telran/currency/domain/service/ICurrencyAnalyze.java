package telran.currency.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import telran.currency.domain.Currency;

public interface ICurrencyAnalyze {
	void addCurrencies(Iterable<Currency> currencies);
	Map<Integer, Float> getCurrencyAvgYears(String currency);
	Map<String, Float> getCurrencyValues(String currency);
	Map<String, Float> getCurrencyValuesDates(String currency, LocalDate from, LocalDate to);
	LocalDate getDateMinValue(String currency);
	LocalDate getDateMaxValue(String currency);
	Map<String, Float> getMaxValues(String currency);
	Map<String, Float> getMinValues(String currency);
	double getDeltaBetweenDates(String currency, LocalDate from, LocalDate to);
}
