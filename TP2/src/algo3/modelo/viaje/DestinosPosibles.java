package algo3.modelo.viaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import algo3.modelo.mapa.Ciudad;
import algo3.modelo.mapa.Mapa;

public class DestinosPosibles {

	private static final int CANTIDAD_CIUDADES_POSIBLES = 4;
	private Map<Ciudad, List<Ciudad>> ciudadesUsadas;
	private List<Ciudad> ciudadesLibres;

	// Recorrido NO distingue entre las ciudades por las que debe pasar el ladron. Solo las recibe para armar
	// los planes de destinos, pero no guarda el recorrido en si. Solo el ladron lo sabe.

	//TODO: CAMBIAR NOMBRE A DESTINOSPOSIBLES
	public DestinosPosibles (List<Ciudad> escapatoria) {
		ciudadesUsadas = new HashMap<Ciudad, List<Ciudad>>();
		ciudadesLibres = new ArrayList<Ciudad>();
		ciudadesLibres.addAll(Mapa.getInstance().getListadoCiudades());
		crearMapaDeRecorrido(escapatoria);
	}

	private void crearMapaDeRecorrido(List<Ciudad> escapatoria) {
		for (int i = 0; i < escapatoria.size(); i++){
			Ciudad unaCiudad = escapatoria.get(i);
			List<Ciudad> posibles = new ArrayList<Ciudad>();

			if (i != escapatoria.size() - 1){
				posibles.add(escapatoria.get(i+1));}
			if (i != 0){
				posibles.add(escapatoria.get(i-1));}

			ciudadesUsadas.put(unaCiudad, posibles);
			ciudadesLibres.remove(unaCiudad);
		}
	}


	public void actualizarNexoEntre(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
		if (!ciudadesUsadas.containsKey(ciudadDestino)) {
			List<Ciudad> posibles = new ArrayList<Ciudad>();
			posibles.add(ciudadOrigen);
			ciudadesUsadas.put(ciudadDestino, posibles);
			if (!ciudadesUsadas.get(ciudadOrigen).contains(ciudadDestino)) {
				ciudadesUsadas.get(ciudadOrigen).add(ciudadDestino);
			}
			ciudadesLibres.remove(ciudadDestino);
		}
	}
	//TODO:SE LLAMA DESDE EVENTO
	// Hacer que el 4 (cantidad de opciones a ofrecer) sea una constante. ??
	// Devuelve las posibles ciudades, donde siempre estaran de la que viene y a la que tiene que ir + otra(s) elegida(s) Randomly.
	public List<Ciudad> getCiudadesPosibles(Ciudad origen){

		List<Ciudad> ciudadesPosibles = new ArrayList<Ciudad>();
		ciudadesPosibles.addAll(ciudadesUsadas.get(origen));

		Iterator<Ciudad> iter = ciudadesLibres.iterator();
		while (ciudadesPosibles.size() < CANTIDAD_CIUDADES_POSIBLES && iter.hasNext()) {
			Ciudad ciudadPosible = iter.next();
			ciudadesPosibles.add(ciudadPosible);
			//Lo saco de las listas posibles para no armar un recorrido que pueda volver
			iter.remove();
		}
		return ciudadesPosibles;
	}

	public boolean sonConsecutivas(Ciudad ciudadPrevia, Ciudad ciudadPosterior) {
		List<Ciudad> ciudadesPosibles = ciudadesUsadas.get(ciudadPrevia);
		return ciudadesPosibles.contains(ciudadPosterior);
	}
}
