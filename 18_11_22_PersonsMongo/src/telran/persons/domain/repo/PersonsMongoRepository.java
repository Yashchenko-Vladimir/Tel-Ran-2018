package telran.persons.domain.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import telran.persons.domain.Child;
import telran.persons.domain.Person;


@Repository
public interface PersonsMongoRepository extends MongoRepository<Person, Integer>{
	

	List<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

	List<Person> findByAddressCity(String city);
	
	@Query("{'salary':{'$gte':?0, '$lte':?1}}")
	List<Person> findBySalary( int from, int to);
	
	List<Child> findByKindergarten(String garten);
	

}
