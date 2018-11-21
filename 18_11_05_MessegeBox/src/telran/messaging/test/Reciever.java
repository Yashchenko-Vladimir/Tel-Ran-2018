package telran.messaging.test;

import telran.messaging.MessegeBox;

public class Reciever extends Thread {
	
	private MessegeBox messegeBox;
	
	public Reciever() {
		setDaemon(true);
	}
	
	@Override
	public void run() {
			while (true) {
			String message = messegeBox.getMessage();
			System.out.printf("thread %d: %s\n", getId(), message);
			
		}
	}
	
	
	public void setMessegeBox(MessegeBox messegeBox) {
		this.messegeBox = messegeBox;
	}
	

}
