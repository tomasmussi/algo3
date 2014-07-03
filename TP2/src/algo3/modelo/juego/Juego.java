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

	/**
	 * Crea el juego.
	 * @param policia: el jugador y su configuracion de dificultad
	 * @param componenteReloj la vista en la que se actualizara el estado del reloj
	 * @param componenteCiudadActual la vista que se actualizara con la ciudad actual del jugador
	 * */
	public Juego(Policia policia, JComponent componenteReloj, JComponent componenteCiudadActual) {
		this.policia = policia;
		policia.addObserver(this);
		inicializarReloj(componenteReloj);
		inicializarCiudadActual(componenteCiudadActual);
		policia.setReloj(reloj);
		policia.setVistaAtaque(new VistaAtaque());
		iniciarCaso();
	}

	/**
	 * Deja al juego en un estado valido para comenzar un juego nuevo
	 * */
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

	/**
	 * Inicializa el reloj y lo deja listo para comenzar el primer caso
	 * */
	private void inicializarReloj(JComponent componenteReloj) {
		reloj = new Reloj();
		vistaReloj = new VistaReloj(componenteReloj);
		reloj.setVista(vistaReloj);
		reloj.notificar();
	}

	/**
	 * Inicializa la vista de ciudad
	 * */
	private void inicializarCiudadActual(JComponent componenteCiudadActual) {
		vistaCiudad = new VistaPolicia(componenteCiudadActual);
		policia.setVista(vistaCiudad);
		policia.addObserver(this);
	}

	/**
	 * Cierra el caso y notifica a la vista para que tome las acciones correspondientes
	 * */
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

	/**
	 * Guarda el estado del jugador hasta el momento
	 * */
	public boolean guardar(String path) {
		return XMLParser.guardarPolicia(policia, path);
	}


	/**
	 * Muestra las ciudades posibles a viajar a partir de la ciudad actual del jugador
	 * @return un array de strings con las posibilidades
	 * */
	public String[] getCiudadesPosibles() {
		List<Ciudad> ciudadesPosibles =  caso.getMapa().getCiudadesPosibles(policia.getCiudadActual());
		String[] ciudades = new String[ciudadesPosibles.size()];
		int i = 0;
		for (Ciudad ciudad : ciudadesPosibles) {
			ciudades[i++] = ciudad.getNombre();
		}
		return ciudades;
	}

	/**
	 * Viaja a una determinada ciudad
	 * @throws CiudadNoEncontradaException si la ciudad pasada por parametro no la genero el mapa y por lo tanto es desconocida
	 * */
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

	/**
	 * Emite una orden de arresto dadas ciertas caracteristicas
	 * @return array de strings correspondientes a los ladrones que coinciden.
	 * Si devuelve un solo nombre, se emitio la orden
	 * Si devuelve 0 o mas de 1, informa que por exceso o por defecto de caracteristicas
	 * no se pudo emitir la orden
	 * */
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

	/**
	 * Informa los nombres de los edificios disponibles en la ciudad actual
	 * */
	public String[] getEdificios() {
		Edificio[] edificios = policia.getCiudadActual().getTodosLosEdificios();
		String[] nombres = new String[edificios.length];
		for (int i = 0; i < edificios.length; i++){
			nombres[i] = edificios[i].getNombre();
		}
		return nombres;
	}

	/**
	 * Ingresa al edificio para obtener una pista
	 * @param edificio indice de los 3 posibles edificios a ingresar
	 * @return pista devuelve la pista que proporciona el edificio
	 * */
	public String buscarPista(int edificio) {
		return policia.visitarEdificioYObtenerPista(policia.getCiudadActual().getTodosLosEdificios()[edificio]);
	}

	/**
	 * Devuelve el sexo del ladron para informarlo al principio del caso
	 * */
	public String getSexoLadron() {
		return caso.getLadron().getSexo();
	}

	/**
	 * Devuelve el nombre de la ciudad de origen del objeto robado del caso
	 * */
	public String getCiudadOrigen() {
		return caso.getCiudadOrigenDeObjeto().getNombre();
	}

	/**
	 * Devuelve el nombre del objeto robado
	 * */
	public String getNombreObjeto() {
		return caso.getLadron().getObjetoRobado().getNombre();
	}

	/**
	 * Devuelve el nombre de la ciudad actual que esta el jugador
	 * */
	public String getCiudadActual() {
		return policia.getCiudadActual().getNombre();
	}
}
