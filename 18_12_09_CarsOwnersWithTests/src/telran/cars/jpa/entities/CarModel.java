	package telran.cars.jpa.entities;
	
	import javax.persistence.Entity;
	import javax.persistence.Id;
	import javax.persistence.Table;
	
	@Table(name = "carmodels")
	@Entity
	public class CarModel {
		@Id
		String modelName;
		int volume;
		String country;
		String company;
	
		public String getModelName() {
			return modelName;
		}
	
		public int getVolume() {
			return volume;
		}
	
		public String getCountry() {
			return country;
		}
	
		public String getCompany() {
			return company;
		}
	
		public CarModel() {
		}
	
		public CarModel(String model_name, int volume, String country, String company) {
			super();
			this.modelName = model_name;
			this.volume = volume;
			this.country = country;
			this.company = company;
		}
	
	}
