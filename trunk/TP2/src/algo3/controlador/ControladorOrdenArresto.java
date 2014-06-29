package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import algo3.modelo.juego.Juego;
import algo3.vista.FrameExpedientes;

public class ControladorOrdenArresto implements ActionListener {

	private FrameExpedientes frame;
	private Juego juego;

	public ControladorOrdenArresto(Juego juego, FrameExpedientes frame) {
		this.juego = juego;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<String> caracteristicas = frame.getListaCaracteristicas();
		frame.mostrarOrdenEmitida(juego.emitirOrdenDeArresto(caracteristicas));
		frame.dispose();
	}
}
