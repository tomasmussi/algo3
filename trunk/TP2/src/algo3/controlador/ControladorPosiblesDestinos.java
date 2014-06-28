package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import algo3.modelo.juego.Juego;

public class ControladorPosiblesDestinos implements ActionListener {

	private Juego juego;

	public ControladorPosiblesDestinos(Juego juego){
		this.juego = juego;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] ciudadesPosibles = juego.getCiudadesPosibles();
		JOptionPane.showMessageDialog(null, ciudadesPosibles, "Ciudades posibles", JOptionPane.INFORMATION_MESSAGE);
	}

}
