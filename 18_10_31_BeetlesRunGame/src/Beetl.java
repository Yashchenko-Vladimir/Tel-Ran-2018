import java.util.Random;

public class Beetl extends Thread{
	int number;
	int distance;
	Random gen = new Random();
	
	public Beetl(int number, int distance) {
		this.distance = distance;
		this.number = number;// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		for (int i = 0; i < distance; i++) {
			try {
				sleep(getrandomnumber());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	private long getrandomnumber() {
				return (long) gen.ints(1, 2, 6).findFirst().getAsInt();
	}


	public int getNumber() {
		return number;
	}
	

}
