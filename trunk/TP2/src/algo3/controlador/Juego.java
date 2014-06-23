package algo3.controlador;

import java.util.Iterator;
import java.util.List;

import algo3.modelo.caso.Caso;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.Mapa;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;
import algo3.modelo.policia.grado.Grado;
import algo3.modelo.tiempo.Reloj;


public class Juego {

	private Policia policia;
	private Caso caso;


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
		caso = new Caso(ladrones, objetos, grado);
		return caso;
	}

	public String ciudadActual() {
		return policia.getCiudadActual().toString();
	}

	public String buscarPista(String edificioNumero) {
		int numero = Integer.valueOf(edificioNumero);
		return policia.getCiudadActual().getTodosLosEdificios()[numero].darPista();
	}

	public String getCiudadesPosibles() {
		StringBuilder sb = new StringBuilder();
		Iterator<Ciudad> iter = caso.getRecorrido().getCiudadesPosibles(policia.getCiudadActual()).iterator();
		while (iter.hasNext()){
			Ciudad ciudad = iter.next();
			sb.append(ciudad.toString());
			sb.append("\t");
		}
		return sb.toString();
	}

	public void viajar(String ciudad) {
		policia.viajarA(Mapa.getInstance().getCiudadDeNombre(ciudad));
	}




}
