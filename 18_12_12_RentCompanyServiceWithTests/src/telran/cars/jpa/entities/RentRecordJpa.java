package telran.cars.jpa.entities;

import java.time.LocalDate;

import javax.persistence.*;

@Table(name = "records", indexes = { @Index(columnList = "driver_id, " + "car_id, " + "rent_date, " + "return_date ") })
@Entity
public class RentRecordJpa {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne
	@JoinColumn(name = "driver_id")
	CarJpa car;
	@ManyToOne
	@JoinColumn(name = "car_id")
	DriverJpa driver;
	@Column(name = "rent_date")
	LocalDate rentDate;
	@Column(name = "return_date", nullable = true)
	LocalDate returnDate;
	int rentDays;
	float cost;
	int damages;
	int gasTankPercent;

	public RentRecordJpa() {
	}

	public RentRecordJpa(CarJpa car, DriverJpa driver, LocalDate rentDate, LocalDate returnDate, int rentDays,
			float cost, int damages, int gasTankPercent) {
		super();
		this.car = car;
		this.driver = driver;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.rentDays = rentDays;
		this.cost = cost;
		this.damages = damages;
		this.gasTankPercent = gasTankPercent;
	}

	public int getId() {
		return id;
	}

	public CarJpa getCar() {
		return car;
	}

	public DriverJpa getDriver() {
		return driver;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public int getRentDays() {
		return rentDays;
	}

	public float getCost() {
		return cost;
	}

	public int getDamages() {
		return damages;
	}

	public int getGasTankPercent() {
		return gasTankPercent;
	}

	public void setCar(CarJpa car) {
		this.car = car;
	}

	public void setDriver(DriverJpa driver) {
		this.driver = driver;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setDamages(int damages) {
		this.damages = damages;
	}

	public void setGasTankPercent(int gasTankPercent) {
		this.gasTankPercent = gasTankPercent;
	}

	@Override
	public String toString() {
		return "RentRecordJpa [car=" + car + ", driver=" + driver + ", rentDate=" + rentDate + ", returnDate="
				+ returnDate + ", rentDays=" + rentDays + ", cost=" + cost + ", damages=" + damages
				+ ", gasTankPercent=" + gasTankPercent + "]";
	}

}
