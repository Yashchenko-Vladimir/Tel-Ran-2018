import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GroupSumExecuter extends GroupSum {

	private int nThreads = 3;

	public GroupSumExecuter(int[][] numbers) {
		super(numbers);
		
	}

	@Override
	long computeSum() {
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		OneGroupSum[] groups = new OneGroupSum[numbers.length];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = new OneGroupSum(numbers[i]);
			executor.execute(groups[i]);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			
		}
		
		return Arrays.stream(groups).mapToLong(x-> x.sum).sum();
	}

	public int getnThreads() {
		return nThreads;
	}

	public void setnThreads(int nThreads) {
		this.nThreads = nThreads;
	}

}
