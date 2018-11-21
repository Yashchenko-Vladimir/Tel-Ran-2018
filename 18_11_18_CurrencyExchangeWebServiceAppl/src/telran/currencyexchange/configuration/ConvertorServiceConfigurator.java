package telran.currencyexchange.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import telran.currencyexchange.service.CurExchangeMap;
import telran.currencyexchange.service.ICurExchange;

@Configuration
public class ConvertorServiceConfigurator {
	@Bean
	public ICurExchange getConvertorService(){
		return new CurExchangeMap();
	}

}
