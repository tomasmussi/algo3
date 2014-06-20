package algo3.modelo.ladron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.MapaMundi;
import algo3.modelo.objeto.Robable;


/**
 * Ladron, cuando roba un objeto, le pide al objeto la ciudad de origen y la posiciona como
 * Primera en la lista de informacion ciudad. El ladron crea su recorrido y saca de la
 * informacion disponible las ciudades usadas en el recorrido.

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

	private void elegirEscapatoria(List<Ciudad> ciudadesDelMundo){
		int cantidadCiudades = this.objetoRobado.getCantidadDeCiudades();
		if (cantidadCiudades > ciudadesDelMundo.size()){
			throw new IllegalArgumentException("No hay suficiente informacion de ciudades para generar: "
					+ cantidadCiudades + " ciudades");
		}
		List<Ciudad> ciudades = new ArrayList<Ciudad>(ciudadesDelMundo);
		desordenarCiudades(ciudades);
		this.rutaEscape = new ArrayList<Ciudad>();
		this.rutaEscape.add(ciudadActual);
		// Para que no pueda volver a la ciudad inicial:
		ciudades.remove(ciudadActual);

		Iterator<Ciudad> ciudadIterator = ciudades.iterator();
		while (ciudadIterator.hasNext() && this.rutaEscape.size() < cantidadCiudades){
			Ciudad nuevaCiudad = ciudadIterator.next();
			this.rutaEscape.add(nuevaCiudad);
			ciudadIterator.remove(); //NO esta mas disponible para utilizar
		}
		iterador = rutaEscape.iterator();
		this.moverAlSiguientePais();
	}

	private void desordenarCiudades(List<Ciudad> ciudades){
		for (int i = 0; i < ciudades.size() - 1; i++){
			Ciudad ciudad = ciudades.get(i);
			int posicion = (int) ((Math.random() * ciudades.size()) % ciudades.size());
			Ciudad otra = ciudades.get(posicion);
			ciudades.set(i, otra);
			ciudades.set(posicion, ciudad);
		}
	}

	public Ciudad getCiudadActual() {
		return this.ciudadActual;
	}

	public void moverAlSiguientePais() {
		if (iterador.hasNext()){
			ciudadActual = iterador.next();
		} else {
			this.refugiarseEnEdificio();
		}
	}

	//elije random en q edificio se mete
	private void refugiarseEnEdificio() {
		Edificio[] edificios = ciudadActual.getTodosLosEdificios();
		Random rand = new Random();
		int posicion = rand.nextInt(edificios.length -1);
		this.entrar(edificios[posicion]);
	}

	private void entrar(Edificio edificio) {
		edificio.refugiarLadron(this);
	}

	public List<Ciudad> getEscapatoria(){
		return this.rutaEscape;
	}

	public void robar(Robable objetoRobado) {
		this.ciudadActual = MapaMundi.getInstance().getCiudadDeNombre(objetoRobado.getCiudadOrigen());
		this.objetoRobado = objetoRobado;
		elegirEscapatoria(MapaMundi.getInstance().getListadoCiudades());
	}

	public Robable getObjetoRobado(){
		return this.objetoRobado;
	}
}