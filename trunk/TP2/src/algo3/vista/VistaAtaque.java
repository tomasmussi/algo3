package algo3.vista;

import java.util.Observable;

import javax.swing.JOptionPane;

public class VistaAtaque implements Vista {

	@Override
	public void update(Observable o, Object arg) {
		JOptionPane.showMessageDialog(null, arg, "", JOptionPane.WARNING_MESSAGE);
	}

}
