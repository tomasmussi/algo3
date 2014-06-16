package algo3.modelo.ladron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
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
	private List <Ciudad> recorridoEscapatoria;
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

	public List<Ciudad> elegirEscapatoria(List<Ciudad> ciudadesDelMundo, Ciudad ciudadInicial, int cantidadCiudades){
		if (cantidadCiudades > ciudadesDelMundo.size()){
			throw new IllegalArgumentException("No hay suficiente informacion de ciudades para generar: " 
					+ cantidadCiudades + " ciudades");
		}
		
	// Desordeno un poco la lista:
		for (int i = 0; i < ciudadesDelMundo.size(); i++) {
			int posicionRandom = ((int) Math.random() * 10) % ciudadesDelMundo.size();
			Ciudad elemento = ciudadesDelMundo.get(i);
			ciudadesDelMundo.set(i, ciudadesDelMundo.get(posicionRandom));
			ciudadesDelMundo.set(posicionRandom, elemento);
		}
		this.recorridoEscapatoria.add(ciudadInicial);
		for (int i = 0; i < cantidadCiudades; i++) {
			if (ciudadInicial != ciudadesDelMundo.get(i)) {
				this.recorridoEscapatoria.add(ciudadesDelMundo.get(i));
			}
		}
		
		return this.recorridoEscapatoria;
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
