package telran.fabric.entity;

import java.util.List;

import javax.persistence.*;

@Table(name = "employees")
@Entity
public class Employee {
	
	@Id
	int id;
	int salary;
	String name;
	String department;
	
	@ManyToOne
	Employee manager;
	
	@OneToMany(mappedBy="manager")
	List<Employee> employees;
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee() {}

public Employee(int id, int salary, String name, String department) {
	super();
	this.id = id;
	this.salary = salary;
	this.name = name;
	this.department = department;
}


public Employee getManager() {
	return manager;
}

public void setManager(Employee manager) {
	this.manager = manager;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getSalary() {
	return salary;
}

public void setSalary(int salary) {
	this.salary = salary;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((department == null) ? 0 : department.hashCode());
	result = prime * result + id;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + salary;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Employee other = (Employee) obj;
	if (department == null) {
		if (other.department != null)
			return false;
	} else if (!department.equals(other.department))
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (salary != other.salary)
		return false;
	return true;
}
	
	
}
