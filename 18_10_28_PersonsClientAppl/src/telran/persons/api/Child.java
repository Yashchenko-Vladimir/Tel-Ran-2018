package telran.persons.api;

import java.time.LocalDate;

import telran.util.Address;

public class Child extends Person {
String kindergarten;
public Child() {
}
public Child(int id, String phone, String name, Address address, LocalDate birthDate,String kindergarten) {
	super(id, phone, name, address,birthDate);
	this.kindergarten = kindergarten;
}

@Override
public String toString() {
	return "Child [kindergarten=" + kindergarten + ", address=" + getAddress() + ", phone=" + getPhone()
			+ ", id=" + getId() + ", name=" + getName() +", birthDate="+getBirthDate()+ "]\n";
}
public String getKindergarten() {
	return kindergarten;
}
public void setKindergarten(String kindergarten) {
	this.kindergarten = kindergarten;
}

}
