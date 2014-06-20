package algo3.modelo.ladron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import algo3.modelo.edificio.Aeropuerto;
import algo3.modelo.edificio.Banco;
import algo3.modelo.edificio.Edificio;
import algo3.modelo.edificio.Embajada;
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

	//PD: Lo hacemos privado y void te parece??
	//(FLAVIA) void SI pero no privado xq caso le pasa las ciudades SE LLAMA DESDE CASO
	//osea a ladron le pasan las ciudades y el elige su escapatoria pero se llama desde caso.
	//no me parece que robar lo haga
	public void elegirEscapatoria(List<Ciudad> ciudadesDelMundo){

		int cantidadCiudades = this.objetoRobado.getCantidadDeCiudades();
		if (cantidadCiudades > ciudadesDelMundo.size()){
			throw new IllegalArgumentException("No hay suficiente informacion de ciudades para generar: "
					+ cantidadCiudades + " ciudades");
		}
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		ciudades.addAll(ciudadesDelMundo);
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


	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	public void moverAlSiguientePais() {
		if (iterador.hasNext()){
			ciudadActual = iterador.next();
			int longitudEscapatoria = getEscapatoria().size();
			//si es la ultima ciudad a la q se puede mover marca q entra al edificio
			if(getCiudadActual().esMismaCiudadQue((getEscapatoria().get(longitudEscapatoria-1)))){
				this.refugiarseEnEdificio();
			}


		}
	}

	//elije random en q edificio se mete
	private void refugiarseEnEdificio() {

		List<Edificio> edificios = new ArrayList<Edificio>();
		edificios.add(new Aeropuerto());
		edificios.add(new Banco());
		edificios.add(new Embajada());
		Random rand = new Random();
		int posicion = rand.nextInt(edificios.size() -1);
		this.entrar(edificios.get(posicion));



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
	}

}
