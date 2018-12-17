package telran.cars.jpa.entities;

import javax.persistence.*;

@Table (name = "models")
@Entity
public class ModelJpa {
	
	@Id
	String modelName;
	int gasTank;
	String company;
	String country;
	int priceDay;
	
	public ModelJpa() {	}

	public ModelJpa(String modelName, int gasTank, String company, String country, int priceDay) {
		super();
		this.modelName = modelName;
		this.gasTank = gasTank;
		this.company = company;
		this.country = country;
		this.priceDay = priceDay;
	}
	
	
	public String getModelName() {
		return modelName;
	}

	public int getGasTank() {
		return gasTank;
	}

	public String getCompany() {
		return company;
	}

	public String getCountry() {
		return country;
	}

	public int getPriceDay() {
		return priceDay;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setGasTank(int gasTank) {
		this.gasTank = gasTank;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPriceDay(int priceDay) {
		this.priceDay = priceDay;
	}

	@Override
	public String toString() {
		return "ModelJpa [modelName=" + modelName + ", gasTank=" + gasTank + ", company=" + company + ", country="
				+ country + ", priceDay=" + priceDay + "]";
	}
	
	

}
