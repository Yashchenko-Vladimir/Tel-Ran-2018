package telran.messaging.test;

import telran.messaging.MessegeBox;

public class Sender extends Thread {
	private static final int N_MESSAGES = 20;
	
	private MessegeBox messageBox1;
	private MessegeBox messageBox2;

	public Sender(MessegeBox messageBox1, MessegeBox messageBox2 ) {
		this.messageBox1 = messageBox1;
		this.messageBox2 = messageBox2;
			
		
	}

	@Override
	public void run() {
		for(int i = 0; i < N_MESSAGES; i++) {
			if(i%2 == 0)				
				messageBox1.putMessage("message_" + (i+1));
			else
				messageBox2.putMessage("message_" + (i+1));

		}
		
	}
	
	
	
	
	
	
	
}
