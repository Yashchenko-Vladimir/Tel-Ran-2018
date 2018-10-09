package telran.util;


public class MemoryService {
	public static int getMaxAvailableMemorySize() {
		int size = 0;
		   int min = 0;
	        int max = Integer.MAX_VALUE;
	        int midle = 0;
	        byte [] ar = null;
	               
	        while (min <= max) {
	            try {
	            	midle = max/2 + min /2;
	            	ar = null;
	                ar = new byte[midle];
	                size = midle;
	                min = midle + 1;
	            } catch (OutOfMemoryError e) {
	                max = midle-1;
	            }
	        }
	        
	    return size;
	}
}