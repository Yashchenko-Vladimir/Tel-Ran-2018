package telran.cars.controller.items;

import java.util.List;
import java.util.stream.Collectors;

import telran.cars.model.IRentCompany;
import telran.view.*;

public abstract class CarsItem implements Item {
	
	protected InputOutput io;
	protected IRentCompany rentCompany;
	
	
	public CarsItem(InputOutput io, IRentCompany rentCompany) {
		this.io = io;
		this.rentCompany = rentCompany;
	}
	
	protected String inputModelName() {
		List<String> allModelName = rentCompany.getAllModelNames();
		for(int i = 0; i < allModelName.size(); i++) {
			io.outputLine(String.format("%d. %s",i+1, rentCompany.getModel(allModelName.get(i))));
		}
		Integer numModel = io.inputInteger("Select model number", 1, allModelName.size());
		
		String modelName = null;
		if(numModel != null) {
			modelName = allModelName.get(numModel -1);
		}
		return  modelName;
	}
	
	protected String inputCarNumber() {
		List<String> allCarRegnumber = rentCompany.getAllCars().map(car -> car.getRegNumber()).collect(Collectors.toList());
		for(int i = 0; i < allCarRegnumber.size(); i++) {
			io.outputLine(String.format("%d. %s",i+1, rentCompany.getModel(allCarRegnumber.get(i))));
		}
		Integer numCarNmber = io.inputInteger("Select model number", 1, allCarRegnumber.size());
		
		String carRegNumber = null;
		if(numCarNmber != null) {
			carRegNumber = allCarRegnumber.get(numCarNmber -1);
		}
		return  carRegNumber;
	}
	
}
