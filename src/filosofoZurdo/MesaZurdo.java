package filosofoZurdo;

public class MesaZurdo {

	public static void main(String[] args) throws Exception {

		FilosofoZurdo[] filosofos = new FilosofoZurdo[5];
		Object[] tenedores = new Object[5];

		for (int i = 0; i < tenedores.length; i++) {
			tenedores[i] = new Object();
		}

		for (int i = 0; i < filosofos.length; i++) {
			Object tenedorIzquierdo = tenedores[i];
			Object tenedorDerecho = tenedores[(i + 1) % tenedores.length];

			if (i == filosofos.length - 1) {

				// El último de los filosofos levanta primero el tenedor izquierdo
				filosofos[i] = new FilosofoZurdo(tenedorIzquierdo, tenedorDerecho);
			} else {
				filosofos[i] = new FilosofoZurdo(tenedorDerecho, tenedorIzquierdo);
			}

			Thread filosofo = new Thread(filosofos[i], "Filosofo " + (i + 1));
			filosofo.start();
		}
	}
}