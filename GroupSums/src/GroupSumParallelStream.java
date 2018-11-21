import java.util.Arrays;
import java.util.HashSet;

public class GroupSumParallelStream extends GroupSum {
	private  HashSet<String> setThread = new HashSet<>();

	public GroupSumParallelStream(int[][] numbers) {
		super(numbers);
	}

	@Override
	long computeSum() {
		long res = Arrays.stream(numbers).parallel().flatMapToInt(x -> {
			setThread.add(Thread.currentThread().getName());
			return Arrays.stream(x);
		}).mapToLong(x -> x).sum();
		System.out.println(setThread);
		return res;
	}

	public HashSet<String> getSetThread() {
		return setThread;
	}

	
}
