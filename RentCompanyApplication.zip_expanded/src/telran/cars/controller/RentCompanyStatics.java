package telran.cars.controller;

import telran.cars.model.IRentCompany;
import telran.cars.model.RentCompanyEmbedded;

public class RentCompanyStatics {

	public static void main(String[] args) {
		RentCompanyEmbedded rentCompany = RentCompanyEmbedded.restoreFromFile("RentCompany");
		System.out.println(rentCompany.getAllRecords().count());
		System.out.println(rentCompany.getAllCars().count());
		System.out.println("Car is in  use " + rentCompany.getAllCars().filter(car -> car.isInUse()).count());
//		rentCompany.getAllCars().forEach(System.out::println);

	}

}
