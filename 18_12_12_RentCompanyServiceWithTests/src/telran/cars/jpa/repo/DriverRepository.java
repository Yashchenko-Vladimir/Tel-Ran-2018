package telran.cars.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.jpa.entities.DriverJpa;

public interface DriverRepository  extends JpaRepository<DriverJpa, Long>{

}
