package algo3.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.juego.Juego;

public class ControladorEntrarEdificio implements MouseListener {

	private Juego juego;
	private int edificio;

	public ControladorEntrarEdificio(Juego juego, int edificio) {
		this.juego = juego;
		this.edificio = edificio;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		String pista = juego.buscarPista(edificio);
		if (!Edificio.LADRON_ENCONTRADO.equals(pista)) {
			JOptionPane.showMessageDialog(null, pista);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Nada.
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Nada.
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Nada.
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Nada.
	}

}
