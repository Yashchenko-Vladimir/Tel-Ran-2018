import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {
	private BlockingQueue<Car> garage;
	private static List<Car> listRecords = new ArrayList<>();
	private static int countNoWork = -1;

	public Worker(BlockingQueue<Car> garage) {
		this.garage = garage;
		setDaemon(true);
	}

	@Override
	public void run() {
		while(true) {
			Car car = null;
			
				synchronized (garage) {
					while (garage.size() == 0) {
						countNoWork++;
						try {
							garage.wait(1);
						} catch (InterruptedException e) {
							
						}
					}
					car = garage.poll();
				}
					

					try {
						car.setNameThread(getName());
						car.setStartWorkTime(Instant.now());
						listRecords.add(car);
						sleep(car.getTimeWork());
					} catch (InterruptedException e) {
//						System.out.println("Thread: " + getName() + " finish work.");
						break;
					}
				
			
		}
	}

	public static List<Car> getListRecords() {
		return listRecords;
	}

	public static int getCountNoWork() {
		return countNoWork;
	}
	
	
	
	
	
}
