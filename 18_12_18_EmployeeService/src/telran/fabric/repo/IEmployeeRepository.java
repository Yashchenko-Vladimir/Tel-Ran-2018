package telran.fabric.repo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.fabric.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{

	Employee findByManagerNull();
	
	@Query("SELECT department FROM employees GROUP BY department")
	List<String> selectDepartment();

}
