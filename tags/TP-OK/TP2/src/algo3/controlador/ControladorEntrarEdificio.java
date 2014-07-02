package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.juego.Juego;
import algo3.vista.FrameDeEdificios;

public class ControladorEntrarEdificio implements ActionListener {

	private Juego juego;
	private FrameDeEdificios frame;

	public ControladorEntrarEdificio(Juego juego, FrameDeEdificios frameDeEdificios) {
		this.juego = juego;
		this.frame = frameDeEdificios;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int edificio = frame.getEdificioSeleccionado();
		String pista = juego.buscarPista(edificio);
		if (!Edificio.LADRON_ENCONTRADO.equals(pista)){
			JOptionPane.showMessageDialog(null, pista);
			frame.setVisible(true);
		} else {
			frame.dispose();
		}
	}

}
