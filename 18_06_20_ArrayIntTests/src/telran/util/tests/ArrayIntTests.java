package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import telran.util.ArrayInt;
import telran.util.LimitedQueue;

class ArrayIntTests {
	
	@Test
	void searchSorted() {
		int ar1[]= {20,25,40,100};
		ArrayInt array1=new ArrayInt(ar1);
		assertEquals(1,array1.search(25));
		assertEquals(-3,array1.search(26));
		
		
	}
	@Test
	void searchUnsorted() {
		int ar2[]= {25,100,20,40};
		ArrayInt array2=new ArrayInt(ar2);
		assertEquals(0,array2.search(25));
		assertEquals(-5,array2.search(26));
		
	}
	@Test 
	void get() {
		int ar2[]= {25,100,20,40};
		ArrayInt array=new ArrayInt(ar2);
		//int n=array.get(1);
		assertEquals(100,(int)array.get(1));
		assertNull(array.get(4));
		assertNull(array.get(-1));
	}
	@Test
	void addSorted() {
		int ar[]=new int[10000];
		ArrayInt array=
				new ArrayInt(new int[0]);
		runAddTest(ar, array);
		
	}
	@Test
	void addUnSorted() {
		int ar[]=new int[10000];
		ArrayInt array=
				new ArrayInt();
		runAddTest(ar, array);
	}
	private void runAddTest(int[] ar, ArrayInt array) {
		fillArray(ar,array);
		testArray(ar,array);
	}
	private void testArray(int[] ar, ArrayInt array) {
		assertEquals(ar.length,array.size());
		for(int i=0;i<ar.length;i++)
			assertEquals(ar[i],(int)array.get(i));
		
	}
	private void fillArray(int[] ar, ArrayInt array) {
		for(int i=0;i<ar.length;i++) {
			array.add(i);
			ar[i]=i;
		}
		
	}
	@Test
	public void removeNumber() {
		int ar[]= {10,2,20,15,100};
		int expected[]={10,20,15,100};
		ArrayInt array=new ArrayInt(ar);
		assertTrue(array.remove((Integer)2));
		testArray(expected, array);
		assertFalse(array.remove((Integer)3));
		
	}
	@Test
	public void removeByIndex() {
		int ar[]= {10,2,20,15,100};
		int expected[]={10,20,15,100};
		ArrayInt array=new ArrayInt(ar);
		assertTrue(array.remove(1));
		testArray(expected, array);
		assertFalse(array.remove(5));
		assertFalse(array.remove(-1));
		
	}
	@Test
	public void shuffle() {
		int ar[]=new int[10000];
		ArrayInt array=new ArrayInt(new int[0]);
		fillArray(ar, array);
		ArrayInt arraySh=new ArrayInt(ar);
		arraySh.shuffle();
		testAfterShuffle(array,arraySh);
		ArrayInt arraySh1=new ArrayInt(ar);
		arraySh1.shuffle();
		testAfterShuffle(arraySh,arraySh1);
		
	}
	private void testAfterShuffle(ArrayInt array,
			ArrayInt arraySh) {
		int []ar1=getArray(array);
		int []ar2=getArray(arraySh);
		testSameNumbers(ar1,ar2);
		assertFalse(Arrays.equals(ar1, ar2));
		
	}
	
	private void testSameNumbers(int[] ar1,
			int[] ar2) {
		int[] ar1Sorted=Arrays.copyOf(ar1, ar1.length);
		Arrays.sort(ar1Sorted);
		int[] ar2Sorted=Arrays.copyOf(ar2, ar2.length);
		Arrays.sort(ar2Sorted);
		assertArrayEquals(ar1Sorted, ar2Sorted);
		
	}
	private int[] getArray(ArrayInt array) {
		int size=array.size();
		int []res=new int[size];
		for(int i=0;i<size;i++)
			res[i]=array.get(i);
		return res;
	}
	@Test
	public void sort() {
		int ar[]=new int[10000];
		ArrayInt array=new ArrayInt();
		fillArray(ar, array);
		array.shuffle();
		array.sort();
		testSort(array);
	}
	private void testSort(ArrayInt array) {
		assertTrue(array.isSorted());
		int size=array.size();
		for(int i=1;i<size;i++) {
			assertTrue(array.get(i-1)<=array.get(i));
		}
		
	}
	@Test
	public void Queue() {
		LimitedQueue ar= new LimitedQueue(3);
		ar.add(5);
		ar.add(5);
		ar.add(5);
		assertFalse(ar.add(6));
		ar.offer();
		assertTrue(ar.add(6));
		ar.offer();
		ar.offer();
		assertTrue(ar.offer().equals(6));
		assertTrue(ar.offer() == null);
		
		
	}
	

}
