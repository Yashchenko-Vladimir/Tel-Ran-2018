package telran.cars.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.jpa.entities.CarModel;

public interface CarModelRepository extends
JpaRepository<CarModel, String> {

}
