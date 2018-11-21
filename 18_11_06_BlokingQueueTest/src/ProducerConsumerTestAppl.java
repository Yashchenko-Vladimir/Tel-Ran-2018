import telran.util.concurrent.MyBlokingQueue;

public class ProducerConsumerTestAppl {

	private static final int N_RECIEVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MyBlokingQueue<String> blkQueue = new MyBlokingQueue<>();
		for (int i = 0; i < N_RECIEVERS; i++) {
			new MessageConsumer(blkQueue).start();
		}
		new MessageProducer(blkQueue).start();
		Thread.sleep(100);

	}

}
