package telran.persons;

import java.time.LocalDate;

import telran.util.Address;

public class Person implements Comparable<Person>{
	private int id;
	private String phone;
	private String name;
	private Address address;
	private LocalDate birthDate;
	
	
	public Person(int id, String phone, String name, Address address, LocalDate birthDate) {
		super();
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.birthDate = birthDate;
	}

	
	@Override
	public String toString() {
		return "Person [id=" + id + ", phone=" + phone + ", name=" + name + ", address=" + address + ", birthDate=" +birthDate + "]";
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}


	@Override
	public int compareTo(Person per) {
		
		return id - per.id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
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
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


	


	
	
	
}
