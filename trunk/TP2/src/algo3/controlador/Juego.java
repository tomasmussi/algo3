package algo3.controlador;

import java.util.Iterator;
import java.util.List;

import algo3.modelo.caso.Caso;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.tiempo.Reloj;
import algo3.vista.Vista;


public class Juego {

	private Policia policia;
	private Vista vistaReloj;
	private Caso caso;


	public Juego(Policia policia){
		this.policia = policia;
	}

	public Juego(Policia policia, Vista vista){
		this.policia = policia;
		this.vistaReloj = vista;
	}

	public void iniciar(){

		Reloj reloj = new Reloj();
		reloj.setVista(vistaReloj);
		policia.setReloj(reloj);
		generarCaso();
		policia.asignarCaso(caso);

	}

	private void generarCaso() {

		List<CaracteristicaLadron> ladrones = XMLParser.cargarExpedientes();
		List<CaracteristicaObjeto> objetos = XMLParser.cargarObjetos();
		Grado grado = policia.getGrado();
		try {
			caso = new Caso(ladrones, objetos, grado);
		} catch (CiudadNoEncontradaException e) {
			Logger.loguearError(e);
		}
	}

	public String ciudadActual() {
		return policia.getCiudadActual().toString();
	}

	public String buscarPista(String edificioNumero) {
		int numero = Integer.valueOf(edificioNumero);
		return policia.visitarEdificioYObtenerPista(policia.getCiudadActual().getTodosLosEdificios()[numero]);
	}

	public String getCiudadesPosibles() {
		StringBuilder sb = new StringBuilder();
		Iterator<Ciudad> iter = caso.getMapa().getCiudadesPosibles(policia.getCiudadActual()).iterator();
		while (iter.hasNext()){
			Ciudad ciudad = iter.next();
			sb.append(ciudad.toString());
			sb.append("\t");
		}
		return sb.toString();
	}

	public void viajar(String ciudad) {
		Iterator<Ciudad> ciudades = caso.getMapa().getCiudadesPosibles(policia.getCiudadActual()).iterator();
		boolean encontrado = false;
		Ciudad ciudadPosible = null;
		while (ciudades.hasNext() && !encontrado){
			ciudadPosible = ciudades.next();
			if (ciudadPosible.getNombre().equals(ciudad)){
				encontrado = true;
			}
		}
		if (encontrado) {
			policia.viajarA(ciudadPosible);
		}
	}

	public void setVistaViaje(Vista vistaViaje) {
		policia.setVista(vistaViaje);
	}

	public boolean emitirOrdenDeArresto(String[] caracteristicas) {
		CaracteristicaLadron carac = new CaracteristicaLadron(null, caracteristicas[0],
				caracteristicas[1], caracteristicas[2], caracteristicas[3], caracteristicas[4]);
		return policia.emitirOrdenDeArresto(carac);
	}

}
