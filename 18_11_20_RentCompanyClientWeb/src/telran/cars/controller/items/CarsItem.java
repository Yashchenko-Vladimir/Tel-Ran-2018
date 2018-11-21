package telran.cars.controller.items;

import java.util.List;

import telran.cars.dto.Model;
import telran.cars.model.IRentCompany;
import telran.view.*;

public abstract class CarsItem implements Item {
protected InputOutput io;
protected IRentCompany company;
public CarsItem(InputOutput io, IRentCompany company) {
	super();
	this.io = io;
	this.company = company;
}
protected String inputModelName() {
	List<String> allModelNames = company.getAllModelNames();
    for (int i = 0; i < allModelNames.size(); i++) {
        io.outputLine((i + 1) + " " + allModelNames.get(i));
    }
    if(allModelNames.isEmpty()) {
    	io.outputLine("No models provided");
    	return null;
    }
    Integer modelNum = io.inputInteger("Select model number:", 1, allModelNames.size());
    String modelName=null;
    if(modelNum!=null)
    {
    	modelName=allModelNames.get(modelNum-1);
    }
	return modelName;
}
}
