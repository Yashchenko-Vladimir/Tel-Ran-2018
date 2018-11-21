import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
	
	public Timer(){
		setDaemon(true);
	}
	
	
	@Override
	public  void run(){
		
		while(true){
			LocalTime time = LocalTime.now();
			DateTimeFormatter fd = DateTimeFormatter.ofPattern("hh:mm:ss");
			System.out.println(time.format(fd));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("timer finished");
				break;
			}
		}
	}
}
