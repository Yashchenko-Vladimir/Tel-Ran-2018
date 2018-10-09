package java1.util.homework.lesson7;

public class Cat {
	private String name;
	private boolean isFull;

	public Cat(String name) {
		this.name = name;
	}
	
	
	public boolean isFull() {
		return isFull;
	}


	public void eat(Plate p) {
		if(!isFull) {
		if (p.getFood() > 15) {
			System.out.println(name + " eat from this plate");
			p.decreaseFood(15);
			isFull = true;
		} else {
			System.out.println(name + " can't eat from this plate");
		}
		}else {
			System.out.println(name + " allreidy full");
		}
	}
	
	

}
