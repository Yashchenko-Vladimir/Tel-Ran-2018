import java.util.Arrays;

public class GroupSumStream extends GroupSum {

	public GroupSumStream(int[][] numbers) {
		super(numbers);
		
	}

	@Override
	long computeSum() {
		return Arrays.stream(numbers).flatMapToInt(x -> Arrays.stream(x)).mapToLong(x -> x).sum();
	}

}
