package telran.security.accounting.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.security.accounting.mongo.Account;


public interface AccountingMongoRepository extends MongoRepository<Account, String>{

	Account findByUsername(String username);

	
}
