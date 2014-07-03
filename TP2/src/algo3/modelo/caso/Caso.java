package algo3.modelo.caso;

import java.util.List;
import java.util.Random;

import algo3.modelo.estacionPolicia.EstacionDePolicia;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.OrdenDeArresto;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.viaje.Mapa;


/**
 * El caso es el nivel de juego actual de policia
 * Crea un objeto robado a partir del grado del policia
 * Tambien crea un Ladron que se debe robar el objeto
 * A partir del objeto robado, se crea una ciudad, que es la ciudad
 * origen del recorrido, el ladron comienza escapandose a la siguiente ciudad.
 * */
public class Caso{


	private OrdenDeArresto ordenDeArresto;
	private Ladron ladron;
	private Mapa mapa;

	/**
	 * Construye un Caso. Es el objeto que representa un nivel en el juego que el
	 * Policia (el jugador) debera resolver
	 * @param Lista de ladrones
	 * @param Lista de objetos
	 * @param El grado del policia
	 * 
	 * Post condiciones: Obtengo un caso con un ladron y un objeto que el ladron se roba,
	 * seleccionado a partir de las listas de que se obtienen por parametro.
	 * @throws CiudadNoEncontradaException
	 * 
	 */
	public Caso(List<CaracteristicaLadron> ladrones, List<CaracteristicaObjeto> objetos, Grado gradoPolicia) throws CiudadNoEncontradaException{

		Robable objetoRobado = gradoPolicia.getObjetoRobado(objetos.get(getNumeroRandom(objetos.size())));

		ladron = new Ladron(ladrones.get(getNumeroRandom(ladrones.size())));
		ladron.robar(objetoRobado);
		mapa = new Mapa();
		ladron.informarRecorrido(mapa);
	}

	private int getNumeroRandom(int maximaCantidad){
		Random rand = new Random();
		return maximaCantidad != 1 ? rand.nextInt(maximaCantidad - 1) : 0;
	}

	/**
	 * Genera una orden de arresto para las caracteristicas cargadas por el jugador
	 * @return Lista de todos los sospechosos que coinciden con las caracteristicas
	 * */
	public List<CaracteristicaLadron> generarOrdenDeArresto(CaracteristicaLadron caracteristica){
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(caracteristica);
		if (expedientes.size() == 1){
			this.ordenDeArresto = new OrdenDeArresto(expedientes.get(0).clone());
		} else {
			this.ordenDeArresto = null;
		}
		return expedientes;
	}

	public OrdenDeArresto getOrdenDeArresto() {
		return ordenDeArresto;
	}

	public Ladron getLadron() {
		return ladron;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public Ciudad getCiudadOrigenDeObjeto(){
		return ladron.getCiudadOrigen();
	}



}
