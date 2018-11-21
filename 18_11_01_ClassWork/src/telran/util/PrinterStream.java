package telran.util;

public class PrinterStream extends Thread {
	PrinterStream printer;
	int id;
	int portion;
	int nPrints;
	private int printed = 0;
	
	
	
	public PrinterStream(int id, int portion, int nPrints) {
		this.id = id;
		this.portion = portion;
		this.nPrints = nPrints;
	}
	
	

	@Override
	public void run() {
		
		
//			
//		for(int i = 0; i< portion; i++ ){
//			System.out.print(id);
//		}
//		System.out.println();
//		printer.run();
//		interrupt();
		
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				printportion();
				printed  += portion;
				printer.interrupt();
			}
	}



	private void printportion() {
		for(int i = 0; i< portion; i++ ){
			System.out.print(id);
		}
			System.out.println();
		
	}



	public void setPrinter(PrinterStream printer) {
		this.printer = printer;
	}
	
	
}
