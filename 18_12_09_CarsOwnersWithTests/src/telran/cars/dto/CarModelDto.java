package telran.cars.dto;

public class CarModelDto {
public String modelName;
public int volume;
public String company;
public String country;
public CarModelDto() {
}
public CarModelDto(String model_name, int volume, String company, String country) {
	super();
	this.modelName = model_name;
	this.volume = volume;
	this.company = company;
	this.country = country;
}
public String getModel_name() {
	return modelName;
}
public int getVolume() {
	return volume;
}
public String getCompany() {
	return company;
}
public String getCountry() {
	return country;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((company == null) ? 0 : company.hashCode());
	result = prime * result + ((country == null) ? 0 : country.hashCode());
	result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
	result = prime * result + volume;
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
	CarModelDto other = (CarModelDto) obj;
	if (company == null) {
		if (other.company != null)
			return false;
	} else if (!company.equals(other.company))
		return false;
	if (country == null) {
		if (other.country != null)
			return false;
	} else if (!country.equals(other.country))
		return false;
	if (modelName == null) {
		if (other.modelName != null)
			return false;
	} else if (!modelName.equals(other.modelName))
		return false;
	if (volume != other.volume)
		return false;
	return true;
}
@Override
public String toString() {
	return "CarModelDto [model_name=" + modelName + ", volume=" + volume + ", company=" + company + ", country="
			+ country + "]";
}

}
