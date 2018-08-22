package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import telran.util.HashTable;
import telran.util.Tree;

class SetTest {
	
	Tree<Integer> set;
	
	Integer [] expOriginal = {100, 80, 40, 30, 20, 50, 82, 90, 85, 95};

	@BeforeEach
	void setUp() throws Exception {
		set = new Tree<>();
		createSet(set,expOriginal);
	}


	private void createSet(Set<Integer> set, Integer[] array) {
		for(Integer number:array) {
			set.add(number);
		}
	}

	@Test
	void testIterator() {
		testSetArray(set,expOriginal);
	}

	private void testSetArray(Set<Integer> set, Integer[] expected) {
		Integer[]actual=new Integer[expected.length];
		int ind=0;
		for(Integer number:set) {
			actual[ind++]=number;
		}
		Arrays.sort(expected);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
	}
	
//	@Test
//	void toArray() {
//		Object[]actual=set.toArray();
//		Arrays.sort(expOriginal);
//		Arrays.sort(actual);
//		assertArrayEquals(expOriginal,actual);
//	}
	
	@Test
	void testSiseRecursion() {
		assertEquals(expOriginal.length, set.getSizeRecursion());
	}
	@Test
	void printRotated() {
		set.setSpacePerLevel(5);
		set.printRotated();
	}
	
	@Test
	void width() {
		assertEquals(4, set.width());
		
	}
	
	@Test
	void height() {
		assertEquals(5, set.height());
		
	}

}











