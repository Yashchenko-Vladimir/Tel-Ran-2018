import telran.util.concurrent.MyBlokingQueue;

public class MessageConsumer extends Thread {
	MyBlokingQueue<String> blkQueue;
	
	@Override
	public void run() {
		while(true) {
			System.out.printf("thread: %d, message: %s\n", getId(), blkQueue.poll());
		}
	}

	public MessageConsumer(MyBlokingQueue<String> blkQueue) {
		super();
		this.blkQueue = blkQueue;
		setDaemon(true);
	}
	
	
}
