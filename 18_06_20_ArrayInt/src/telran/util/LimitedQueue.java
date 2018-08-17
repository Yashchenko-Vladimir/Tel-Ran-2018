package telran.util;

public class LimitedQueue {
	private int first = 0;
	private int size = 0;
	private Integer [] ar;

	public LimitedQueue(int limit) {
		this.ar = new Integer[limit];
	}
	
	public Integer offer() {
		if(size == 0)
			return null;
		int res = ar[first%ar.length];
		 ar[first%ar.length] = null;
		first++;
		size--;
		return res;
	}
	
	public boolean add(int number) {
		if(size == ar.length)
			return false;
		ar[(first + size)%ar.length]= number;
		size++;
		return true;
	}
	
}
