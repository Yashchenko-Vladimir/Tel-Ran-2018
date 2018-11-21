package telran.messaging;

public class MessegeBox {
	
	private String message;
	private static final Object sender = new Object();
	private static final Object reciever = new Object();
	
	public void putMessage (String message) {
		synchronized (sender) {
			while (this.message != null) {
				try {
					sender.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.message = message;
			
		}
		
		synchronized (reciever) {
				reciever.notify();
		}
			
	}
	
	 public String getMessage() {
		String res = null;
		synchronized (reciever) {
			while (message == null) {
				try {
					reciever.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			res = message;
			message = null;

		}
		    synchronized (sender) {
				 sender.notify();
			}
		    
		    return res;
	}
}
