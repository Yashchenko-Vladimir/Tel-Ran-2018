package telran.cars.dto;

public class OwnerDto {
public long id;
public String name;
public int birthyear;
public OwnerDto() {
}
public OwnerDto(long id, String name, int birthyear) {
	super();
	this.id = id;
	this.name = name;
	this.birthyear = birthyear;
}
public long getId() {
	return id;
}
public String getName() {
	return name;
}
public int getBirthyear() {
	return birthyear;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + birthyear;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	OwnerDto other = (OwnerDto) obj;
	if (birthyear != other.birthyear)
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}
@Override
public String toString() {
	return "OwnerDto [id=" + id + ", name=" + name + ", birthyear=" + birthyear + "]";
}

}
