package algo3.modelo.ladron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.Mapa;
import algo3.modelo.objeto.Robable;

/**
 * Ladron, cuando roba un objeto, le pide al objeto la ciudad de origen y la posiciona como
 * Primera en la lista de informacion ciudad. El ladron crea su recorrido y saca de la
 * informacion disponible las ciudades usadas en el recorrido.
 * 
 * 
 * */
public class Ladron {

	private CaracteristicaLadron caracteristicas;
	private Robable objetoRobado;
	private Ciudad ciudadActual;
	private List<Ciudad> rutaEscape;
	private Iterator<Ciudad> iterador;

	/**
	 * 
	 * Construye un ladron a partir de caracteristicas
	 * */
	public Ladron(CaracteristicaLadron caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * Este metodo compara que un conjunto de caracteristicas lo describen a el/ella
	 * 
	 * @param unaCaracteristica
	 * @return Si sus caracteristicas coinciden, univocamente es el ladron, entonces devuelve true, devuelve false en caso contrario
	 */
	public boolean coincideCon(CaracteristicaLadron unaCaracteristica) {
		return caracteristicas.equals(unaCaracteristica);
	}

	public CaracteristicaLadron getCaracteristicasLadron() {
		return caracteristicas;
	}

	private void elegirEscapatoria(List<Ciudad> ciudadesDelMundo) {

		int cantidadCiudades = this.objetoRobado.getCantidadDeCiudades();
		if (cantidadCiudades > ciudadesDelMundo.size()) {
			throw new IllegalArgumentException("No hay suficiente informacion de ciudades para generar: " + cantidadCiudades + " ciudades");
		}
		List<Ciudad> ciudades = new ArrayList<Ciudad>(ciudadesDelMundo);
		desordenarCiudades(ciudades);
		this.rutaEscape = new ArrayList<Ciudad>();
		this.rutaEscape.add(ciudadActual);
		// Para que no pueda volver a la ciudad inicial:
		ciudades.remove(ciudadActual);

		Iterator<Ciudad> ciudadIterator = ciudades.iterator();
		while (ciudadIterator.hasNext() && (this.rutaEscape.size() < cantidadCiudades)) {
			Ciudad nuevaCiudad = ciudadIterator.next();
			this.rutaEscape.add(nuevaCiudad);
			ciudadIterator.remove(); // NO esta mas disponible para utilizar
		}
		iterador = rutaEscape.iterator();
		this.moverAlSiguientePais();
	}

	private void desordenarCiudades(List<Ciudad> ciudades) {
		for (int i = 0; i < (ciudades.size() - 1); i++) {
			Ciudad ciudad = ciudades.get(i);
			int posicion = (int) ((Math.random() * ciudades.size()) % ciudades.size());
			Ciudad otra = ciudades.get(posicion);
			ciudades.set(i, otra);
			ciudades.set(posicion, ciudad);
		}
	}

	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	public void moverAlSiguientePais() {
		if (iterador.hasNext()) {
			ciudadActual = iterador.next();
		} else {
			refugiarseEnEdificio();
		}
	}

	private void refugiarseEnEdificio() {
		Edificio[] edificios = ciudadActual.getTodosLosEdificios();
		Random rand = new Random();
		int posicion = rand.nextInt(edificios.length - 1);
		// Solucion temporal para que podamos pasarle edificios como null en ViajeTest:
		if (edificios[posicion] != null) {
			this.entrar(edificios[posicion]);
		}
	}

	private void entrar(Edificio edificio) {
		edificio.refugiarLadron(this);
	}

	public List<Ciudad> getEscapatoria() {
		return this.rutaEscape;
	}

	/**
	 * Se llama este metodo cuando el ladron se roba un objeto al comienzo de un caso.
	 * El ladron se roba el objeto y en base al objeto elige un recorrido por el cual escaparse.
	 * Ese recorrido se actualiza cuando el policia se va acercando al ladron
	 */
	public void robar(Robable objetoRobado) {
		this.ciudadActual = Mapa.getInstance().getCiudadDeNombre(objetoRobado.getCiudadOrigen());
		this.objetoRobado = objetoRobado;
		elegirEscapatoria(Mapa.getInstance().getListadoCiudades());
	}

	public Robable getObjetoRobado() {
		return this.objetoRobado;
	}
}