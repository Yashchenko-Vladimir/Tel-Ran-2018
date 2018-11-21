import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BeetlesRun {
	
	public static void main(String[] args) {
		Scanner scaner = new Scanner(System.in);
		
		while(true) {
			
			System.out.println(" Games start");
			int cockroachCount = scaner.nextInt();
			int distance = scaner.nextInt();
			List<Beetl> cockroachs = new ArrayList<>();
	
			for(int i = 0; i < cockroachCount; i++) {
				cockroachs.add(new Beetl(i+1, distance));
			}
			
			cockroachs.forEach(x-> x.start());
			
			boolean fl = false;
				while(!fl) {
				for(Beetl bt : cockroachs) {
						if(!bt.isAlive()) {
							System.out.println("Winner cockroach - " + bt.getNumber());
							fl = true;
							break;
						}
				}
				}
				
				cockroachs.forEach(x -> {
					try {
						x.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				
							
			
				System.out.println("if you want game again enter yes or enter 'exit'");
				if(scaner.next().equals("exit")) {
					break;
				}
		
	}
	}
}
	
	
	
	

