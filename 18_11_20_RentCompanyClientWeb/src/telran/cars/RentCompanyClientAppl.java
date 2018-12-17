package telran.cars;

import java.io.IOException;
import java.net.UnknownHostException;

import telran.cars.controller.items.*;
import telran.cars.controller.items.clerk.AddDriverItem;
import telran.cars.controller.items.clerk.DisplayAllCarsItem;
import telran.cars.controller.items.clerk.DisplayCarItem;
import telran.cars.controller.items.clerk.RentCarItem;
import telran.cars.controller.items.clerk.ReturnCarItem;
import telran.cars.controller.items.driver.DisplayAllDriversItem;
import telran.cars.controller.items.driver.DisplayCarDriversItem;
import telran.cars.controller.items.driver.DisplayDriverCarsItem;
import telran.cars.controller.items.driver.DisplayDriverItem;
import telran.cars.controller.items.manager.AddCarItem;
import telran.cars.controller.items.manager.AddModelItem;
import telran.cars.controller.items.manager.ClearItem;
import telran.cars.controller.items.manager.RemoveCarItem;
import telran.cars.controller.items.statist.DisplayModelProfitItem;
import telran.cars.controller.items.statist.DisplayMostPopularModelNamesItem;
import telran.cars.controller.items.statist.DisplayMostProfitableModelItem;
import telran.cars.controller.items.technician.DisplayAllRecords;
import telran.cars.controller.items.technician.DisplayReturnedRecordsItem;
import telran.cars.model.*;
import telran.view.*;

public class RentCompanyClientAppl {
private static final String DEFAULT_HOST_NAME = "localhost"; //rentcompany2018.herokuapp.com 0
private static final int DEFAULT_PORT = 8080;
static IRentCompany company;
static String fileName;
static InputOutput io=new ConsoleInputOutput();
	public static void main(String[] args) throws UnknownHostException, IOException {
		 String hostname=args.length==0?DEFAULT_HOST_NAME:args[0];
		 int port=args.length<1?DEFAULT_PORT:Integer.parseInt(args[1]);
		company=new RentCompanyWebProxy(hostname, port);
		Item items[]=getMainMenuItems();
		Menu menu=new MenuWithExit(io, items);
		menu.runMenu();

	}
	private static Item[] getMainMenuItems() {
		Item[]res= {
				new Submenu("Manager",
						getManagerMenu()),
				new Submenu
				("Clerk",getClerkMenu()),
				new Submenu
				("Driver",getDriverMenu()),
				new Submenu
				("Statist",getStatistMenu()),
				new Submenu
				("Technician"
						,getTechnicionMenu()),
				new ModelItem(io, company),
				new LoginItem(io, company)
		};
		return res;
	}
	private static Menu getTechnicionMenu() {
		   return new MenuWithExit(io,
				   new Item[]{
			new DisplayAllRecords(io, company),
			new DisplayReturnedRecordsItem
			(io, company)});
    }
	private static Menu getStatistMenu() {
		  return new MenuWithExit(io,
			new Item[]{
			new DisplayModelProfitItem(io, company),
			new DisplayMostPopularModelNamesItem(io, company),
			new DisplayMostProfitableModelItem(io, company)
			});
	}
	private static Menu getDriverMenu() {
		  return new MenuWithExit(io, new Item[]
				  {new DisplayAllDriversItem(io, company),
					new DisplayCarDriversItem(io, company), 
	                new DisplayDriverCarsItem(io, company),
	                new DisplayDriverItem(io, company)});
	}
	private static Menu getClerkMenu() {
		 return new MenuWithExit(io,
				 new Item[]
			{new AddDriverItem(io, company),
			 new DisplayCarItem(io, company),
			 new RentCarItem(io, company),
			 new ReturnCarItem(io, company)});
	}
	private static Menu getManagerMenu() {
		Item items[] = {
				new AddModelItem(io, company),
				new AddCarItem(io, company),
				new ClearItem(io, company),
				new RemoveCarItem(io, company)
				
		};
		Menu menu = new MenuWithExit(io, items);
		return menu;
	}

}
