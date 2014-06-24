package algo3.vista;

import java.util.Observable;
import java.util.Observer;

import algo3.modelo.tiempo.Reloj;

public class VistaConsolaReloj implements Vista, Observer {


	@Override
	public void update(Observable o, Object arg) {
		Reloj reloj = (Reloj) o;
		System.out.println(reloj.tiempoActual());
	}


}
