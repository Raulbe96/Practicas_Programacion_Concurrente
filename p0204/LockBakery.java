package p0204;

public class LockBakery {
	
	private volatile int turn[];
	private int turns;
	
	public LockBakery(int n) {
		this.turns = n*2;
		this.turn = new int[n * 2];
		for(int i = 0; i < n; i++)
			this.turn[i] = 0;		
	}
	
	public void takeLock(int id) {
		this.turn[id] = max() + 1;
		for(int j  = 0; j < this.turns; j++) {
			if(j != id)
				while((this.turn[id] > this.turn[j]) && (this.turn[j] !=0));			
		}
	}

	public void releaseLock(int id) {
		this.turn[id] = 0;
	}
	
	private int max() {
		int max = this.turn[0];
		for(int i = 1; i < this.turn.length; i++) {
			if(this.turn[i] > max)
				max = this.turn[i];
		}
		return max;
	}
}
