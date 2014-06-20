package algo3.modelo.caso;

import java.util.List;
import java.util.Random;

import algo3.controlador.Logger;
import algo3.modelo.estacionPolicia.EstacionDePolicia;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.MapaMundi;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.objeto.Robable;
import algo3.modelo.policia.OrdenDeArresto;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.viaje.Recorrido;


/**
 * El caso es el nivel de juego actual de policia
 * Crea un objeto robado a partir del grado del policia
 * Tambien crea un Ladron que se debe robar el objeto
 * A partir del objeto robado, se crea una ciudad, que es la ciudad
 * origen del recorrido, el ladron comienza escapandose a la siguiente ciudad.
 * El ladron necesita una
 * TODO: UNA QUE NECESITA EL LADRON (?)
 * */
public class Caso {

	private OrdenDeArresto ordenDeArresto;
	private Ladron ladron;
	private Recorrido recorrido;

	public Caso(List<CaracteristicaLadron> ladrones,
			List<CaracteristicaObjeto> objetos, Grado gradoPolicia){
		Random rand = new Random();
		int posicion = rand.nextInt(objetos.size() -1);
		Robable objetoRobado = gradoPolicia.getObjetoRobado(objetos.get(posicion));

		posicion = rand.nextInt(ladrones.size() -1);
		this.ladron = new Ladron(ladrones.get(posicion));
		this.ladron.robar(objetoRobado);
		this.recorrido = new Recorrido(this.ladron.getEscapatoria(), MapaMundi.getInstance().getListadoCiudades());

	}

	public void generarOrdenDeArresto(CaracteristicaLadron caracteristica){

		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(caracteristica);
		if (expedientes.size()==1){
			try {
				this.ordenDeArresto = new OrdenDeArresto((CaracteristicaLadron)expedientes.get(0).clone());
			}catch (CloneNotSupportedException e){
				Logger.loguearError("Hubo un error inesperado en el programa");
			}
		}
	}

	public OrdenDeArresto getOrdenDeArresto() {
		return ordenDeArresto;
	}

	public void setOrdenDeArresto(OrdenDeArresto ordenDeArresto) {
		this.ordenDeArresto = ordenDeArresto;
	}

	public Ladron getLadron() {
		return ladron;
	}

	public void setLadron(Ladron ladron) {
		this.ladron = ladron;
	}

	public Recorrido getRecorrido() {
		return this.recorrido;
	}

	public void setRecorridoCaso(Recorrido recorrido) {
		this.recorrido = recorrido;
	}
}