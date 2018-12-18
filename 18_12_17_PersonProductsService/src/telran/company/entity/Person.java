package telran.company.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
	@Id
	int id;
	String name;
	
	@ManyToMany
	@JoinTable(
	     name = "person_own", 
	     joinColumns = { @JoinColumn(name = "id") }, 
		 inverseJoinColumns = { @JoinColumn(name = "bar_code", referencedColumnName="barCode")})
	List<Product> personOwn;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	     name = "person_rent", 
	     joinColumns = { @JoinColumn(name = "id") }, 
		 inverseJoinColumns = { @JoinColumn(name = "bar_code", referencedColumnName="barCode")})
	List<Product> personRent;
	
	
	
	public Person() {}
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	public List<Product> getOwn() {
		return personOwn;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwn(List<Product> own) {
		this.personOwn = own;
	}
	
	
}
