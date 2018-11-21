import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class ListProcessingTestAppl {

	private static final int N_THREADS = 100;
	private static final long N_NUMBERS = 10;
	private static final int PROB_ADD = 50;
	private static final int N_RUNS = 10_000;

	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new Random().ints(N_NUMBERS, 1, Integer.MAX_VALUE).boxed().collect(Collectors.toList());
		Lock lockRead = new ReentrantReadWriteLock().readLock();
		Lock lockWrite = new ReentrantReadWriteLock().writeLock();
		ListProcessing[] ar = new ListProcessing[N_THREADS];
		for (int i = 0; i < N_THREADS; i++) {
			ar[i] =new ListProcessing(list, PROB_ADD, N_RUNS, lockRead, lockWrite);
			ar[i].start();
		}
		for(ListProcessing listprocessing : ar) {
			listprocessing.join();
		}
		System.out.println("count blocked= " + ListProcessing.getCountBlocked());
	}

}
