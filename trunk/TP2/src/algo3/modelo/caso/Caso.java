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
 * 
 * */
public class Caso {

	//	Crea un ladron random y lo hace robarse un objeto al azar de la lista de objetos.
	// Depende del grado del policia el tipo de objeto.


	private OrdenDeArresto ordenDeArresto;
	private Ladron ladron;
	private Robable objeto;
	private Recorrido recorrido;

	public Caso(MapaMundi mapa, List<CaracteristicaLadron> ladrones,
			List<CaracteristicaObjeto> objetos, Grado gradoPolicia){
		Random rand = new Random();
		int posicion = rand.nextInt(objetos.size() -1);
		Robable objetoRobado = gradoPolicia.getObjetoRobado(objetos.get(posicion));
		
		posicion = rand.nextInt(ladrones.size() -1);
		Ladron esteLadron = new Ladron(ladrones.get(posicion));
		//esteLadron.robar(objetoRobado);
		//this.recorrido = new Recorrido(esteLadron.getRutaEscapatoria(), mapa.getListadoCiudades());
		this.ladron = esteLadron;
		//TODO: (Tomas) Yo no se bien, pero la idea es que Ladron se robe un objeto.
		// Por lo cual se deberia llamar al metodo ladron.robarObjeto(objetoRobado).
		// No se si aca o en otro lado, chequear conmigo quien cambie esto
		// (Eli) Tomi, yo creo que tiene que ir aca, ya que pensandolo en la vida real, hay un caso cuando 
		//hay un ladron que se rob� un objeto, osea que el objeto ya esta robado y el ladron ya planeo su ruta de escape.
		//Deje comentado lo que me parece que tiene que ir.
		this.objeto = objetoRobado;
	}
	public Caso(Robable objeto, Ladron ladron, Recorrido recorrido){
		this.ladron = ladron;
		this.objeto = objeto;
		this.recorrido = recorrido;
	}

	public Caso(){

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

	public void asignarCiudades(){
		//TODO: A IMPLEMENTAR
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
	public Robable getObjeto() {
		return objeto;
	}
	public void setObjeto(Robable objeto) {
		this.objeto = objeto;
	}
	public Recorrido getRecorrido() {
		return this.recorrido;
	}
	public void setRecorridoCaso(Recorrido recorrido) {
		this.recorrido = recorrido;
	}


}



