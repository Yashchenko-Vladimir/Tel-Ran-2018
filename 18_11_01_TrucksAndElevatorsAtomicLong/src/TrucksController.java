import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TrucksController {

	private static final int N_TRUCKS = 1000;
	private static final int N_LOADS = 1000;

	public static void main(String[] args) throws InterruptedException {
		Truck [] trucks = new Truck[N_TRUCKS];
		Instant start = Instant.now();
		for(int i = 0; i < N_TRUCKS; i++){
			trucks[i] = new Truck(N_LOADS);
			trucks[i].start();
		}
		for(Truck truck : trucks){
			truck.join();
		}
		Instant finish = Instant.now();
		
		System.out.printf("elevator1 =%d\nelevator2 =%d\n ..... running time = %d", 
				Truck.getElevator1(), Truck.getElevator2(), ChronoUnit.MILLIS.between(start, finish));
	}

} 
