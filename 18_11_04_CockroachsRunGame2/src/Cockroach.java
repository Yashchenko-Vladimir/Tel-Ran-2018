import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Cockroach extends Thread{
	int coachroachId;
	int distance;
	private static List<Cockroach> result = new ArrayList<>();
	static private Instant finish;
	static private Instant start = Instant.now();
	Random gen = new Random();
	private static final Object mutex = new Object();
	
	public Cockroach(int coachroachId, int distance) {
		this.distance = distance;
		this.coachroachId = coachroachId;// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		for (int i = 0; i < distance; i++) {
			try {
				sleep(getrandomnumber());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		synchronized (mutex ) {
			finish = Instant.now();
			result.add(this);
		}
	}
	
	
	public static void displayResults() {
		int lenght = result.size();
		System.out.println("****************************");
		for(int i = 0; i < lenght; i++) {
			System.out.printf("%d. Tarakan %d time: %d\n", i + 1, result.get(i).coachroachId, ChronoUnit.MILLIS.between(start,finish) );
		}
		System.out.println("****************************");
	}


	private long getrandomnumber() {
				return (long) gen.ints(1, 2, 6).findFirst().getAsInt();
	}


	public int getNumber() {
		return coachroachId;
	}
	

}
