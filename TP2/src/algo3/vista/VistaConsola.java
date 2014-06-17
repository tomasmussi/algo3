package algo3.vista;

import java.util.Observable;
import java.util.Observer;

import algo3.controlador.Juego;

public class VistaConsola implements Vista, Observer {

	private Juego juego;

	public VistaConsola(Juego juego){
		this.juego = juego;
	}


	public void inicializarVista(){

	}


	@Override
	public void update(Observable o, Object arg) {

	}



}
