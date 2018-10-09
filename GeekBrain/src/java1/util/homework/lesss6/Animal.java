package java1.util.homework.lesss6;

public class Animal {
	private  String name;
	private int maxRunDistanse;
	private int maxSwimDistanse;
	private int maxJumpHeugth;
	 
		
	public String getName() {
		return name;
	}

	public Animal(String name, int maxRunDistanse, int maxSwimDistanse, int maxJumpHeugth) {
		this.name = name;
		this.maxRunDistanse = maxRunDistanse;
		this.maxSwimDistanse = maxSwimDistanse;
		this.maxJumpHeugth = maxJumpHeugth;
	}


	public  void run(int distance) {
		if (distance <= maxRunDistanse) {
		System.out.println (name + " run " + distance);
		} else {
			System.out.println("I am not run  " +  distance);
		}
	}
	
	public  void swim(int distance) {
		if (maxSwimDistanse == 0) {
			System.out.println(" I can't swim");
			return;
		}
		if (distance < maxSwimDistanse) {
			System.out.println (name + " swim " + distance);
			} else {
				System.out.println("I am not swim this " +  distance);
			}
	}
	
	public  void jump(int height) {
		if (height < maxJumpHeugth) {
			System.out.println (name + " jump " + height);
			} else {
				System.out.println("I am not jump  " +  height);
			}
	}
}
