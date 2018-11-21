package telran.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintsStreamController {

	
	private static final int PORTION = 10;
	private static final int N_PRINTS = 100;
	private static final int COUNT = 2;
	
	

	public static void main(String[] args) {
	
		PrinterStream [] listprint = new PrinterStream[COUNT];
		for(int i = 1; i<= COUNT; i++ ){
			listprint[i-1] = new PrinterStream(i, PORTION, N_PRINTS);
		}
		
		for(int i = 1; i< COUNT; i++ ){
			listprint[i-1].setPrinter(listprint[i]);
			
				
			}
		listprint[COUNT-1].setPrinter(listprint[0]);
		
		Arrays.asList(listprint).forEach(x-> {
			x.start();
			x.interrupt();
		});
		listprint[0].run();
		
		

	}

}
