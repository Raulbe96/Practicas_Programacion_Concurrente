package servidor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorRW {
	private int nr;
	private int nw;

	private Lock lock;
	private Condition oktoread;
	private Condition oktowrite;

	public MonitorRW() {
		nr = 0;
		nw = 0;
		lock = new ReentrantLock();
		oktoread = lock.newCondition();
		oktowrite = lock.newCondition();
		
	}

	public void requestRead() {
		lock.lock();
		while (nw > 0) {
			try {
				oktoread.await();
				nr++;
			} catch (InterruptedException e) {
			}
		}
		lock.unlock();
	}

	public void releaseRead() {
		lock.lock();
		nr--;
		if (nr == 0) {
			oktowrite.signal();
		}
		lock.unlock();
	}

	public void requestWrite() {
		lock.lock();
		try {
			while (nr > 0 || nw > 0) {
				oktowrite.await();
			}
		} catch (InterruptedException e) {}
		nw = nw+1;
		lock.unlock();
	}
	public void releaseWrite() {
		lock.lock();
		nw--;
		oktowrite.signal();
		oktoread.signalAll();
		lock.unlock();
	}
}
