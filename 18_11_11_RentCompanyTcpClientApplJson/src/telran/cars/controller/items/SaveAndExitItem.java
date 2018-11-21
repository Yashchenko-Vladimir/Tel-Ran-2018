package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.utils.Persistable;
import telran.view.InputOutput;

public class SaveAndExitItem extends CarsItem {
String fileName;
	@Override
	public boolean isExit() {
		return true;
	}

	public SaveAndExitItem(InputOutput io, IRentCompany company,
			String fileName) {
		super(io, company);
		this.fileName=fileName;
	}

	@Override
	public void perform() {
		if(company instanceof Persistable) {
			if (!fileName.isEmpty()) {
				((Persistable)company).saveToFile(fileName);
				io.outputLine("saved to "+fileName+" bye");
			} else io.outputLine("no filename");
		}			
	}

	@Override
	public String displayedName() {
		return "Save and exit";
	}

}
