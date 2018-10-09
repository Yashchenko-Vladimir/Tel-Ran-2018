package java1.util.homework;

public class Le5Person {
	private String name;
	private String position;
	private int age;
	private int salary;
	
	
	public Le5Person(String name, String position, int age, int salary ) {
		this.name = name;
		this.position = position;
		this.age = age;
		this.salary = salary;
	}
	
	public int getAge() {
		return age;
	}
	
	public void info() {
		System.out.println("Name is " + name + ". Position - "+ position + "Age is " + age +
				". Salary - " + salary + ".");
	}

}
