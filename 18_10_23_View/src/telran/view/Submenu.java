package telran.view;

public class Submenu implements Item {
	String name;
	Menu menu;
	

	public Submenu(String name, Menu menu) {
		this.name = name;
		this.menu = menu;
	}

	@Override
	public String displayedName() {
		return name;
	}

	@Override
	public void perform() {
		menu.runMenu();

	}

}
