package telran.cars.dao.repo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.cars.jpa.entities.CarJpa;

public interface CarRepository extends
JpaRepository<CarJpa, String>{

	Set<CarJpa> findByCarModelModelName(String modelName);
	
	@Query("select carModel.modelName as modelName, count(*) as count"
			   + " from CarJpa "
			   + " where year(purchase_date) = :year "
			   + " group by carModel.modelName "
			   + " order by count(*) desc, carModel.modelName")
	List<ModelCount> selectModelCounts(@Param("year") int year);

}
