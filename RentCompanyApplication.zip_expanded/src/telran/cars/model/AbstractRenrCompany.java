package telran.cars.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import telran.cars.dto.Model;
import telran.cars.dto.RentRecord;
import telran.utils.Persistable;

public abstract class AbstractRenrCompany implements IRentCompany, Persistable {
	
	@Override
	public String toString() {
		return "AbstractRenrCompany [finePercent=" + finePercent + ", gasPrice=" + gasPrice + "]";
	}
	protected int finePercent =15;
	protected int gasPrice = 10;
	
	
	public int getFinePercent() {
		return finePercent;
	}
	public void setFinePercent(int finePercent) {
		this.finePercent = finePercent;
	}
	public int getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(int gasPrice) {
		this.gasPrice = gasPrice;
	}
	protected float getCarCostRent(LocalDate rentDay, LocalDate returnDay, int rentDays, int priceRentDay, int gasTank, int percentGasTank ) {
		float cost = 0;
		int moreDays = (int) (ChronoUnit.DAYS.between(rentDay, returnDay) - rentDays);
		float payDay = priceRentDay * rentDays + moreDays * (priceRentDay + priceRentDay * finePercent/100);
		float paytank = gasTank * (100 - percentGasTank)/100 * getGasPrice();
		cost = payDay + paytank;
		return cost;
		
//		getCostFullTankOnTime() + gasPrice * GAS_TANK1 / 2 + DELAY_DAYS * (PRICE1 + PRICE1 * finePercent / 100),
//        actual.getCost());
		
		
	}
	
	protected float calculate_cost(RentRecord record, Model model) {
		float cost = 0;
		int delay = (int) (ChronoUnit.DAYS.between(record.getRentDate(), record.getReturnDate()) - record.getRentDays());
		float payDay = model.getPriceDay() * record.getRentDays() + delay * (model.getPriceDay() + model.getPriceDay() * finePercent/100);
		if(delay < 0) {
			delay = 0;
		}
		float paytank = 0;
		if(record.getGasTankPercent() < 100) {
		paytank = model.getGasTank() * (100 - record.getGasTankPercent())/100 * getGasPrice();
		}
		cost = payDay + paytank;
		
		return cost;
	}
}
