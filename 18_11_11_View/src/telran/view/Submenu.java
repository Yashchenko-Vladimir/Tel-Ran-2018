package telran.view;

public class Submenu implements Item {
String name;
Menu menu;
	@Override
	public String displayedName() {
		return name;
	}

	@Override
	public void perform() {
		menu.runMenu();
		
	}

	public Submenu(String name, Menu menu) {
		super();
		this.name = name;
		this.menu = menu;
	}

}
