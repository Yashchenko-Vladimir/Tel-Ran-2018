package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.utils.Persistable;
import telran.view.InputOutput;

public class SaveAndExitItem extends CarsItem{
	String fileName;

	public SaveAndExitItem(InputOutput io, IRentCompany rentCompany, String fileName) {
		super(io, rentCompany);
		this.fileName = fileName;
	}

	@Override
	public String displayedName() {
		
		return "Exit and Save to File";
	}

	@Override
	public void perform() {
//		String nameFile = io.inputString("Enter name file to save");
		if(rentCompany instanceof Persistable) {
			((Persistable) rentCompany).saveToFile(fileName);
			io.outputLine("saved to " + fileName + " bye");
		} else {
			io.outputLine("no file name");
		}
		
	}
	
	public boolean isExit() {
		return true;
	}
	

}
