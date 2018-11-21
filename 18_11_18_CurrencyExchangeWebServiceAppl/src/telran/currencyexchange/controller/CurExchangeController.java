package telran.currencyexchange.controller;

import static telran.currencyexchange.dto.CurExchangeApi.CODE;
import static telran.currencyexchange.dto.CurExchangeApi.CONVERT;
import static telran.currencyexchange.dto.CurExchangeApi.CURRENCY_VALUE;
import static telran.currencyexchange.dto.CurExchangeApi.ERROR;
import static telran.currencyexchange.dto.CurExchangeApi.SUCCESS;
import static telran.currencyexchange.dto.CurExchangeApi.SUPPORTED_CURRENCIES;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.currencyexchange.dto.CurrencyDataJson;
import telran.currencyexchange.dto.ResponseCurExcahnge;
import telran.currencyexchange.dto.ResponseExchange;
import telran.currencyexchange.service.ICurExchange;

@RestController
public class CurExchangeController {
	@Autowired
	ICurExchange curExchange;
	
	@GetMapping(value = SUPPORTED_CURRENCIES)
	public Set<String> getCodeCurrency() {
		return curExchange.getAllCurrency();
	}
	
	@GetMapping(value = CURRENCY_VALUE + "/{"+ CODE + "}")
	public ResponseCurExcahnge getCurExch(@PathVariable(CODE) String currency) {
		try {
			Double result=curExchange.currencyBaseOfEuro(currency);
			ResponseExchange response=new ResponseExchange();
			response.status=SUCCESS;
			response.result=result;
			return response;
		}catch(Exception e) {
			ResponseCurExcahnge errorMessage=new ResponseCurExcahnge();
			errorMessage.status=ERROR;
			errorMessage.message=e.getMessage();
			return errorMessage;
		}
	}
	
	@PostMapping(value = CONVERT)
	public ResponseCurExcahnge getConvert(@RequestBody CurrencyDataJson data) {
		try {
			Double result=curExchange.convert(data.amount, data.from, data.to);
			ResponseExchange response=new ResponseExchange();
			response.status=SUCCESS;
			response.result=result;
			return response;
		}catch(Exception e) {
			ResponseCurExcahnge errorMessage=new ResponseCurExcahnge();
			errorMessage.status=ERROR;
			errorMessage.message=e.getMessage();
			return errorMessage;
		}
	}

}
