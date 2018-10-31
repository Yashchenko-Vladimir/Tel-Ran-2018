package telran.persons.api;

import java.time.LocalDate;

import telran.util.Address;

public class Employee extends Person {
private String company;
private int salary;
public Employee(int id, String phone, String name, Address address, LocalDate birthDate,String company, int salary) {
	super(id, phone, name, address,birthDate);
	this.company = company;
	this.salary = salary;
}
public Employee() {
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
@Override
public String toString() {
	return "Employee [company=" + company + ", salary=" + salary + ", address=" + getAddress() + ", phone="
			+ getPhone() + ", id=" + getId() + ", name=" + getName() +", birthDate="+getBirthDate()+ "]\n";
}

}
