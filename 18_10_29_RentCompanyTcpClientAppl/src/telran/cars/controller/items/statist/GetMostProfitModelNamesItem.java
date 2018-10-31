package telran.cars.controller.items.statist;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import telran.cars.controller.items.CarsItem;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class GetMostProfitModelNamesItem extends CarsItem{

	public GetMostProfitModelNamesItem(InputOutput io, IRentCompany rentCompany) {
		super(io, rentCompany);
	}

	@Override
	public String displayedName() {
		return "Display most profit model";
	}

	@Override
	public void perform() {
		rentCompany.getMostProfitModelNames().forEach(x-> System.out.println(x + " - is most profit model"));
		
	}

}
