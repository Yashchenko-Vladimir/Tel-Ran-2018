package telran.fabric.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.fabric.entity.Employee;
import telran.fabric.repo.IEmployeeRepository;

@Service
public class EmployeeJpa implements IEmployee {
	
	@Autowired
	IEmployeeRepository empRepo;
	

	@Override
	@Transactional
	public boolean addDirector(int id, String name, int salary) {
		if(!empRepo.existsById(id)) return false;
		Employee employee = new Employee(id, salary, name, null);
		empRepo.save(employee);
		return true;
	}

	@Override
	public boolean addEmployee(int id, String name, int salary, String department, int managerid) {
		if(!empRepo.existsById(id)) return false;
		if(!empRepo.existsById(managerid)) return false;
		Employee employee = new Employee(id, salary, name, department);
		Employee manager = empRepo.findById(managerid).orElse(null);
		manager.getEmployees().add(employee);
		employee.setManager(manager);
		empRepo.save(employee);
		return true;
	}

	@Override
	public Employee getDirector() {
		return empRepo.findByManagerNull();
	}

	@Override
	public Employee getDirectManager(int id) {
		Employee employee = empRepo.findById(id).orElse(null);
		if(employee == null) return null;
		return employee.getManager();
	}

	@Override
	public List<Employee> getEmployeesWithSalaryGreaterThanManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> displayDepartments() {
//		return empRepo.selectDepartment();
		return null;
	}

	@Override
	public List<Employee> getEmployeeAllManager(int id) {
		
		return null;
	}

}
