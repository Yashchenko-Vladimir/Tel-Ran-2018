import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Task {
	
	public static void main(String[] args) {
	Queue<Integer> qu = new LinkedList<Integer>();
	qu.offer(1);
	System.out.println(qu);
	qu.offer(2);
	System.out.println(qu);
	qu.remove();
	System.out.println(qu);
		
	}
	
}
