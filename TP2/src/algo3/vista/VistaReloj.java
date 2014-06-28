package algo3.vista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JLabel;

import algo3.modelo.tiempo.Reloj;

public class VistaReloj implements Vista, Observer {

	private JLabel lblReloj;

	public VistaReloj(JComponent componenteReloj) {
		this.lblReloj = (JLabel) componenteReloj;
	}

	@Override
	public void update(Observable o, Object arg) {
		Reloj reloj = (Reloj) o;
		lblReloj.setText(reloj.tiempoActual());
	}

}
