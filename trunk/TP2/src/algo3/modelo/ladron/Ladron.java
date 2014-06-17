package algo3.modelo.ladron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.objeto.Objeto;
import algo3.modelo.objeto.Robable;


/**
 * Ladron, cuando roba un objeto, le pide al objeto la ciudad de origen y la posiciona como
 * Primera en la lista de informacion ciudad. El ladron crea el recorrido y saca de la
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
	 * Construye un ladron a partir de caracteristicas y de una lista de Ciudades posibles a recorrer
	 * 
	 * 
	 * */
	public Ladron(CaracteristicaLadron caracteristicas, List<Ciudad> ciudades) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * Este metodo compara que un conjunto de caracteristicas lo describen a el/ella
	 * 
	 * @param unaCaracteristica
	 * @return Si sus caracteristicas coinciden, univocamente es el ladron, entonces devuelve true, devuelve false en caso contrario
	 */
	public boolean coincideCon(CaracteristicaLadron unaCaracteristica) {
		// TODO En realidad esto deberia chequear los expedientes actuales.
		// Si hay mas de un expediente, devuelve false (hay que ser mas especifico)
		// Si no hay, tambien devolver false
		// Si hay un solo expediente, comparar por ciertas caracteristicas, es decir si falto especificar color de pelo pero todo lo demas matchea,
		// hay que devolver true
		//TODO(TOMAS) Lo que yo pense para esto es que OrdenDeArresto quede con TODAS las caracteristicas del ladron
		// independientemente de los filtros que use yo para armar un "CaracteristicaLadron"
		return caracteristicas.equals(unaCaracteristica);
	}

	//TODO (TOMAS) Yo sacaria este metodo, no me interesa conocer el objeto robado por el ladron luego de que lo robo
	public boolean seRobo(Objeto objeto) {
		return this.objetoRobado.compareTo(objeto) == 0;
	}

	// PreCondiciones: Se debe haber seteado el objeto que el ladron robo antes de llamar a este metodo, sino
	// no va a funcionar.
	//PD: Lo hacemos privado y void te parece??
	public List<Ciudad> elegirEscapatoria(List<Ciudad> ciudadesDelMundo, Ciudad ciudadInicial){
		//TODO(TOMAS) Para mi no deberia recibir la ciudadInicial, deberia construirla a partir de la ciudad
		// del objeto robado
		// RTA A TOMI: Me parece bien, pero en ese caso hay que hacer que mapa mundi sea visible por todos.
		this.ciudadActual = ciudadInicial;
		int cantidadCiudades = this.objetoRobado.getCantidadDeCiudades();

		if (cantidadCiudades > ciudadesDelMundo.size()){
			throw new IllegalArgumentException("No hay suficiente informacion de ciudades para generar: "
					+ cantidadCiudades + " ciudades");
		}
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		ciudades.addAll(ciudadesDelMundo);
		this.rutaEscape = new ArrayList<Ciudad>();
		this.rutaEscape.add(ciudadInicial);
		// Para que no pueda volver a la ciudad inicial:
		ciudades.remove(ciudadInicial);
		
		Iterator<Ciudad> ciudadIterator = ciudades.iterator();
		while (ciudadIterator.hasNext() && this.rutaEscape.size() < cantidadCiudades){
			Ciudad nuevaCiudad = ciudadIterator.next();
			this.rutaEscape.add(nuevaCiudad);
			ciudadIterator.remove(); //NO esta mas disponible para utilizar
		}
		 
		iterador = rutaEscape.iterator();
		this.moverAlSiguientePais();
		return this.rutaEscape;
	}


	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	public int getLongitudRecorridoEscapatoria() {
		return (this.rutaEscape.size());
	}

	public void moverAlSiguientePais() {
		if (iterador.hasNext()){
			ciudadActual = iterador.next();
		}
	}
	
	public List<Ciudad> getEscapatoria(){
		return this.rutaEscape;
	}

	public void robar(Robable objetoRobado) {
		// Aca deberia ir que el ladron se situe en la ciudad del objeto:
		// this.ciudadActual = mapa.getCiudadDeNombre(objetoRobado.getCiudadOrigen);
		this.objetoRobado = objetoRobado;
		//this.elegirEscapatoria(mapa.getCiudades, this.ciudadActual);
		//Dejando o sacando lo de pasarle la ciudad actual...
	}
	
	public List<Ciudad> getRutaEscapatoria(){
		return this.rutaEscape;
	}
}
