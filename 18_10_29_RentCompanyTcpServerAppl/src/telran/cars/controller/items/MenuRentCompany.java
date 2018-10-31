package telran.cars.controller.items;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.MenuWithExit;

public class MenuRentCompany extends MenuWithExit {

	private InputOutput io;
	private Item[] items;
	
	
 public MenuRentCompany(InputOutput io, Item[] items) {
		super(io, items);
		this.io = io;
		this.items = items;
	}
	
	public void runMenu() {
		
		while(true) {
			
			for(int i = 1; i <=items.length; i++ ) {
				io.outputLine(String.format("%d. %s", i, items[i-1].displayedName()));
			}
			
			Integer nItem = io.inputInteger("Type Item number ", 1, items.length);
			if(nItem == null) {
				break;
			}
			try {
				items[nItem - 1 ].perform();
			} catch (Throwable e) {
				io.outputLine(e.getMessage());
			}
			if(items[nItem-1].isExit())
				break;
		}
			
	}
	
}
