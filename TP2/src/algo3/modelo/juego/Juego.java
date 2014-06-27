package algo3.modelo.juego;

import algo3.vista.FramePrincipal;

public class Juego {

	public void iniciar() {
		iniciarModelos();
		crearVentanas();
	}

	private void iniciarModelos() {

	}

	private void crearVentanas() {
		FramePrincipal ventana = new FramePrincipal();
		ventana.setVisible(true);
		ventana.setExtendedState(FramePrincipal.MAXIMIZED_BOTH);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(FramePrincipal.EXIT_ON_CLOSE);
	}
}
