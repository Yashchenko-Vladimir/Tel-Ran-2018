package telran.currencyexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@SpringBootApplication
@ManagedResource
public class CurrencyExchangeAppl {
	
	static ConfigurableApplicationContext ctx;
	
	@ManagedOperation
	public static void stop() {
		ctx.close();
	}
	
	public static void main(String[] args) {
		ctx = SpringApplication.run(CurrencyExchangeAppl.class, args);
		

	}

}
