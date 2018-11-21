
public class Printer extends Thread {
	String printed;
	int nPrints;
	public Printer(String printed, int nPrints) {
		super();
		this.printed = printed;
		this.nPrints = nPrints;
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < nPrints; i++) {
			System.out.println(printed);
			try {
				sleep(2); // -> move to waiting two millisecond
				// no interrupted
			} catch (InterruptedException e) {
				// there was interrupted from outside threadTODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
