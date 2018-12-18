package telran.cars.jpa.entities;

import java.time.LocalDate;

import javax.persistence.*;

@Table(name = "cars")
@Entity
public class CarJpa {
	@Id
	String reg_number;
	String color;
	@Column(name = "purchasedate")
	LocalDate purchase_date;
	@ManyToOne
	@JoinColumn(name = "modelname")
	CarModel carModel;
	@ManyToOne
	@JoinColumn(name="ownerid")
	OwnerJpa owner;

	public CarJpa(String reg_number, String color, LocalDate purchase_date, CarModel car_model, OwnerJpa owner) {
		super();
		this.reg_number = reg_number;
		this.color = color;
		this.purchase_date = purchase_date;
		this.carModel = car_model;
		this.owner = owner;
	}

	public CarJpa() {

	}

	public LocalDate getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(LocalDate purchase_date) {
		this.purchase_date = purchase_date;
	}

	public OwnerJpa getOwner() {
		return owner;
	}

	public void setOwner(OwnerJpa owner) {
		this.owner = owner;
	}

	public String getReg_number() {
		return reg_number;
	}

	public String getColor() {
		return color;
	}

	public CarModel getCar_model() {
		return carModel;
	}

}
