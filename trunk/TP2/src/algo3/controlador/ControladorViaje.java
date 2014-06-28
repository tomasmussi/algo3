package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import algo3.modelo.juego.Juego;
import algo3.vista.FrameDeViaje;


public class ControladorViaje implements ActionListener {

	private Juego juego;
	private JComboBox<String> combo;
	private FrameDeViaje viaje;

	public ControladorViaje(Juego juego, JComboBox<String> combo, FrameDeViaje viaje){
		this.juego = juego;
		this.combo = combo;
		this.viaje = viaje;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		juego.viajar((String) combo.getSelectedItem());
		viaje.dispose();
	}

}
