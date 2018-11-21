import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MultithreadingTestAppl {

	private static final int N_PRINTERS = 100;

	public static void main(String[] args) throws InterruptedException {

		Printer printer1 = new Printer("*", N_PRINTERS);
		Printer printer2 = new Printer("&", N_PRINTERS);
		Instant start = Instant.now();
		printer1.start();
		printer2.start();
		printer1.join();
		printer2.join();
		Instant finish = Instant.now();
		System.out.println("running time:" + ChronoUnit.MILLIS.between(start, finish));
		
	}

}

// задать количество тараканов, дистанцию, должна напечатать победителя