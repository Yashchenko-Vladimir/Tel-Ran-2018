import java.util.List;
import java.util.concurrent.locks.Lock;

public class ListProcessing extends Thread {
	private final List<Integer> list;
	private final Lock monitorRead;
	private final Lock monitorWriter;
	private int probAdd;
	private int nRuns;
	private static int countBlocked = -1;
	
	
	public ListProcessing(List<Integer> list, int probAdd, int nRuns, Lock monitorRead, Lock monitorWriter) {
		super();
		this.list = list;
		this.probAdd = probAdd;
		this.nRuns = nRuns;
		this.monitorRead = monitorRead;
		this.monitorWriter = monitorWriter;
	}


	@Override
	public void run() {
		for (int i = 0; i < nRuns; i++) {
			if(getRandomNumber(1,100) <= probAdd)
				addToList();
			else
				readFromList();
		}
	}


	private void readFromList() {
		tryLock(monitorRead);
			
			try {
				list.contains((getRandomNumber(1, Integer.MAX_VALUE)));
			} finally {
				monitorRead.unlock();
			}
				
	}


	private void addToList() {
		tryLock(monitorWriter);
			try {
				list.add(getRandomNumber(1, Integer.MAX_VALUE));
				list.remove(list.size() - 1);
			} finally {
				monitorWriter.unlock();
			}
		
	}


	private void tryLock(Lock monitor) {
		boolean lockresult = false;
		do {
			
			lockresult = monitor.tryLock();
			if(!lockresult)
				countBlocked++;
		} while (!lockresult);
	}


	private int getRandomNumber(int min, int max) {
		
		return (int) (min + Math.random()* (max - min));
	}


	public static int getCountBlocked() {
		
		return countBlocked;
	}
	
	
}
