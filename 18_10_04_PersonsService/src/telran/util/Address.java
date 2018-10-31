package telran.util;

import java.io.Serializable;

public class Address implements Serializable{
	private String city;
	private String street;
	private int building;
	private int aprt;
	
	public Address() {
		
	}
	
	public Address(String city, String street, int building, int aprt) {
		super();
		this.city = city;
		this.street = street;
		this.building = building;
		this.aprt = aprt;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public int getBuilding() {
		return building;
	}

	public int getAprt() {
		return aprt;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + ", building=" + building + ", aprt=" + aprt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aprt;
		result = prime * result + building;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (aprt != other.aprt)
			return false;
		if (building != other.building)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	
	
}
