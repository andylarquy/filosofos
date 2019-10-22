package filosofos;

public class Filosofo implements Runnable {

	// The forks on either side of this Philosopher
	private Object tenedorIzquierdo;
	private Object tenedorDerecho;

	public Filosofo(Object tenedorIzquierdo, Object tenedorDerecho) {
		this.tenedorIzquierdo = tenedorIzquierdo;
		this.tenedorDerecho = tenedorDerecho;
	}

	private void simularAccion() throws InterruptedException {
		Thread.sleep(((int) (Math.random() * 200)));
	}

	@Override
	public void run() {
		try {
			while (true) {

				// Pensando
				System.out.println(Thread.currentThread().getName() + ": Pensando");
				this.simularAccion();

				// Espera por el tenedor izquierdo
				synchronized (tenedorIzquierdo) {
					System.out.println(Thread.currentThread().getName() + ": Levantó tenedor izquierdo");
					simularAccion();

					// Espera por el tenedor derecho
					synchronized (tenedorDerecho) {
						// Comiendo
						System.out.println(Thread.currentThread().getName() + ": Levantó el tenedor derecho y empezó a comer");
						simularAccion();

						System.out.println(Thread.currentThread().getName() + ": Dejó el tenedor derecho");
					}

					// terminando de comer
					System.out.println(Thread.currentThread().getName() +": Dejó el cuchillo izquierdo y empezó a pensar");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}

}