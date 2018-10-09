package java1.util.homework.lesson7;

public class Main {

	public static void main(String[] args) {
		
		Cat cat1 = new Cat("Vaska");
		Plate plate1 = new Plate(100);
		Plate plate2 = new Plate(10);
		
		
		Cat [] cats = {
				new Cat("1"),
				new Cat("2"),
				new Cat("3"),
				new Cat("4"),
				new Cat("5"),
				new Cat("6"),
				new Cat("7"),
				
		};
		
		for(Cat ct: cats) {
			ct.eat(plate1);
			System.out.println(ct.isFull());
		}
		
		cats[1].eat(plate1);
		plate1.info();
		plate1.addFoud(40);
		plate1.info();
		cats[6].eat(plate1);
		plate1.info();
		
		
		

	}
	
	

}
