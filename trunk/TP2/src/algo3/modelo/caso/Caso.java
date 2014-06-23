package algo3.modelo.caso;

import java.util.List;
import java.util.Random;

import algo3.modelo.estacionPolicia.EstacionDePolicia;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.Mapa;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.OrdenDeArresto;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.viaje.DestinosPosibles;


/**
 * El caso es el nivel de juego actual de policia
 * Crea un objeto robado a partir del grado del policia
 * Tambien crea un Ladron que se debe robar el objeto
 * A partir del objeto robado, se crea una ciudad, que es la ciudad
 * origen del recorrido, el ladron comienza escapandose a la siguiente ciudad.
 * */
public class Caso {


	private OrdenDeArresto ordenDeArresto;
	private Ladron ladron;
	private DestinosPosibles recorrido;

	/**
	 * Construye un Caso. Es el objeto que representa un nivel en el juego que el
	 * Policia (el jugador) debera resolver
	 * @param Lista de ladrones
	 * @param Lista de objetos
	 * @param El grado del policia
	 * 
	 * Post condiciones: Obtengo un caso con un ladron y un objeto que el ladron se roba,
	 * seleccionado a partir de las listas de que se obtienen por parametro.
	 * 
	 */
	public Caso(List<CaracteristicaLadron> ladrones, List<CaracteristicaObjeto> objetos, Grado gradoPolicia){

		Robable objetoRobado = gradoPolicia.getObjetoRobado(objetos.get(getNumeroRandom(objetos.size())));

		this.ladron = new Ladron(ladrones.get(getNumeroRandom(ladrones.size())));
		this.ladron.robar(objetoRobado);
		this.recorrido = new DestinosPosibles(ladron.getEscapatoria());
	}

	private int getNumeroRandom(int maximaCantidad){
		Random rand = new Random();
		return maximaCantidad != 1 ? rand.nextInt(maximaCantidad - 1) : 0;
	}

	public void generarOrdenDeArresto(CaracteristicaLadron caracteristica){
		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(caracteristica);
		if (expedientes.size() == 1){
			this.ordenDeArresto = new OrdenDeArresto(expedientes.get(0).clone());
		}
	}

	public OrdenDeArresto getOrdenDeArresto() {
		return ordenDeArresto;
	}

	public Ladron getLadron() {
		return ladron;
	}

	public DestinosPosibles getRecorrido() {
		return recorrido;
	}

	public Ciudad getCiudadOrigenDeObjeto(){
		Robable objetoRobado = ladron.getObjetoRobado();
		return Mapa.getInstance().getCiudadDeNombre(objetoRobado.getCiudadOrigen());
	}

	public boolean ultimoPaisLadron(Ciudad ciudadActual) {
		return ciudadActual.equals(ladron.getCiudadActual());
	}

}
