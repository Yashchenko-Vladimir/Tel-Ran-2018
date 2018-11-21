
public class Truck extends Thread{
	
	static long elevator1 = 0;
	static long elevator2 = 0;
	int nLoads;
	static final Object mutex = new Object(); 
	
	
	public Truck(int nLoads) {
		
		this.nLoads = nLoads;
	}


	public static long getElevator1() {
		return elevator1;
	}


	public static long getElevator2() {
		return elevator2;
	}


	@Override
	public void run() {
		for (int i = 0; i < nLoads; i++) {
	
				loadElevator1();
				loadElevator2();
				
		}
	}


	static private void loadElevator2() {
		synchronized (mutex) {
			elevator2++;
		}
		
	}


	synchronized static private void loadElevator1() {
		elevator1++;
		
	}
	
	
	
}
