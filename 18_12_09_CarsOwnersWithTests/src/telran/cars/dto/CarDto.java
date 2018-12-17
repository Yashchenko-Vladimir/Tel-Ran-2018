package telran.cars.dto;

import java.time.LocalDate;

public class CarDto {
public String reg_number;
public String color;
public String modelName;
public long ownerId;
public LocalDate purchase_date;
public CarDto() {
}
public CarDto(String reg_number, String color, String modelName, long ownerId, LocalDate purchase_date) {
	super();
	this.reg_number = reg_number;
	this.color = color;
	this.modelName = modelName;
	this.ownerId = ownerId;
	this.purchase_date = purchase_date;
}
public String getReg_number() {
	return reg_number;
}
public String getColor() {
	return color;
}
public String getModelName() {
	return modelName;
}
public long getOwnerId() {
	return ownerId;
}
public LocalDate getPurchase_date() {
	return purchase_date;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((color == null) ? 0 : color.hashCode());
	result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
	result = prime * result + (int) (ownerId ^ (ownerId >>> 32));
	result = prime * result + ((purchase_date == null) ? 0 : purchase_date.hashCode());
	result = prime * result + ((reg_number == null) ? 0 : reg_number.hashCode());
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
	CarDto other = (CarDto) obj;
	if (color == null) {
		if (other.color != null)
			return false;
	} else if (!color.equals(other.color))
		return false;
	if (modelName == null) {
		if (other.modelName != null)
			return false;
	} else if (!modelName.equals(other.modelName))
		return false;
	if (ownerId != other.ownerId)
		return false;
	if (purchase_date == null) {
		if (other.purchase_date != null)
			return false;
	} else if (!purchase_date.equals(other.purchase_date))
		return false;
	if (reg_number == null) {
		if (other.reg_number != null)
			return false;
	} else if (!reg_number.equals(other.reg_number))
		return false;
	return true;
}
@Override
public String toString() {
	return "CarDto [reg_number=" + reg_number + ", color=" + color + ", modelName=" + modelName + ", ownerId=" + ownerId
			+ ", purchase_date=" + purchase_date + "]";
}

}
