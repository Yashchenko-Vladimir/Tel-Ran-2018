import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Cockroach extends Thread{
	int coackrochId;
	int distance;
	
	static private AtomicInteger photoFinish;
	
	public int getNumber() {
		return coackrochId;
	}


	public void setCoackrochId(int coackrochId) {
		this.coackrochId = coackrochId;
	}


	Random gen = new Random();
	
	public Cockroach(int coackrochId, int distance) {
		photoFinish = new AtomicInteger(-1);
		this.distance = distance;
		this.coackrochId = coackrochId;// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		for (int i = 0; i < distance; i++) {
			try {
				sleep(getrandomnumber());
				System.out.println("coackroach" + coackrochId + "passed" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
//		if(photoFinish.get() == 0) {
//			photoFinish.set(co)
		
		photoFinish.compareAndSet(-1, coackrochId);
	}


	private long getrandomnumber() {
				return (long) gen.ints(1, 2, 6).findFirst().getAsInt();
	}


	
	

}
