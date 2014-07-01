package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import algo3.modelo.juego.Juego;
import algo3.vista.FrameJuego;

public class ControladorPosiblesDestinos implements ActionListener {

	private FrameJuego frame;
	private Juego juego;

	public ControladorPosiblesDestinos(FrameJuego frameJuego, Juego juego) {
		this.frame = frameJuego;
		this.juego = juego;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.mostrarGoogleMaps();
		frame.refrescarMarcadores();
		String[] ciudadesPosibles = juego.getCiudadesPosibles();
		JOptionPane.showMessageDialog(null, ciudadesPosibles, "Ciudades posibles", JOptionPane.INFORMATION_MESSAGE);
	}

}
