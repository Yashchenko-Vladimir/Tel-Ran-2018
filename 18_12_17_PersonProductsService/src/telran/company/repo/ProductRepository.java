package telran.company.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.company.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
