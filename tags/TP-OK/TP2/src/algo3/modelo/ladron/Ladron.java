package algo3.modelo.ladron;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.CiudadFactory;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.Policia;
import algo3.modelo.viaje.Mapa;


/**
 * Ladron, cuando roba un objeto, le pide al objeto la ciudad de origen y la posiciona como
 * Primera en la lista de informacion ciudad. El ladron crea su recorrido y saca de la
 * informacion disponible las ciudades usadas en el recorrido.

 * 
 * */
public class Ladron implements Observer {

	private CaracteristicaLadron caracteristicas;
	private Robable objetoRobado;
	private Ciudad ciudadActual;
	private List<Ciudad> rutaEscape;
	private Iterator<Ciudad> iterador;
	private boolean refugiado;

	/**
	 * 
	 * Construye un ladron a partir de caracteristicas
	 * */
	public Ladron(CaracteristicaLadron caracteristicas) {
		this.caracteristicas = caracteristicas;
		refugiado = false;
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


	private void elegirEscapatoria() throws CiudadNoEncontradaException {
		rutaEscape = CiudadFactory.crearRecorridoDeCiudades(caracteristicas, objetoRobado.getCiudadOrigen(), objetoRobado.getCantidadDeCiudades());
		iterador = rutaEscape.iterator();
		this.moverAlSiguientePais();
	}


	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	public void moverAlSiguientePais() {
		if (iterador.hasNext()){
			ciudadActual = iterador.next();
		} else {
			refugiarseEnEdificio();
		}
	}

	private void refugiarseEnEdificio() {
		if (refugiado){
			return;
		}
		refugiado = true;
		Edificio[] edificios = ciudadActual.getTodosLosEdificios();
		Random rand = new Random();
		int posicion = rand.nextInt(edificios.length);
		if (edificios[posicion] != null){
			this.entrar(edificios[posicion]);
		}
	}

	private void entrar(Edificio edificio) {
		edificio.refugiarLadron(this);
	}

	public List<Ciudad> getEscapatoria(){
		return rutaEscape;
	}

	/**
	 * Se llama este metodo cuando el ladron se roba un objeto al comienzo de un caso.
	 * El ladron se roba el objeto y en base al objeto elige un recorrido por el cual escaparse.
	 * Ese recorrido se actualiza cuando el policia se va acercando al ladron
	 * @throws CiudadNoEncontradaException
	 */
	public void robar(Robable objetoRobado) throws CiudadNoEncontradaException {
		this.objetoRobado = objetoRobado;
		elegirEscapatoria();
	}

	public Robable getObjetoRobado(){
		return this.objetoRobado;
	}

	public Ciudad getCiudadOrigen() {
		return rutaEscape.get(0);
	}

	public void informarRecorrido(Mapa mapa) {
		mapa.agregarCiudades(rutaEscape);
	}

	@Override
	public void update(Observable observado, Object arg1) {
		Policia policia = (Policia) observado;
		if (policia.getCiudadActual().equals(this.ciudadActual)){
			this.moverAlSiguientePais();
		}
	}

	public String getSexo() {
		return caracteristicas.getSexo();
	}

}