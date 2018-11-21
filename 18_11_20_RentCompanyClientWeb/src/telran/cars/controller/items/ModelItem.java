package telran.cars.controller.items;
import java.util.List;

import telran.cars.dto.Model;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ModelItem extends CarsItem {
    public ModelItem(InputOutput io, IRentCompany company) {
        super(io, company);
    }

    @Override
    public String displayedName() {
        return "Get information about a specific model";
    }

    @Override
    public void perform() {
    	String modelName = inputModelName();
        	
        Model model=null;
    	if (modelName!=null) {
    		model = company.getModel(modelName);
    	}
        if (model != null)
            io.outputLine(model);
        else 
        	throw new IllegalArgumentException("No model");
    }

	
    
	
}
