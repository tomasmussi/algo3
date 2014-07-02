package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo3.modelo.juego.Juego;
import algo3.vista.FrameJuego;

public class ControladorMoverCiudades implements ActionListener {

	private FrameJuego frame;
	private Juego juego;

	public ControladorMoverCiudades(FrameJuego frameJuego, Juego juego) {
		this.frame = frameJuego;
		this.juego = juego;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.mostrarGoogleMaps();
		frame.mostarFrameDeViaje("Viajar a:", "Viajar",juego.getCiudadesPosibles());

	}
}
