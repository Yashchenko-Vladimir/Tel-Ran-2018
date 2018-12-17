package telran.cars.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.jpa.entities.CarJpa;

public interface CarRepository extends JpaRepository<CarJpa, String>{

}
