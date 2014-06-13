package algo3.modelo.caso;

import java.util.List;

import algo3.modelo.estacionPolicia.EstacionDePolicia;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.objeto.Objeto;
import algo3.modelo.policia.OrdenDeArresto;



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

	/*
	 * Pre: Crear objeto robado a partir de la categoria del jugador
	 * 		(novato->objetoComun; detective/investigador->objetoValioso; sargento->objetoMuyValioso).
	 * 		Crear Ladrón y asignarle dicho objeto.
	 * 1ro: cantidad de ciudades a generar a partir del valor del objeto robado.
	 * 2do: generar ciudades.
	 * 3ro: linkear ciudades (dar referencias a la ciudad sobre la siguiente - casos especiales: primera, ultima).
	 * 4to: situar ladron en la primer ciudad.
	 * 5to:
	 * 
	 */

	
	private OrdenDeArresto ordenDeArresto;
	private Ladron ladron;
	private Objeto objeto;



	public OrdenDeArresto generarOrdenDeArresto(CaracteristicaLadron caracteristica  ){

		List<CaracteristicaLadron> expedientes = EstacionDePolicia.getInstance().buscarExpediente(caracteristica);
		if (expedientes.size()==1){
			try {
				this.ordenDeArresto = new OrdenDeArresto((CaracteristicaLadron)expedientes.get(0).clone());
				return ordenDeArresto;
			}catch (CloneNotSupportedException e){
				System.out.println("Hubo un error inesperado en el programa");

			}

		}
		return null;
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
	public Objeto getObjeto() {
		return objeto;
	}
	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public List<Ciudad> getRecorrido() {
		return null;
	}




}




