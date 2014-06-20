package algo3.controlador;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.policia.Policia;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.tiempo.Reloj;


public class Juego {

	private Policia policia;


	public Juego(Policia policia){
		this.policia = policia;
	}

	public void iniciar(){


		Reloj reloj = new Reloj();
		policia.setReloj(reloj);

		/*tendría que haber un WHILE para q pueda generarcaso mas de una vez ya que el mismo policia
		puede jugar varios casos.
		Controla que si se gano el caso muestra tal msj, sino tal otro
		si no desea continuar es la CONDICION DE CORTE para salir del WHILE*/

		Caso caso = generarCaso();
		policia.asignarCaso(caso);

	}

	private Caso generarCaso() {

		List<CaracteristicaLadron> ladrones = XMLParser.cargarExpedientes();
		Grado grado = policia.getGrado();
		//preciso lista de objetos
		return new Caso(ladrones, new ArrayList(), grado);
	}




}
