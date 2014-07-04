package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo3.modelo.juego.Juego;
import algo3.vista.FrameJuego;

public class ControladorBuscarEdificos implements ActionListener {

	private Juego juego;
	private FrameJuego frame;

	public ControladorBuscarEdificos(FrameJuego frameJuego, Juego juego) {
		this.juego = juego;
		this.frame = frameJuego;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.mostrarPanelEdificios(juego.getEdificios());
	}
}
