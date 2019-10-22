package filosofos;

public class Mesa {

	public static void main(String[] args) throws Exception {

		Filosofo[] filosofos = new Filosofo[5];
		Object[] tenedores = new Object[5];

		for (int i = 0; i < tenedores.length; i++) {
			tenedores[i] = new Object();
		}

		for (int i = 0; i < filosofos.length; i++) {
			Object tenedorIzquierdo = tenedores[i];
			Object tenedorDerecho = tenedores[(i + 1) % tenedores.length];

			filosofos[i] = new Filosofo(tenedorIzquierdo, tenedorDerecho);

			Thread filosofo = new Thread(filosofos[i], "Filosofo " + (i + 1));
			filosofo.start();
		}
	}
}