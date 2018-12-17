package telran.cars.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.cars.jpa.entities.PopularModels;

public interface PopularModelsRepository extends JpaRepository<PopularModels, Integer>{
	
	@Query(value = "select max(count) from (select count(*) as count from PopularModels "
			+ "where=: year  and age between :agefrom :ageTo group by "
			+ "modelname)", nativeQuery = true)
	long getMax(@Param("yaer")int year, @Param("ageFrom")int ageFrom, @Param("ageTo")int ageTo);
	
	@Query(value ="select modelname from PopularModels where year= :year and and age between :agefrom :ageTo "
			+ "group by modelname having count(*)=:max")
	List<String> getModels(@Param("yaer")int year, @Param("ageFrom")int ageFrom, 
			@Param("ageTo")int ageTo, @Param("max") long maxOccurences);

}
