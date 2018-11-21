import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Service {
	private static Random gen = new Random();
	private static final int N_HOURS = 2;
	private static final int N_DAYS = 2;
	private static final int PROB = 30;
	private static final int N_WORKERS = 10;
	private static BlockingQueue<Car> garage;

	private static int nMin = N_HOURS * 60 * N_DAYS;

	public static void main(String[] args) throws InterruptedException {
		garage = new LinkedBlockingQueue<>();
		List<Worker> workers = new ArrayList<>();
		for (int i = 0; i < N_WORKERS; i++) {
			Worker worker = new Worker(garage);
			workers.add(worker);
			worker.start();
		}
		for (int i = 1; i <= nMin; i++) {
			if (getrandomNumber(1, 100) < PROB)
				repairCar();
			sleep(1);
		}

		workers.forEach(x -> x.interrupt());
		System.out.println("Quantity cars in queue: "+ garage.size());
		
		List<Car> listCar = Worker.getListRecords();
//		listCar.forEach(it -> System.out.println(ChronoUnit.MILLIS.between(it.getArriveTime(), it.getStartWorkTime())));
		Double waitingCar = listCar.stream()
				.mapToDouble(it -> ChronoUnit.MILLIS.between(it.getArriveTime(), it.getStartWorkTime())).average()
				.getAsDouble();
		System.out.println("Car waiting: " + waitingCar);
		System.out.println("Worker waiting: " + (Worker.getCountNoWork() / N_WORKERS));
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			}
	}

	private static void repairCar() {
		Car car = new Car(Instant.now());
		int probabilityRepair = getrandomNumber(1, 100);
		if (probabilityRepair < 75) {
			car.setTimeWork(getrandomNumber(120, 360));
		} else if (probabilityRepair < 85)
			car.setTimeWork(getrandomNumber(60, 720));
		else
			car.setTimeWork(getrandomNumber(60, 180));

		garage.offer(car);

	}

	private static int getrandomNumber(int min, int max) {
		return gen.ints(1, min, max + 1).findFirst().getAsInt();

	}
}
