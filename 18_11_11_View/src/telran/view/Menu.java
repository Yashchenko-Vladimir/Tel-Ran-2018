package telran.view;

public class Menu {
private InputOutput io;
private Item[]items;
public Menu(InputOutput io, Item[] items) {
	super();
	this.io = io;
	this.items = items;
}
public void runMenu() {
	while(true) {
		for(int i=1;i<=items.length;i++) {
			io.outputLine
		(String.format("%d. %s",i,items[i-1].displayedName()));
		}
		Integer nItem=io.inputInteger("Type item number", 1,
				items.length);
		if(nItem==null)
			break;
		try {
			items[nItem-1].perform();
		} catch (Throwable e) {
			io.outputLine(e.getMessage());
		}
		if(items[nItem-1].isExit())
			break;
	}
}
}
