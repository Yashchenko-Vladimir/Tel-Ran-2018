package telran.currency.repo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import telran.currency.domain.Currency;

public interface CurrenciesMongoRepository extends MongoRepository<Currency, Serializable>{

	List<Currency> findByCurrencyName(String currency);

	List<Currency> findByCurrencyNameAndDateBetween(String currency, LocalDate from, LocalDate to);
	
	
}
