package telran.company.entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "products")
public class Product {
	
	@Id
	long barCode;
	String productName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	     name = "person_own", 
	     joinColumns = { @JoinColumn(name = "bar_code", referencedColumnName="barCode") }, 
		 inverseJoinColumns = { @JoinColumn(name = "id")})
	List<Person> productOwner;
	
	public Product() {}
	
	
	public Product(long barCode, String productName) {
		super();
		this.barCode = barCode;
		this.productName = productName;
	}

	
	public List<Person> getProductOwner() {
		return productOwner;
	}


	public void setProductOwner(List<Person> productOwner) {
		this.productOwner = productOwner;
	}


	public long getBarCode() {
		return barCode;
	}


	public String getProductName() {
		return productName;
	}
	
	
}
