package telran.company.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.company.entity.Person;
import telran.company.entity.Product;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	
//	
//	@Query(value = "SELECT max(prod.count) FROM (SELECT bar_code, count(*) as count FROM person_own "
//			+ "GROUP BY bar_code) as prod")
//	long selectGetMaxPersonOwns();
	
//	@Query(value ="SELECT * FROM products JOIN (select bar_code, count(*) as count "
//			+ "from personown group by bar_code) as product_group ON products.bar_code = prouduct_group.bar_code "
//			+ "where product_group.count = :max ",
//			nativeQuery = true)
//	List<Person> selectPersonWithMostOwns(@Param ("max") long max);

	
	
}
