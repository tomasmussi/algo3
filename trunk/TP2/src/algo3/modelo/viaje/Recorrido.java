package algo3.modelo.viaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import algo3.modelo.mapa.mundi.Ciudad;

public class Recorrido {
	private Map<Ciudad, List<Ciudad>> ciudadesUsadas;
	private List<Ciudad> ciudadesLibres;

	// Recorrido NO distingue entre las ciudades por las que debe pasar el ladron. Solo las recibe para armar
	// los planes de destinos, pero no guarda el recorrido en si. Solo el ladron lo sabe.

	public Recorrido (List<Ciudad> escapatoria, List<Ciudad> todasLasCiudades) {
		this.ciudadesUsadas = new HashMap<Ciudad, List<Ciudad>>();
		this.ciudadesLibres = new ArrayList<Ciudad>();
		this.ciudadesLibres.addAll(todasLasCiudades);
		this.crearMapaDeRecorrido(escapatoria);
	}

	public void crearMapaDeRecorrido(List<Ciudad> escapatoria) {
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


	public void actualizarNexoEntre(Ciudad ciudad1, Ciudad ciudad2) {
		if (!ciudadesUsadas.containsKey(ciudad2)) {
			List<Ciudad> posibles = new ArrayList<Ciudad>();
			posibles.add(ciudad1);
			ciudadesUsadas.put(ciudad2, posibles);
			//TODO: (Eli) Analizar si realmente es necesaria esta ultima linea.
			ciudadesLibres.remove(ciudad2); 
		}
	}
	
	// TODO: Hacer que el 4 (cantidad de opciones a ofrecer) sea una constante. ??
	// Devuelve las posibles ciudades, donde siempre estaran de la que viene y a la que tiene que ir + otra(s) elegida(s) Randomly.
	public List<Ciudad> getCiudadesPosibles(Ciudad origen){

		List<Ciudad> ciudadesPosibles = new ArrayList<Ciudad>();
		ciudadesPosibles.addAll(ciudadesUsadas.get(origen));

		Random rand = new Random();
		int posicion = rand.nextInt(ciudadesLibres.size() -1);

		while (ciudadesPosibles.size() < 4) {
			ciudadesPosibles.add(ciudadesLibres.get(posicion));
			posicion += 1;
		}
		return ciudadesPosibles;
	}

	public boolean sonConsecutivas(Ciudad ciudadPrevia, Ciudad ciudadPosterior) {
		List <Ciudad> ciudadesPosibles = ciudadesUsadas.get(ciudadPrevia);
		return (ciudadesPosibles.contains(ciudadPosterior));
	}
}
