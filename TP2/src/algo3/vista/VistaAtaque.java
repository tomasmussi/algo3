package algo3.vista;

import java.util.Observable;

import javax.swing.JOptionPane;

/**
 * Mostrado cuando se origina un ataque al jugador
 * */
public class VistaAtaque implements Vista {

	@Override
	public void update(Observable o, Object arg) {
		JOptionPane.showMessageDialog(null, arg, "", JOptionPane.WARNING_MESSAGE);
	}

}
