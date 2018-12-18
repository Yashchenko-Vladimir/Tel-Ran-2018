package telran.cars.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.cars.jpa.entities.PopularModels;

public interface PopularModelsRepository extends JpaRepository<PopularModels, Integer>{
	
	@Query(value = "select max(count) from (select count(*) as count from cars join owners "
			+ "on ownerid=id where year(purchasedate)= :year and year(curdate())-birthyear between "
			+ ":ageFrom and :ageTo group by modelname)", nativeQuery = true)
	Long getMax(@Param("year")int year, @Param("ageFrom")int ageFrom, @Param("ageTo")int ageTo);
	
//	Это JPQL @Query(value ="select modelname from PopularModels where year= :year and age between :ageFrom and :ageTo "
//			+ "group by modelname having count(*)=:max")
	@Query("SELECT carModel.modelName FROM CarJpa WHERE year(purchase_date)= :year AND year(curdate())-"
			+ "owner.birthyear between :ageFrom and :ageTo GROUP BY "
			+ "carModel.modelName HAVING count(*)=:max")
	List<String> getModels(@Param("year")int year, @Param("ageFrom")int ageFrom, 
			@Param("ageTo")int ageTo, @Param("max") long maxOccurences);


}
