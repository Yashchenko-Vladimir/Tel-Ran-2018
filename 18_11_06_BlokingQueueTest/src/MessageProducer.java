import telran.util.concurrent.MyBlokingQueue;

public class MessageProducer extends Thread {
	MyBlokingQueue<String> blkQueue;
	private static final int N_MESSAGE = 20;
	public MessageProducer(MyBlokingQueue<String> blkQueue) {
		super();
		this.blkQueue = blkQueue;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < N_MESSAGE; i++) {
			blkQueue.offer("message_" + i);
		}
	}
	
}
