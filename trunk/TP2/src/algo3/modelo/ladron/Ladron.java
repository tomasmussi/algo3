package algo3.modelo.ladron;

import java.util.Iterator;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.objeto.Objeto;
import algo3.modelo.objeto.Robable;


/**
 * Ladron, cuando roba un objeto, le pide al objeto la ciudad de origen y la posiciona como
 * Primera en la lista de informacion ciudad. El ladron crea el recorrido y saca de la
 * informacion disponible las ciudades usadas en el recorrido.
 * 
 * 
 * 
 * 
 * 
 * 
 * */
public class Ladron {

	private CaracteristicaLadron caracteristicas;
	private Robable objetoRobado;
	private Ciudad ciudadActual;
	private Iterator<Ciudad> iterador;

	public Ladron(CaracteristicaLadron caracteristicas, Robable objetoRobado) {
		this.caracteristicas = caracteristicas;
		this.objetoRobado = objetoRobado;
	}

	public Ladron(CaracteristicaLadron caracteristicas, Robable objetoRobado, Iterator<Ciudad> recorrido) {
		this.caracteristicas = caracteristicas;
		this.objetoRobado = objetoRobado;
		this.iterador = recorrido;
		this.moverAlSiguientePais();
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
		return caracteristicas.equals(unaCaracteristica);
	}

	public boolean seRobo(Objeto objeto) {
		return this.objetoRobado.compareTo(objeto) == 0;
	}

	public void moverAlSiguientePais() {
		if (iterador.hasNext()){
			this.ciudadActual = iterador.next();
		}
	}

	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

}
