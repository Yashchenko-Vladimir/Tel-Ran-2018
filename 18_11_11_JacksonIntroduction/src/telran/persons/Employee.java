package telran.persons;

import java.time.LocalDate;

import telran.util.Address;

public class Employee extends Person {
	private String company;
	private int salary;
	
	
	public Employee(int id, String phone, String name, Address address, String company, int salary, LocalDate birthDate) {
		super(id, phone, name, address, birthDate);
		this.company = company;
		this.salary = salary;
	}
	
	public Employee() {
		
	}

	@Override
	public String toString() {
		return "Employee [company=" + company + ", salary=" + salary + ", address=" + getAddress() + ", id="
				+ getId() + ", phone=" + getPhone() + ", name()=" + getName() +  ", birthDate=" + getBirthDate() + "]\n";
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
	
	
}
