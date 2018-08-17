package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.LinkedList;
import telran.util.NodeList;

class LinkedList_HomeTask {
	LinkedList<Integer> numbers; 
	Integer [] arExp= {3,-10,20,100,80,13,150,98,24};

	
	@Test
	void testGetNode() {
		numbers = new LinkedList<Integer>();
		numbers = getFillLinkedlist(arExp);
		assertFalse(numbers.getNode());
		
		numbers = getFillLinkedlist(arExp);
		numbers.head.next.next.next.next = numbers.head.next;
		assertTrue(numbers.getNode());
				
	}
	
	private LinkedList<Integer> getFillLinkedlist(Integer []  ar) {
		int ind = 0;
	   for(Integer i : ar) {
		   numbers.add(ind, i);
	   ind++;
	   }
	   
		return numbers;
	}

}
