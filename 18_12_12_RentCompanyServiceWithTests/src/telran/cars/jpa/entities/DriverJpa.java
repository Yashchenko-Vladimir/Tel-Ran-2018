package telran.cars.jpa.entities;

import java.util.Set;

import javax.persistence.*;


@Table(name = "drivers")
@Entity
public class DriverJpa {
	
	@Id
	long licenseId;
	String name;
	int birthYear;
	String phone;
//	@OneToMany(mappedBy="driver")
//	Set<RentRecordJpa> records;
	
	public DriverJpa() {}
	
	
	

	public DriverJpa(long licenseId, String name, int birthYear, String phone) {
		super();
		this.licenseId = licenseId;
		this.name = name;
		this.birthYear = birthYear;
		this.phone = phone;
	}




	public long getLicenseId() {
		return licenseId;
	}


	public String getName() {
		return name;
	}


	public int getBirthYear() {
		return birthYear;
	}


	public String getPhone() {
		return phone;
	}


	public void setLicenseId(long licenseId) {
		this.licenseId = licenseId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "DriverJpa [licenseId=" + licenseId + ", name=" + name + ", birthYear=" + birthYear + ", phone=" + phone
				+ "]";
	}
	
	

}
