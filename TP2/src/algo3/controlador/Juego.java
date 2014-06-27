package algo3.controlador;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import algo3.modelo.caso.Caso;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.tiempo.Reloj;
import algo3.vista.Vista;


public class Juego implements Observer {

	private Policia policia;
	private Caso caso;
	private boolean casoAsignado;
	private Reloj reloj;

	private Vista vistaReloj;
	private static final String CASO_NO_INICIADO = "Hay que iniciar un caso";


	public Juego(Policia policia){
		this.policia = policia;
		casoAsignado = false;
	}

	public Juego(Policia policia, Vista vista){
		this.policia = policia;
		this.vistaReloj = vista;
		this.reloj = new Reloj();
		inicializarReloj();
		casoAsignado = false;
	}

	private void inicializarReloj(){
		policia.setReloj(reloj);
		reloj.setVista(vistaReloj);
		policia.addObserver(this);
		reloj.addObserver(this);
	}

	public void iniciar(){
		reloj.resetear();
		policia.resetear();
		generarCaso();
		policia.asignarCaso(caso);
		casoAsignado = true;
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
		if (!casoAsignado){
			return CASO_NO_INICIADO;
		}
		return policia.getCiudadActual().toString();
	}

	public String buscarPista(String edificioNumero) {
		if (!casoAsignado){
			return CASO_NO_INICIADO;
		}
		int numero = Integer.valueOf(edificioNumero);
		return policia.visitarEdificioYObtenerPista(policia.getCiudadActual().getTodosLosEdificios()[numero]);
	}

	public String getCiudadesPosibles() {
		if(!casoAsignado){
			return CASO_NO_INICIADO;
		}
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
		if (!casoAsignado){
			return;
		}
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
		if (!casoAsignado){
			return false;
		}
		CaracteristicaLadron carac = new CaracteristicaLadron(null, caracteristicas[0],
				caracteristicas[1], caracteristicas[2], caracteristicas[3], caracteristicas[4]);
		return policia.emitirOrdenDeArresto(carac);
	}

	private void finalizarCaso(){
		casoAsignado = false;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!reloj.hayTiempoRestante()){
			finalizarCaso();
		}
		if (arg != null){
			// Fue un update proveniente de Policia
			Boolean atrapado = (Boolean) arg;
			if (atrapado){
				System.out.println("ATRAPADO");
			} else {
				System.out.println("SE ESCAPO");
			}
			finalizarCaso();
		}
	}

	public boolean guardar() {
		return XMLParser.guardarPolicia(policia);
	}

	public Policia cargar() {
		policia = XMLParser.cargarPolicia();
		finalizarCaso();
		inicializarReloj();
		return policia;
	}

}
