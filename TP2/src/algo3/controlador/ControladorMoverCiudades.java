package algo3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo3.modelo.juego.Juego;
import algo3.vista.FrameJuego;

public class ControladorMoverCiudades implements ActionListener {

	private FrameJuego frame;
	private Juego juego;

	public ControladorMoverCiudades(FrameJuego frameJuego, Juego juego) {
		this.frame = frameJuego;
		this.juego = juego;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// situar al policia en la ciudad elegida.
		// obtener la ciudad a la que se viajo, obetener el nombre y elegir la foto a mostrar.
		// Toolkit.getDefaultToolkit().getImage(ciudad.getNombre()); ???
		// Ver como interactuar con google maps y las coordenadas, esto por ahora es solo de adorno,
		// todavia no tiene funcionalidad, es solo para hacerlo "cheto".
		frame.mostrarGoogleMaps();
		frame.mostarFrameDeViaje("Viajar a:", "Viajar",juego.getCiudadesPosibles());
	}
}
