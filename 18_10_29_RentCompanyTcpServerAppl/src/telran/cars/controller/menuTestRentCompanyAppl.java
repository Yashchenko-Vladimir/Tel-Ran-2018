package telran.cars.controller;

import telran.cars.controller.items.*;
import telran.cars.controller.items.clerk.*;
import telran.cars.controller.items.driver.*;
import telran.cars.controller.items.manager.*;
import telran.cars.controller.items.statist.GetModelProfitItem;
import telran.cars.controller.items.statist.GetMostPopularModelNamesItem;
import telran.cars.controller.items.technician.*;
import telran.cars.model.RentCompanyEmbedded;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;
import telran.view.Submenu;

public class menuTestRentCompanyAppl  {
	private static final String DEFAUL_FILE_NAME = "consolTest.data";
	static String fileName;
	static InputOutput io;
	static RentCompanyEmbedded rentCompany;

	public static void main(String[] args) {
		
		fileName = args.length == 0? DEFAUL_FILE_NAME:args[0];
		rentCompany = RentCompanyEmbedded.restoreFromFile(fileName);
		io = new ConsoleInputOutput();
		Item [] items = getMainMenuItems();
		
		Menu menu = new MenuWithExit(io, items);
		menu.runMenu();
//		startAppl(io);
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
				new SaveAndExitItem(io, rentCompany, fileName)
				
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

	private static void startAppl(InputOutput io) {
		
		
//				new RentCompanyEmbedded();
//				RentCompanyEmbedded.restoreFromFile("company.data");
		Item [] itemsManager = {
				new AddCarItem(io, rentCompany),
				new AddModelItem(io, rentCompany),
				new RemoveCarItem(io, rentCompany),
				new ClearItem(io, rentCompany)
	};
		Item [] itemTechnician = {
			new GetAllRecordsItem(io, rentCompany),
			new ReturnRecordsByRange(io, rentCompany)
		};
		Item[] itemsClerk = {
				new AddDriverItem(io, rentCompany),
				new GetCarItem(io, rentCompany),
				new GetAllCarsItem(io, rentCompany),
				new RentCarItem(io, rentCompany),
				new ReturnCarItem(io, rentCompany)
		};
		Item[] itemsDriver = {
				new GetDriverItem(io, rentCompany),
				new GetAllDriversItem(io, rentCompany),
				new GetCarDriversItem(io, rentCompany),
				new getDriverCarsItem(io, rentCompany)
		};
		Item[] itemStatist = {
				new GetMostPopularModelNamesItem(io, rentCompany),
				new GetModelProfitItem(io, rentCompany),
				new GetModelProfitItem(io, rentCompany)
		};
		Menu menuStatist = new MenuWithExit(io, itemStatist);
		Menu menuClerk = new  MenuWithExit(io, itemsClerk);
		Menu menuManager = new MenuWithExit(io, itemsManager);
		Menu menuTechnician = new MenuWithExit(io, itemTechnician);
		Menu menuDriver = new MenuWithExit(io, itemsDriver);
		Item [] items = {
				new Submenu("Manager", menuManager),
				new Submenu("Clerk", menuClerk),
				new Submenu("Driver", menuDriver),
				new Submenu("Statist", menuStatist),
				new Submenu("Technician", menuTechnician),
				new getAllModelsNameItem(io, rentCompany),
				new getModelNameItem(io, rentCompany),
				new SaveAndExitItem(io, rentCompany, fileName)
		};
		
		Menu menu = new MenuWithExit(io, items);
		
		menu.runMenu();
		
	}

}
