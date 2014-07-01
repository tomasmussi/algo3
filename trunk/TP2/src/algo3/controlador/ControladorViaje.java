package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import algo3.modelo.juego.Juego;
import algo3.vista.FrameDeViaje;
import algo3.vista.FrameJuego;


public class ControladorViaje implements ActionListener {

	private Juego juego;
	private JComboBox<String> combo;
	private FrameDeViaje viaje;
	private FrameJuego frameJuego;

	public ControladorViaje(Juego juego, JComboBox<String> combo, FrameDeViaje viaje, FrameJuego frameJuego){
		this.juego = juego;
		this.combo = combo;
		this.viaje = viaje;
		this.frameJuego = frameJuego;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		juego.viajar((String) combo.getSelectedItem());
		viaje.dispose();
		frameJuego.refrescarMarcadores();
		frameJuego.setearImagenCiudad(juego.getCiudadActual());
	}

}
