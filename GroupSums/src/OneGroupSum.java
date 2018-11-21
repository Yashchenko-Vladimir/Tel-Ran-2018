import java.util.Arrays;

public class OneGroupSum implements Runnable {
	long sum;
	int group [];
	
	
	public OneGroupSum(int [] group) {
		this.group = group;
	}
	@Override
	public void run() {
		sum = Arrays.stream(group).mapToLong(x->x).sum();
//		System.out.println(sum);
	}

}
