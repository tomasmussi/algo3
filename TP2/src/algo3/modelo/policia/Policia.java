package algo3.modelo.policia;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import algo3.modelo.caso.Caso;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.policia.grado.GradoNovato;
import algo3.modelo.tiempo.Reloj;
import algo3.vista.Vista;

public class Policia extends Observable {

	private Reloj reloj;
	private int cantidadArrestos;
	private List<Edificio> edificiosVisitados;
	private Ciudad ciudadActual;
	private Grado grado;
	private Caso caso;
	private Vista vista;
	private String nombre;

	public Policia() {
		inicializarPolicia();
	}

	public Policia(String nombrePolicia) {
		this.nombre = nombrePolicia;
		inicializarPolicia();
	}

	private void inicializarPolicia() {
		cantidadArrestos = 0;
		edificiosVisitados = new ArrayList<Edificio>();
		grado = new GradoNovato();
		ciudadActual = null;
	}

	public void setReloj(Reloj reloj) {
		this.reloj = reloj;
	}

	public void asignarCaso(Caso esteCaso) {
		this.caso = esteCaso;
		addObserver(caso.getLadron());
		this.viajarA(esteCaso.getCiudadOrigenDeObjeto());

	}

	/**
	 * Viajar recibe la cantidad de kilometros a viajar por parte del policia
	 * y devuelve la cantidad de horas que le consume el viaje de acuerdo a la cantidad de kilometros
	 * que puede viajar
	 * 
	 * */
	public void viajar(int kilometros) {
		reloj.transcurrir(calcularKilometrosPorHora(kilometros));
	}

	public int calcularKilometrosPorHora(int kilometros) {
		// Calculo de cuantas horas tengo que restar
		int horas = kilometros / grado.getKilometrosPorHora();
		horas += (kilometros % grado.getKilometrosPorHora()) != 0 ? 1 : 0;
		return horas;
	}

	private void aumentarArrestos() {
		cantidadArrestos++;
		grado.evaluarGrado(this);
	}

	// TODO:METODO SOLO PARA PRUEBAS VER DE CORREGIRLAS Y BORRARLO
	public boolean puedeArrestar() {
		return reloj.hayTiempoRestante();
	}

	public Grado getGrado() {
		return grado;
	}

	public int getCantidadArrestos() {
		return cantidadArrestos;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setGrado(Grado gradoSiguiente) {
		this.grado = gradoSiguiente;
	}

	/**
	 * Aumenta el numero de visitas a edificios.
	 * Agrega el edificio a lista de edificios visitados.
	 * 
	 * @return Horas a restar.
	 */
	private int aumentarVisitas(Edificio edificio) {
		int horasARestar = 0;
		if (!edificiosVisitados.contains(edificio)) {
			edificiosVisitados.add(edificio);
			horasARestar = edificiosVisitados.size();
		}
		return horasARestar;
	}

	// Se llama con un evento
	public String visitarEdificioYObtenerPista(Edificio edificio) {
		int horasARestar = aumentarVisitas(edificio);
		reloj.transcurrir(horasARestar);
		String pista = edificio.visitar(this);
		if (pista.equals(Edificio.LADRON_ENCONTRADO)) {
			setChanged();
			notifyObservers(arrestar(caso.getLadron()));
		}
		return pista;
	}

	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	// Se llama con un evento
	public void viajarA(Ciudad ciudad) {
		if (ciudadActual != null) {
			this.viajar(ciudadActual.getDistanciaCon(ciudad));
		}
		this.ciudadActual = ciudad;
		edificiosVisitados = new ArrayList<Edificio>();
		setChanged();
		notifyObservers();
	}

	/**
	 * Accion que el policia realiza cuando quiere arrestar un ladron
	 * Devuelve true, si y solo si, el policia tiene una orden de arresto para el ladron
	 * y misma coincide con el ladron que esta siendo perseguido.
	 * Devuelve false en caso contrario
	 * 
	 * @param ladron
	 * @return true si atrapo al ladron, false de lo contrario
	 */
	public boolean arrestar(Ladron ladron) {
		if ((caso.getOrdenDeArresto() == null) || !ladron.coincideCon(caso.getOrdenDeArresto().getCaracteristicaLadron()) || !reloj.hayTiempoRestante()) {
			return false;
		}
		aumentarArrestos();
		return true;
	}

	public boolean emitirOrdenDeArresto(CaracteristicaLadron caracteristica) {
		reloj.transcurrir(3);
		if ((caracteristica != null) && reloj.hayTiempoRestante()) {
			return caso.generarOrdenDeArresto(caracteristica);
		}
		// TODO Revisar...
		setChanged();
		notifyObservers();
		return false;
	}

	public void setVista(Vista vistaViaje) {
		this.vista = vistaViaje;
		addObserver(vista);
	}

	public void resetear() {
		if (caso != null) {
			deleteObserver(caso.getLadron());
		}
		ciudadActual = null;
		caso = null;
		edificiosVisitados = new ArrayList<Edificio>();
	}

	@Override
	public Object clone() {
		Policia policiaPersistir = new Policia();
		policiaPersistir.cantidadArrestos = this.cantidadArrestos;
		policiaPersistir.grado = this.grado;
		return policiaPersistir;
	}

}
