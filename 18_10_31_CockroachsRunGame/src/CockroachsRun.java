import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CockroachsRun {
	
	public static void main(String[] args) {
		Scanner scaner = new Scanner(System.in);
		List<Cockroach> cockroachs;
		
		while(true) {
					
			cockroachs = createdCockroachs(scaner);
			
			startGame(cockroachs);
					
				System.out.println("if you want game again enter yes or  NO enter 'exit'");
				if(scaner.next().equals("exit")) {
					break;
				}
		
		}
	}

	private static void startGame(List<Cockroach> cockroachs) {
		cockroachs.forEach(x-> x.start());
		List<Cockroach> result = new ArrayList<>();
		
			while(cockroachs.size()!=result.size()) {
				Cockroach co = null;
			for(Cockroach bt : cockroachs) {
					if(!bt.isAlive()) {
						result.add(bt);
					}
				}
			}
			
			System.out.println("Winner cockroach - " + result.get(0).getNumber());	
			System.out.println(result.size());
			
	}

	private static List<Cockroach> createdCockroachs(Scanner scaner) {
		List<Cockroach> cockroachs;
		cockroachs = new ArrayList<>();
		
		System.out.println(" Games start");
		System.out.print("Enter count cockroach ->");
		int cockroachCount = scaner.nextInt();
		System.out.print("Enter distance for cockroachs ->");
		int distance = scaner.nextInt();
		

		for(int i = 0; i < cockroachCount; i++) {
			cockroachs.add(new Cockroach(i+1, distance));
		}
		return cockroachs;
	}
}
	
	
	
	

