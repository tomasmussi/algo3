package algo3.vista;

import java.util.Observable;

import algo3.modelo.policia.Policia;

public class VistaViaje implements Vista {

	@Override
	public void update(Observable o, Object arg) {
		Policia policia = (Policia) o;
		System.out.println(policia.getCiudadActual());
	}

}
