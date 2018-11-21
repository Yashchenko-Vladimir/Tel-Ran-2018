import java.awt.color.ICC_ColorSpace;
import java.util.concurrent.atomic.AtomicLong;

public class Truck extends Thread{
	
	static AtomicLong elevator1 = new AtomicLong(0);
	static AtomicLong elevator2 = new AtomicLong(0);
	int nLoads;

	
	public Truck(int nLoads) {
		
		this.nLoads = nLoads;
	}


	public static long getElevator1() {
		return elevator1.get();
	}


	public static long getElevator2() {
		return elevator2.get();
	}


	@Override
	public void run() {
		for (int i = 0; i < nLoads; i++) {
	
				loadElevator1();
				loadElevator2();
				
		}
	}


	static private void loadElevator2() {
		elevator2.incrementAndGet();
			
	}


	 static private void loadElevator1() {
		elevator1.incrementAndGet();
		
	}
	
	
	
}
