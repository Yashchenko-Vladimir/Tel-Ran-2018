package java1.util.homework;

public class Le5Main {
	
public static void main(String[] args) {
	
	Le5Person [] workers = new Le5Person[4];
	
	workers[0] = new Le5Person("John", "employer", 32, 10000);
	workers[1] = new Le5Person("Janny", "employer", 28, 9000);
	workers[2] = new Le5Person("Fedar", "boss", 50, 20000);
	workers[3] = new Le5Person("Natan", "proger", 48, 15000);
	
	for (int i = 0; i < workers.length; i++) {
		if (workers[i].getAge() > 35) {
			workers[i].info();
		}
	}
	
}
}
