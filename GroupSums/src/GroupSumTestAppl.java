import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupSumTestAppl {

	private static final int N_GROUPS = 10000;
	private static final int N_NUMBERS_GROUP = 10000;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		int[][] numbers = getRandomNumbers();
		List<GroupSum> listSum = new ArrayList<>();
		listSum.add( new GroupSumThreads(numbers));
		listSum.add( new GroupSumExecuter(numbers));
		listSum.add( new GroupSumStream(numbers));
    	listSum.add( new GroupSumParallelStream(numbers));
		System.out.printf("Amount of the groups - %d, amount of the numbers in each group - %d\n", N_GROUPS, N_NUMBERS_GROUP);
    	listSum.forEach(groupSum -> countSum(groupSum));

//    	for(int i = 0; i < args.length; i++) {
//    	Class<?> clazz = Class.forName(args[i]);
//    	GroupSum groupSum = (GroupSum) clazz.getConstructor().newInstance(numbers);
//    	countSum(groupSum);
//    	}
	}

	private static void countSum(GroupSum groupSum) {
		Instant start = Instant.now();
		long sum = groupSum.computeSum();
		long timeRun= ChronoUnit.MILLIS.between(start, Instant.now());
		System.out.printf("%s, running time: %d\n", groupSum.getClass().getSimpleName(), timeRun);
	}

	private static int[][] getRandomNumbers() {
		int[][] res = new int[N_GROUPS] [N_NUMBERS_GROUP];
		for (int i = 0; i < N_GROUPS; i++) {
			res[i] = new Random().ints(N_NUMBERS_GROUP, 1, Integer.MAX_VALUE).toArray();
		}
		return res;
	}

}
