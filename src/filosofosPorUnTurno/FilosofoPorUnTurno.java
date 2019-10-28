package filosofosPorUnTurno;

public class FilosofoPorUnTurno implements Runnable {

	private Object tenedorIzquierdo;
	private Object tenedorDerecho;
	private int identificador;

	public FilosofoPorUnTurno(Object tenedorIzquierdo, Object tenedorDerecho, int identificador) {
		this.tenedorIzquierdo = tenedorIzquierdo;
		this.tenedorDerecho = tenedorDerecho;
		this.identificador = identificador;
	}

	private void pensar() throws InterruptedException {
		Thread.sleep(((int) (Math.random() * 200)));
	}

	private void comer() throws InterruptedException {
		while (MesaPorUnTurno.instance().turno == identificador) {
			Thread.sleep(((int) (1)));
		}
	}

	@Override
	public void run() {
		try {
			while (true) {

				// Pensando
				this.pensar();

				while (MesaPorUnTurno.instance().turno == identificador) {
					System.out.println("Turno de: " + Thread.currentThread().getName());

					// Espera por el tenedor derecho
					synchronized (tenedorDerecho) {

						System.out.println(Thread.currentThread().getName() + ": Levantó tenedor derecho");

						// Espera por el tenedor izquierdo
						synchronized (tenedorIzquierdo) {

							// Comiendo
							System.out.println(Thread.currentThread().getName()
									+ ": Levantó el tenedor izquierdo y empezó a comer");
							comer();
						}

					}

					System.out.println(Thread.currentThread().getName() + ": Dejó el tenedor izquierdo");

					// Terminando de comer
					System.out.println(Thread.currentThread().getName() + ": Dejó el cuchillo derecho y empezó a pensar \n");
				}

			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}

}