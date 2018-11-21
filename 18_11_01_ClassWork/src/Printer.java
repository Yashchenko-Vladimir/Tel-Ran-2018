
public class Printer extends Thread {
String str;

public Printer(String str) {
	this.str = str;
	setDaemon(true);
}

@Override
public void run() {
	while(true){
		System.out.print(str);
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			
			System.out.println("next letter");
			break;
		}
	}
}

}
