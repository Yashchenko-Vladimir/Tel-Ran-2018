package telran.cars.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.jpa.entities.ModelJpa;

public interface ModelRepository extends JpaRepository<ModelJpa, String>{

}
