package algo3.controlador;

import java.util.List;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.objeto.CaracteristicaObjeto;
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
		Caso caso = generarCaso();
		policia.asignarCaso(caso);

	}

	private Caso generarCaso() {

		List<CaracteristicaLadron> ladrones = XMLParser.cargarExpedientes();
		Grado grado = policia.getGrado();
		List<CaracteristicaObjeto> objetos = XMLParser.cargarObjetos();
		return new Caso(ladrones, objetos, grado);
	}




}
