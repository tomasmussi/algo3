package algo3.modelo.juego;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import algo3.controlador.Logger;
import algo3.controlador.XMLParser;
import algo3.modelo.caso.Caso;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.tiempo.Reloj;
import algo3.vista.Vista;
import algo3.vista.VistaPolicia;
import algo3.vista.VistaReloj;

public class Juego implements Observer {

	private Vista vistaReloj;
	private Policia policia;
	private Caso caso;
	private boolean casoAsignado;
	private Reloj reloj;

	public Juego(String nombrePolicia, JComponent componenteReloj, JComponent componenteCiudadActual) {
		this.policia = new Policia(nombrePolicia);
		policia.addObserver(this);
		inicializarReloj(componenteReloj);
		inicializarCiudadActual(componenteCiudadActual);
		policia.setReloj(reloj);
		iniciarCaso();
	}

	private void iniciarCaso() {
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

	private void inicializarReloj(JComponent componenteReloj) {
		reloj = new Reloj();
		vistaReloj = new VistaReloj(componenteReloj);
		reloj.setVista(vistaReloj);
		reloj.addObserver(this);
		reloj.notificar();
	}

	private void inicializarCiudadActual(JComponent componenteReloj) {
		vistaReloj = new VistaPolicia(componenteReloj);
		policia.setVista(vistaReloj);
		policia.addObserver(this);
	}

	private void finalizarCaso() {
		casoAsignado = false;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!reloj.hayTiempoRestante()) {
			finalizarCaso();
		}
		if (arg != null) {
			// Fue un update proveniente de Policia
			Boolean atrapado = (Boolean) arg;
			if (atrapado) {
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
		// inicializarReloj();
		return policia;
	}

	public void transcurrirReloj() {
		reloj.transcurrir(3);
	}

	public String[] getCiudadesPosibles() {
		List<Ciudad> ciudadesPosibles =  caso.getMapa().getCiudadesPosibles(policia.getCiudadActual());
		String[] ciudades = new String[4];
		int i = 0;
		for (Ciudad ciudad : ciudadesPosibles) {
			ciudades[i++] = ciudad.getNombre();
		}
		return ciudades;
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

}
