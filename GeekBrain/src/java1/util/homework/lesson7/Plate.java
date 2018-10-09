package java1.util.homework.lesson7;

public class Plate {
	private int food;

	public Plate(int food) {
		this.food = food;
	}
	
	public  void info() {
		System.out.println("Food : " + food);
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getFood() {
		return food;
	}
	
	public void decreaseFood(int amount) {
		food = food - amount;
			
	}
	
	public void addFoud(int amount) {
		food += amount;
	}
	
	
}
