package telran.persons;

import java.time.LocalDate;

import telran.util.Address;

public class Child extends Person{
	private String kindergarten;
	
	public Child() {
		
	}

	public Child(int id, String phone, String name, Address address, String kindergarten, LocalDate birthDate) {
		super(id, phone, name, address, birthDate);
		this.kindergarten = kindergarten;
	}

	public String getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(String kindergarten) {
		this.kindergarten = kindergarten;
	}

	@Override
	public String toString() {
		return "Child [kindergarten=" + kindergarten + ", address=" + getAddress() + ", id=" + getId()
				+ ", phone=" + getPhone() + ", name=" + getName() + ", birthDate=" +getBirthDate() +"]";
	}
	
}
