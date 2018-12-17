package telran.cars.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.cars.jpa.entities.OwnerJpa;

public interface OwnerRepository extends
JpaRepository<OwnerJpa, Long> {
	
	@Query("select owner from OwnerJpa owner where size(cars)>:nCars")
	List<OwnerJpa> findByCarsSizeGreaterThan(@Param("nCars") int nCars);

}
