package telran.util.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.*;;

public class MyBlokingQueue<T> {

	private Queue<T> queue = new LinkedList<>();
	private int max;
	private Lock mutex = new ReentrantLock();
	private Condition producerWaitingCondition = mutex.newCondition();
	private Condition consumerWaitingCondition = mutex.newCondition();

	public MyBlokingQueue(int capacity) {
		max = capacity;
	}

	public MyBlokingQueue() {
		this(Integer.MAX_VALUE);
	}

	public void offer(T obj) {
		mutex.lock();
		try {
			while (queue.size() == max) {
				try {
					producerWaitingCondition.await();
				} catch (InterruptedException e) {

					System.out.println("interrupted on producer waiting");
				}
			}
			queue.offer(obj);
			consumerWaitingCondition.signal();
		} finally {
			mutex.unlock();
		}
	}
	
	public T poll() {
		mutex.lock();
		try {
			while(queue.isEmpty()) {
				try {
					consumerWaitingCondition.await();
				} catch (InterruptedException e) {
					System.out.println("interrupted on consumer waiting");
				}
			}
			T res = queue.poll();
			producerWaitingCondition.signal();
			return res;
		} finally {
			mutex.unlock();
		}
		
	}

}
