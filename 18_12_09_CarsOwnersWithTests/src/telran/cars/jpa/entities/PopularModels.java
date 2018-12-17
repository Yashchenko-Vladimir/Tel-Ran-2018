package telran.cars.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select model_name, year(purchase_date) as year, "
		+ "year(curdate())-birthyear as age from cars join owners on ownerid=id")
public class PopularModels {
	String modelname;
	int year;
	int age;
	
	
	@Id
	@GeneratedValue
	int gid;
	
	public String getModelname() {
		return modelname;
	}
	public int getYear() {
		return year;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "PopularModels [modelname=" + modelname + ", year=" + year + ", age=" + age + "]";
	}
	
	
}
