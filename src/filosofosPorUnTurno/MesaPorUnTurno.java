package filosofosPorUnTurno;

public class MesaPorUnTurno {

	private MesaPorUnTurno() {
	}

	private static MesaPorUnTurno mesaPorUnTurno;

	public static MesaPorUnTurno instance() {
		if (mesaPorUnTurno == null) {
			mesaPorUnTurno = new MesaPorUnTurno();
		}

		return mesaPorUnTurno;
	}
	
	int turno = 0;

	public static void main(String[] args) throws Exception {

		FilosofoPorUnTurno[] filosofos = new FilosofoPorUnTurno[5];
		Object[] tenedores = new Object[5];
		int tiempoParaComer = 150;

		for (int i = 0; i < tenedores.length; i++) {
			tenedores[i] = new Object();
		}

		for (int i = 0; i < filosofos.length; i++) {
			Object tenedorIzquierdo = tenedores[i];
			Object tenedorDerecho = tenedores[(i + 1) % tenedores.length];

			// El último de los filosofos levanta primero el tenedor izquierdo
			filosofos[i] = new FilosofoPorUnTurno(tenedorDerecho, tenedorIzquierdo, i);

			Thread filosofo = new Thread(filosofos[i], "Filosofo " + (i + 1));
			filosofo.start();
		}
		
		int i = 0;
		while(true) {
			MesaPorUnTurno.instance().turno = i % filosofos.length;
			Thread.sleep((int) (tiempoParaComer));
			i++;
		}

	}
}