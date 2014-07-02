package algo3.modelo.juego;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import algo3.controlador.Logger;
import algo3.controlador.XMLParser;
import algo3.modelo.caso.Caso;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.tiempo.Reloj;
import algo3.vista.Vista;
import algo3.vista.VistaAtaque;
import algo3.vista.VistaPolicia;
import algo3.vista.VistaReloj;

public class Juego extends Observable implements Observer {

	private Vista vistaReloj;
	private Vista vistaCiudad;
	private Policia policia;
	private Caso caso;
	private Reloj reloj;

	public Juego(Policia policia, JComponent componenteReloj, JComponent componenteCiudadActual) {
		this.policia = policia;
		policia.addObserver(this);
		inicializarReloj(componenteReloj);
		inicializarCiudadActual(componenteCiudadActual);
		policia.setReloj(reloj);
		policia.setVistaAtaque(new VistaAtaque());
		iniciarCaso();
	}

	public void resetear() {
		reloj.resetear();
		reloj.notificar();
		policia.resetear();
		iniciarCaso();
	}

	private void iniciarCaso() {
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

	private void inicializarReloj(JComponent componenteReloj) {
		reloj = new Reloj();
		vistaReloj = new VistaReloj(componenteReloj);
		reloj.setVista(vistaReloj);
		reloj.notificar();
	}

	private void inicializarCiudadActual(JComponent componenteCiudadActual) {
		vistaCiudad = new VistaPolicia(componenteCiudadActual);
		policia.setVista(vistaCiudad);
		policia.addObserver(this);
	}

	private void finalizarCaso(String estado) {
		setChanged();
		notifyObservers(estado);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!reloj.hayTiempoRestante()) {
			finalizarCaso("TIME OUT");
		}
		if (arg != null) {
			// Fue un update proveniente de Policia
			Boolean atrapado = (Boolean) arg;
			finalizarCaso(atrapado ? "Ladron Atrapado" : "No se pudo atrapar ladron");
		}
	}

	public boolean guardar(String path) {
		return XMLParser.guardarPolicia(policia, path);
	}


	public String[] getCiudadesPosibles() {
		List<Ciudad> ciudadesPosibles =  caso.getMapa().getCiudadesPosibles(policia.getCiudadActual());
		String[] ciudades = new String[ciudadesPosibles.size()];
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

	public String[] emitirOrdenDeArresto(List<String> caracteristicas) {
		CaracteristicaLadron carac = new CaracteristicaLadron(null, caracteristicas.get(0),
				caracteristicas.get(1), caracteristicas.get(2), caracteristicas.get(3), caracteristicas.get(4));
		List<CaracteristicaLadron> expedientes = policia.emitirOrdenDeArresto(carac);
		String[] nombres = new String[expedientes.size()];
		for (int i = 0; i < expedientes.size(); i++){
			nombres[i] = expedientes.get(i).getNombre();
		}
		return nombres;
	}

	public String[] getEdificios() {
		Edificio[] edificios = policia.getCiudadActual().getTodosLosEdificios();
		String[] nombres = new String[edificios.length];
		for (int i = 0; i < edificios.length; i++){
			nombres[i] = edificios[i].getNombre();
		}
		return nombres;
	}

	public String buscarPista(int edificio) {
		return policia.visitarEdificioYObtenerPista(policia.getCiudadActual().getTodosLosEdificios()[edificio]);
	}

	public String getSexoLadron() {
		return caso.getLadron().getSexo();
	}

	public String getCiudadOrigen() {
		return caso.getCiudadOrigenDeObjeto().getNombre();
	}

	public String getNombreObjeto() {
		return caso.getLadron().getObjetoRobado().getNombre();
	}

	public String getCiudadActual() {
		return policia.getCiudadActual().getNombre();
	}
}
