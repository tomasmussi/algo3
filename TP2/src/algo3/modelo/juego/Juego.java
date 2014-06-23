package algo3.modelo.juego;

import algo3.vista.VentanaDeJuego;

public class Juego {

	public void iniciar() {
		iniciarModelos();
		crearVentanas();
	}

	private void iniciarModelos() {

	}

	private void crearVentanas() {
		VentanaDeJuego ventana = new VentanaDeJuego();
		ventana.setVisible(true);
		ventana.setLocation(300, 300);
		ventana.setDefaultCloseOperation(VentanaDeJuego.EXIT_ON_CLOSE);
	}

}
