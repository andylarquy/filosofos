package filosofosInicial;

public class FilosofoInicial implements Runnable {

	// The forks on either side of this Philosopher
	private Object tenedorIzquierdo;
	private Object tenedorDerecho;

	public FilosofoInicial(Object tenedorIzquierdo, Object tenedorDerecho) {
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

				// Espera por el tenedor derecho
				synchronized (tenedorDerecho) {
					
					System.out.println(Thread.currentThread().getName() + ": Levantó tenedor derecho");
					simularAccion();

					// Espera por el tenedor derecho
					synchronized (tenedorIzquierdo) {

						// Comiendo
						System.out.println(Thread.currentThread().getName() + ": Levantó el tenedor izquierdo y empezó a comer");
						simularAccion();

						System.out.println(Thread.currentThread().getName() + ": Dejó el tenedor izquierdo");
					}

					// Terminando de comer
					System.out.println(Thread.currentThread().getName() + ": Dejó el cuchillo derecho y empezó a pensar");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}

}