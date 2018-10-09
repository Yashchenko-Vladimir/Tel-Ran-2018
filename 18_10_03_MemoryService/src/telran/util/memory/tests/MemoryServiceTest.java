package telran.util.memory.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import telran.util.MemoryService;

public class MemoryServiceTest {
	byte [] ar;


	@Test
	public void test() {
		int size = MemoryService.getMaxAvailableMemorySize();
		ar = new byte[size];
		boolean flException = false;
		try {
			ar = null;
			ar = new byte[size+1];
			
		} catch (Throwable e) {
			flException = true;
		}
		
		
		assertTrue(flException);
		System.out.println(size);
	}

}
