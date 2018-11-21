import java.util.Arrays;

public class GroupSumThreads extends GroupSum {

	public GroupSumThreads(int[][] numbers) {
		super(numbers);
		
	}

	@Override
	long computeSum() {
		Thread[] threads = new Thread[numbers.length];
		OneGroupSum[] groups = new OneGroupSum[numbers.length];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = new OneGroupSum(numbers[i]);
			threads[i] = new Thread(groups[i]);
			threads[i].start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		Arrays.stream(groups).forEach(x -> System.out.println(x.sum));
		return Arrays.stream(groups).mapToLong(g -> g.sum).sum();
	}

// посчитать обычным массивом
// другой класс параллельльными потоками stream расспечатать имена средов участвовавшие в процессе
	
	
}
