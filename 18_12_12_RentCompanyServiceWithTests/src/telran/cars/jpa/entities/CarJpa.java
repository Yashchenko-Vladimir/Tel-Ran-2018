package telran.cars.jpa.entities;

import java.util.Set;

import javax.persistence.*;

import telran.cars.dto.State;

@Table(name = "cars", indexes= @Index(columnList = "car_model_name"))
@Entity
public class CarJpa {
	
	@Id
	String carNumber;
	@ManyToOne
	@JoinColumn (name = "car_model_name", nullable = false)
	ModelJpa model;
	String color;
	@Enumerated(EnumType.STRING)
	State state;
	boolean flRemoved;
	@OneToMany(mappedBy = "car", cascade=CascadeType.REMOVE)
	Set<RentRecordJpa> records;
	
	public CarJpa() {}

	public CarJpa(String carNumber, ModelJpa model, String color, State state, boolean flRemoved) {
		super();
		this.carNumber = carNumber;
		this.model = model;
		this.color = color;
		this.state = state;
		this.flRemoved = flRemoved;
	}
	
	

	public Set<RentRecordJpa> getRecords() {
		return records;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public ModelJpa getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public State getState() {
		return state;
	}

	public boolean isFlRemoved() {
		return flRemoved;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public void setModel(ModelJpa model) {
		this.model = model;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setFlRemoved(boolean flRemoved) {
		this.flRemoved = flRemoved;
	}

	@Override
	public String toString() {
		return "CarJpa [carNumber=" + carNumber + ", model=" + model + ", color=" + color + ", state=" + state
				+ ", flRemoved=" + flRemoved + "]";
	}
	
	
	
	

}
