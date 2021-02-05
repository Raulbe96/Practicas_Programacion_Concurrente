package p0203;

import java.util.concurrent.atomic.*;

public class LockTicket {
	private volatile AtomicInteger number;
	private volatile int next;
	private volatile int [] turn;
	
	public LockTicket(int n) {
		this.number = new AtomicInteger(1);
		this.next = 1;
		this.turn = new int[n*2];
		for(int i = 0; i < n*2; i++)
			turn[i] = 0;
	}
	
	
	public void takeLock(int id) {
		turn[id] = number.getAndIncrement();
		while(turn[id] != next);
	}

	public void releaseLock(int id) {
		next = next+1;
	}
}
