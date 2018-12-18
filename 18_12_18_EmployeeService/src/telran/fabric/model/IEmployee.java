package telran.fabric.model;

import java.util.*;

import telran.fabric.entity.Employee;

public interface IEmployee {
	
	boolean addDirector(int id, String name, int salary);

	 boolean addEmployee(int id, String name, int salary, String department, int managerid);

	 Employee getDirector();

	 Employee getDirectManager(int id);
	 
	 List<Employee> getEmployeeAllManager(int id);

	 List<Employee> getEmployeesWithSalaryGreaterThanManager();

	 Map<String, Integer> displayDepartments();
}
