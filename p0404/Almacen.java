package p0404;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Almacen {
	private Producto[] prod;
	private int MAX;
	private int meter;
	private int sacar;
	private int nElems;

	// Para Lock & Condition
	final Lock lock = new ReentrantLock();;
	final Condition not_empty = lock.newCondition();;
	final Condition not_full = lock.newCondition();;

	public Almacen(int m) {
		this.MAX = m;
		this.prod = new Producto[MAX];
		this.meter = 0;
		this.sacar = 0;
		this.nElems = 0;
	}

	public void almacenar(Producto producto, int aMeter) {
		lock.lock();
		try {
			for (int i = 0; i < aMeter; i++) {
			while (nElems == MAX)
				not_full.await();
			System.out.println("vamos a meter el prod " + producto.get() + " en pos " + meter);
			prod[meter] = producto;
			meter = (meter + 1) % MAX;
			nElems++;
			not_empty.signal();

		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public Producto[] extraer(int aSacar) {

		lock.lock();
		Producto sacado[] = new Producto[aSacar];
		try {
			for (int i = 0; i < aSacar; i++) {
				while (nElems == 0)
					not_empty.await();
				// sacado[i] = prod[sacar];
				prod[sacar] = null;

				// System.out.println("vamos a sacar el prod " + sacado[i].get() + " en pos " +
				// sacar);
				System.out.println("vamos a sacar el prod " + " en pos " + sacar);

				sacar = (sacar + 1) % MAX;
				nElems--;
				not_full.signal();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return sacado;

	}
}
