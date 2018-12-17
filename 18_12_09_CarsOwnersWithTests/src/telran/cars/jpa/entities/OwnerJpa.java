	package telran.cars.jpa.entities;
	
	import java.util.Set;
	
	import javax.persistence.*;
	
	@Table(name = "owners")
	@Entity
	public class OwnerJpa {
		@Id
		long id;
		String name;
		int birthyear;
		@OneToMany(mappedBy = "owner"/*, fetch=FetchType.EAGER*/ /*, cascade=CascadeType.REMOVE чтобы удалить со всех таблиц*/)
		Set<CarJpa> cars;
	
		public OwnerJpa(long id, String name, int birthyear) {
			super();
			this.id = id;
			this.name = name;
			this.birthyear = birthyear;
		}
	
		public OwnerJpa() {
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
	
		public Set<CarJpa> getCars() {
			return cars;
		}
	}
