package telran.view;

import java.util.Arrays;

public class MenuWithExit extends Menu {

	public MenuWithExit(InputOutput io, Item[] items) {
		super(io, addExitItem(items));
		
	}

	private static Item[] addExitItem(Item[] items) {
		Item[]res=
				Arrays.copyOf(items, items.length+1);
		res[items.length]=new ExitItem();
		return res;
	}

}
