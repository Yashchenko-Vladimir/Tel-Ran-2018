package telran.messaging.test;

import telran.messaging.MessegeBox;

public class TestMessageingAppl {

	private static final int N_RECIEVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		
		MessegeBox messageBox1 = new MessegeBox();
		MessegeBox messageBox2 = new MessegeBox();
		
		for (int i = 0; i < N_RECIEVERS; i++) {
			Reciever reciever = new Reciever();
			if(reciever.getId()%2 == 0)
				reciever.setMessegeBox(messageBox2);
			else
				reciever.setMessegeBox(messageBox1);
			reciever.start();
			
		}
		
		new Sender(messageBox1, messageBox2).start();
	
		Thread.sleep(1000);

	}

}

