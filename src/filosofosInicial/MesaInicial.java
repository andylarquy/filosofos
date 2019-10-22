package filosofosInicial;

public class MesaInicial {

	public static void main(String[] args) throws Exception {

		FilosofoInicial[] filosofos = new FilosofoInicial[5];
		Object[] tenedores = new Object[5];

		for (int i = 0; i < tenedores.length; i++) {
			tenedores[i] = new Object();
		}

		for (int i = 0; i < filosofos.length; i++) {
			Object tenedorIzquierdo = tenedores[i];
			Object tenedorDerecho = tenedores[(i + 1) % tenedores.length];

			filosofos[i] = new FilosofoInicial(tenedorDerecho, tenedorIzquierdo);

			Thread filosofo = new Thread(filosofos[i], "Filosofo " + (i + 1));
			filosofo.start();
		}
	}
}