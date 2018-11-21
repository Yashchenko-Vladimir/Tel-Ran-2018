import java.time.Instant;

public class Car {
	private Instant arriveTime;
	private Instant startWorkTime;
	private int timeWork;
	private String nameThread;
	
	
	public Car(Instant arriveTime) {
		this.arriveTime = arriveTime;
	}


	public Instant getStartWorkTime() {
		return startWorkTime;
	}


	public void setStartWorkTime(Instant startWorkTime) {
		this.startWorkTime = startWorkTime;
	}


	public int getTimeWork() {
		return timeWork;
	}


	public void setTimeWork(int timeWork) {
		this.timeWork = timeWork;
	}


	public String getNameThread() {
		return nameThread;
	}


	public void setNameThread(String nameThread) {
		this.nameThread = nameThread;
	}


	public Instant getArriveTime() {
		return arriveTime;
	}


	@Override
	public String toString() {
		return "Car [arriveTime=" + arriveTime + ", startWorkTime=" + startWorkTime + ", timeWork=" + timeWork
				+ ", nameThread=" + nameThread + "]";
	}
	
	
	
}
