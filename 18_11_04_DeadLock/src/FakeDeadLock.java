
public class FakeDeadLock {
	
	private static final Object mutex1 = new Object();
	private static final Object mutex2 = new Object();
	
	
	
	public static void main(String[] args) throws InterruptedException {
//		Thread.currentThread().join();
		Thread thread1 = new Thread() {
		
			@Override
			public void run() {
				synchronized (mutex1 ) {
				System.out.println("thread1 blocked mutex1");
					try {
						sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					synchronized (mutex2) {
						System.out.println("thread1 get access mutex2");
						try {
							sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					
				}
			}
			
		};
		Thread thread2 = new Thread() {

			@Override
			public void run() {
				synchronized (mutex2 ) {
					System.out.println("thread2 blocked mutex2");
					try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					synchronized (mutex1) {
						System.out.println("thread2 get access mutex1");
						try {
							sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			}
		};
		thread1.start();
		thread2.start();
	}
}
