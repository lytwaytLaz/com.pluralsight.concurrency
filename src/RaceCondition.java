import model.LongWrapper;
import java.util.concurrent.atomic.AtomicInteger;

class RaceCondition {

	public static void main(String[] args) throws InterruptedException {

		LongWrapper longWrapper = new LongWrapper(0L);

		Runnable r = () -> {
			
			for (int i = 0 ; i < 1000 ; i++) {
				longWrapper.incrementValue();
			}
		};
		
		Thread[] threads = new Thread[1000];
		for (int i = 0 ; i < threads.length ; i++) {
			threads[i] = new Thread(r);
			threads[i].start();
		}

		//race condition is created by the incrementValue method in
		//the LongWrapper class
		//to prevent that a key object must be created in that class
		//or a Atomic variable must be used, see RaceConditionAtomic class
		for (int i = 0 ; i < threads.length ; i++) {
			threads[i].join();
		}
		
		System.out.println("Value = " + longWrapper.getValue());
	}
}
