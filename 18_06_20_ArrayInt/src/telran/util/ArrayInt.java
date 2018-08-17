package telran.util;

public class ArrayInt {
	private static final int INITIAL_CAPACITY = 16;
	private static final int N_RESERV = 2;
	private int[] ar;
	private int size;
	private boolean flSorted;

	public ArrayInt(int[] ar) {
		this.ar = new int[ar.length > INITIAL_CAPACITY ? ar.length : INITIAL_CAPACITY];
		flSorted = true;
		if (ar.length != 0) {
			this.ar[0] = ar[0];

			for (int i = 1; i < ar.length; i++) {
				this.ar[i] = ar[i];
				if (ar[i] < ar[i - 1])
					flSorted = false;

			}
			size = ar.length;
		}
	}

	public ArrayInt() {
		ar = new int[INITIAL_CAPACITY];
	}

	public int search(int pattern) {

		return flSorted ? searchSorted(pattern) : searchUnsorted(pattern);
	}

	private int searchUnsorted(int pattern) {
		for (int i = 0; i < size; i++) {
			if (pattern == ar[i])
				return i;
		}
		return -(size + 1);
	}

	public boolean isSorted() {
		return flSorted;
	}

	public void sort() {
		int s = size-1;
		  int tmp;
		  for (int i = 0; i < s; i++) {
		   for (int j = 0; j <s; j++) {
		    if (ar[j] > ar[j +1]) {
		     tmp = ar[j];
		    ar[j] = ar[j +1];
		    ar[j+1] = tmp;
		    }
		   
		   }
		  }
		  flSorted = true;
		  
	}

	private int searchSorted(int pattern) {
		int left = 0;
		int right = size - 1;
		int middle = 0;
		while (left <= right) {
			middle = (left + right) / 2;
			if (ar[middle] == pattern)
				return middle;
			if (pattern < ar[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return -(left + 1);
	}

	public Integer get(int index) {

		return index >= 0 && index < size ? ar[index] : null;
	}

	public void add(int number) {
		if (size == ar.length) {
			allocateMemory();
		}
		if (!flSorted)
			ar[size] = number;
		else {
			insertSorted(number);
		}
		size++;

	}

	private void insertSorted(int number) {
		int index = search(number);
		if (index < 0) {
			index = (-index) - 1;
		}
		insertAt(number, index);

	}

	private void insertAt(int number, int index) {
		for (int i = size; i > index; i--)
			ar[i] = ar[i - 1];
		ar[index] = number;

	}

	private void allocateMemory() {
		int tmp[] = new int[ar.length * N_RESERV];
		for (int i = 0; i < size; i++)
			tmp[i] = ar[i];
		ar = tmp;

	}

	/**
	 * removes first occurrence of number in array
	 * 
	 * @param number
	 * @return true if number has been removed from array
	 */
	public boolean remove(Integer number) {
		int index = search(number);
		if (index < 0)
			return false;
		removeAt(index);
		return true;

	}

	private void removeAt(int index) {
		for (int i = index + 1; i < size; i++) {
			ar[i - 1] = ar[i];
		}
		size--;

	}

	/**
	 * removes number at a given index
	 * 
	 * @param index
	 * @return true if index is correct
	 */
	public boolean remove(int index) {
		boolean res = false;
		if (index >= 0 && index < size) {
			removeAt(index);
			res = true;
		}
		return res;
	}

	/**
	 * 
	 * @return amount of numbers in the array
	 */
	public int size() {
		return size;
	}

	/**
	 * mix order of numbers in the random order
	 */
		public void shuffle() {
			  int tmp [] = new int [size];
			  for (int i = 0; i < tmp.length && size > 0; i++) {
			   int index = (int) (Math.random()*size);
			   tmp[i] = ar[index];
			   
			   for (int a = index + 1 ; a <size; a++) {
			    ar[a-1] = ar[a];
			   }
			   size--;
			  // remove(index);
			  }
			  ar = tmp;
			  size = tmp.length;
			  flSorted = false;

	}
}
