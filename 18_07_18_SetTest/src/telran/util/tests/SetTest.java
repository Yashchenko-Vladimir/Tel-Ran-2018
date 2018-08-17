package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashTable;
import telran.util.Tree;

class SetTest {
	
	Set<Integer> set;
	
	Integer [] expOriginal = {100, 80, 40, 30, 20, 50, 82, 90, 85, 95};
	Integer [] expAdd  =  {100, 80, 40, 30, 20, 50, 82, 90, 85, 95, 1000};
	Integer [] expNo100 = {80, 40, 30, 20, 50, 82, 90, 85, 95};
	Integer [] expNo80 = {100, 40, 30, 20, 50, 82, 90, 85, 95};
	Integer [] expNo30 = {100, 80, 40, 20, 50, 82, 90, 85, 95};
	Integer [] expNo82 = {100, 80, 40, 30, 20, 50, 90, 85, 95};
	Integer [] expNo20 = {100, 80, 40, 30, 50, 82, 90, 85, 95};
	Integer [] expNo95 = {100, 80, 40, 30, 20, 50, 82, 90, 85};
	Integer [] expEven = {100,80,40,30,20,50,82,90};
	Integer [] expOdd  = {85,95};

	@BeforeEach
	void setUp() throws Exception {
		set = new HashTable<>();
		createSet (set, expOriginal);
		
	}

	private void createSet(Set<Integer> set, Integer[] array) {
		for (Integer number : array) {
			set.add(number);
		}
		
	}

	@Test
	void contains() {
		assertTrue(set.contains(100));
		assertTrue(set.contains(20));
		assertTrue(set.contains(95));
		assertFalse(set.contains(1000));
	}
		
	@Test 
	void testIterator () {
			testSetArray(set, expOriginal);
		}

	private void testSetArray(Set<Integer> set, Integer[] expected) {
		Integer[] actual = new Integer[expected.length];
		int ind = 0;
		for(Integer number: set) {
			actual[ind++] = number;			
		}
		Arrays.sort(expected);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
	}
	
	@Test 
	void testAdd() {
		assertFalse(set.add(40));
		assertTrue(set.add(1000));
		testSetArray(set, expAdd);
	}
	
	@Test
	void remove100() {
		set.remove(100);
		testSetArray(set, expNo100);
	}
	
	@Test
	void remove80() {
		set.remove(80);
		testSetArray(set, expNo80);
	}
	
	@Test
	void remove30() {
		set.remove(30);
		testSetArray(set, expNo30);
	}
	@Test
	
	void remove82() {
		set.remove(82);
		testSetArray(set, expNo82);
		
	}
	
	@Test
	void remove20() {
		set.remove(20);
		testSetArray(set, expNo20);
		
	}
	
	@Test
	void remove95() {
		set.remove(95);
//		for(Integer aa: set) {
//			System.out.println(aa);
//		}
		testSetArray(set, expNo95);
	}
	
	@Test
	void retainAll() {
		Set<Integer> setOdd=new HashTable<>();
		createSet(setOdd,expOdd);
		assertTrue(set.retainAll(setOdd));
		testSetArray(setOdd, expOdd);
		assertFalse(set.retainAll(setOdd));
		set.retainAll(new Tree<>());
		assertEquals(0,set.size());
		testSetArray(set, new Integer[0]);
		
	}
	@Test
	void removeAll() {
		Set<Integer> setOdd=new Tree<>();
		createSet(setOdd,expOdd);
		assertTrue(set.removeAll(setOdd));
		testSetArray(set, expEven);
		assertFalse(set.removeAll(setOdd));
	}
	@Test
	void clear() {
		set.clear();
		assertEquals(0,set.size());
		testSetArray(set, new Integer[0]);
	}
	@Test
	void removeIf() {
		set.removeIf(x->x%2==0);
		testSetArray(set,expOdd);
		set.removeIf(x->true);
		assertEquals(0,set.size());
		testSetArray(set, new Integer[0]);
	}

}











