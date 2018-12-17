package telran.currency.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.currency.domain.service.ICurrencyAnalyze;

@RestController
public class CarrenciesController {
	@Autowired
	ICurrencyAnalyze currencise;
	
	@GetMapping("/currency/avgyears/{currency}")
	Map<Integer,Float> getCurrencyAverageYears(@PathVariable("currency") String currency){
		return currencise.getCurrencyAvgYears(currency);
	}
	
	@GetMapping("/currency/dateminvalue/{currency}")
	LocalDate getDateMinValue(@PathVariable("currency") String currency) {
		return currencise.getDateMinValue(currency);
	}
	
	@GetMapping("/currency/datemaxvalue/{currency}")
	LocalDate getDateMaxValue(@PathVariable("currency") String currency) {
		return currencise.getDateMaxValue(currency);
	}
	
	@GetMapping("/currency/delta/{currency}")
	double getDeltaBetweenDates(@PathVariable("currency") String currency, 
			@RequestParam("fromDate") String from, @RequestParam("toDate") String to) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return currencise.getDeltaBetweenDates(currency, LocalDate.parse(from, format), LocalDate.parse(to, format));
	}
	
	@GetMapping("/currency/value/{currency}")
	Map<String, Float> getCurrencyValues(@PathVariable("currency") String currency){
		return currencise.getCurrencyValues(currency);
	}
	
	@GetMapping("/currency/getvaluedate/{currency}")
	 Map<String, Float> getCurrencyValuesDates(@PathVariable("currency") String currency, 
			@RequestParam(name ="fromDate", defaultValue = "2000-01-01") String from,
			@RequestParam(name ="toDate", defaultValue = "2018-11-01") String to) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return currencise.getCurrencyValuesDates(currency, LocalDate.parse(from, format), LocalDate.parse(to, format));
	}
	@GetMapping("currency/minvalues/{currency}")
	Map<String, Float> getMinValues(@PathVariable("currency") String currency){
		return currencise.getMinValues(currency);
	}
	@GetMapping("currency/maxvalues/{currency}")
	Map<String, Float> getMaxValues(@PathVariable("currency") String currency){
		return currencise.getMaxValues(currency);
	}
}
