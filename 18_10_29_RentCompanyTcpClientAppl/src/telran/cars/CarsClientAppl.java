package telran.cars;

import java.io.IOException;
import java.net.UnknownHostException;

import telran.cars.controller.items.*;
import telran.cars.controller.items.clerk.*;
import telran.cars.controller.items.driver.*;
import telran.cars.controller.items.manager.*;
import telran.cars.controller.items.statist.*;
import telran.cars.controller.items.technician.*;
import telran.cars.model.CarsTcpProxy;
import telran.cars.model.IRentCompany;
import telran.view.*;

public class CarsClientAppl {

	private static final String DEFAULT_HOST_NAME = "localhost";
	private static final int DEFAULT_PORT = 2000;
	static InputOutput io;
	static IRentCompany rentCompany;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		String hostname = args.length > 0? args[0]:DEFAULT_HOST_NAME;
		int port = args.length > 1? Integer.parseInt(args[1]) :DEFAULT_PORT;
		io = new ConsoleInputOutput();
		rentCompany = new CarsTcpProxy(hostname, port);
		Item[] item = getMainMenuItems();
		Menu menu = new MenuWithExit(io, item);
		menu.runMenu();
	}

	private static Item[] getMainMenuItems() {
		Item [] res = {
				new Submenu("Manager", getManagerMenu()),
				new Submenu("Clerk", getClerkSubmenu()),
				new Submenu("Driver", getDriverSubmenu()),
				new Submenu("Statist", getStatistSubMenu()),
				new Submenu("Technician", getTechnicianSubMenu()),
				new getAllModelsNameItem(io, rentCompany),
				new getModelNameItem(io, rentCompany),
//				new SaveAndExitItem(io, rentCompany, fileName)
				
		};
		return res;
	}

	private static Menu getTechnicianSubMenu() {
		Item [] itemTechnician = {
				new GetAllRecordsItem(io, rentCompany),
				new ReturnRecordsByRange(io, rentCompany)
			};
		Menu menuTechnician = new MenuWithExit(io, itemTechnician);
		return menuTechnician;
	}

	private static Menu getStatistSubMenu() {
		Item[] itemStatist = {
				new GetMostPopularModelNamesItem(io, rentCompany),
				new GetModelProfitItem(io, rentCompany),
				new GetModelProfitItem(io, rentCompany)
		};
		Menu menuStatist = new MenuWithExit(io, itemStatist);
			return menuStatist;
	}

	private static Menu getDriverSubmenu() {
		Item[] itemsDriver = {
				new GetDriverItem(io, rentCompany),
				new GetAllDriversItem(io, rentCompany),
				new GetCarDriversItem(io, rentCompany),
				new getDriverCarsItem(io, rentCompany)
		};
		Menu menuDriver = new MenuWithExit(io, itemsDriver);
		return menuDriver;
	}

	private static Menu getClerkSubmenu() {
		Item[] itemsClerk = {
				new AddDriverItem(io, rentCompany),
				new GetCarItem(io, rentCompany),
				new GetAllCarsItem(io, rentCompany),
				new RentCarItem(io, rentCompany),
				new ReturnCarItem(io, rentCompany)
		};
		Menu menuClerk = new  MenuWithExit(io, itemsClerk);
		return menuClerk;
	}

	private static Menu getManagerMenu() {
		Item [] itemsManager = {
				new AddCarItem(io, rentCompany),
				new AddModelItem(io, rentCompany),
				new RemoveCarItem(io, rentCompany),
				new ClearItem(io, rentCompany)
		};
		Menu menuManager = new MenuWithExit(io, itemsManager);
		
		return menuManager;
	}


}
