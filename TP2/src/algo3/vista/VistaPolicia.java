package algo3.vista;

import java.util.Observable;

import javax.swing.JComponent;
import javax.swing.JLabel;

import algo3.modelo.policia.Policia;

public class VistaPolicia implements Vista {


	private JLabel lblCiudad;

	public VistaPolicia(JComponent lblCiudad) {
		this.lblCiudad = (JLabel) lblCiudad;
	}

	@Override
	public void update(Observable o, Object arg) {
		Policia policia = (Policia) o;
		lblCiudad.setText(policia.getCiudadActual().toString());
	}
}
