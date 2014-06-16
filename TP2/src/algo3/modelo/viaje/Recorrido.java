package algo3.modelo.viaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;

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
			// ciudadesLibres.remove(unaCiudad); Por verse....
		}
	}

// Devuelve las posibles ciudades, donde siempre estaran de la que viene y a la que tiene que ir + otra(s) elegida(s) Randomly.
	public List<Ciudad> getCiudadesPosibles(Ciudad origen){

		List<Ciudad> ciudadesPosibles = new ArrayList<Ciudad>();
		ciudadesPosibles.addAll(ciudadesUsadas.get(origen));
	
		Random rand = new Random();
		int posicion = rand.nextInt(ciudadesLibres.size() -1);
	
		while (ciudadesPosibles.size() < 3) {
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
	/*	
	private List<InformacionCiudad> listaInformacion;
	private List<Ciudad> ciudades;
	

	public Recorrido(List<InformacionCiudad> listaInformacion, int cantidadCiudades) {
		this.ciudadesUsadas = new HashMap<Ciudad, List<Ciudad>>();
		this.listaInformacion = new ArrayList<InformacionCiudad>();
		this.listaInformacion.addAll(listaInformacion);
		this.ciudades = this.crearRecorridoDeCiudades(cantidadCiudades);
	}

	public List<Ciudad> crearRecorridoDeCiudades(int cantidadCiudades){
		if (cantidadCiudades > listaInformacion.size()){
			throw new IllegalArgumentException("No hay suficiente informacion de ciudades para generar: " 
					+ cantidadCiudades + " ciudades");
		}
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		for (int i = 0; i < cantidadCiudades; i++){
			Ciudad unaCiudad = new Ciudad(listaInformacion.get(i));
			ciudades.add(unaCiudad);
			if (i != cantidadCiudades - 1){
				// Agrego informacion de la siguiente ciudad
				unaCiudad.agregarInformacionProximaCiudad(listaInformacion.get(i+1));
				// Todavia tengo que relacionar ciudades
				relacionarCiudadesEntreSi(unaCiudad, listaInformacion.get(i+1));
			}
		}

		// Borro de la lista de informacion disponible para crear ciudades, las ciudades que son del recorrido
		Iterator<InformacionCiudad> iter = listaInformacion.iterator();
		int contador = 0;
		while (iter.hasNext() && contador < cantidadCiudades){
			iter.next();
			iter.remove();
			contador++;
		}
		return ciudades;
	}

	private void relacionarCiudadesEntreSi(Ciudad origen, InformacionCiudad infoDestino){
		List<Ciudad> posibles = new ArrayList<Ciudad>();
		Ciudad siguiente = new Ciudad(infoDestino);
		posibles.add(siguiente);
		// Origen -> Destino
		if (ciudadesUsadas.containsKey(origen)){
			ciudadesUsadas.get(origen).add(siguiente);
		} else {
			ciudadesUsadas.put(origen,posibles);
		}

		// Destino -> Origen
		// Posibles ciudades de Destino
		posibles = ciudadesUsadas.containsKey(siguiente) ? ciudadesUsadas.get(siguiente) : new ArrayList<Ciudad>();
		if (!posibles.contains(origen)){
			posibles.add(origen);
		}
		// Destino -> Origen
		ciudadesUsadas.put(siguiente, posibles);
	}

	public List<Ciudad> getCiudadesRecorrido() {
		return ciudades;
	}

	public int longitudEnPaises() {
		return ciudades.size();
	}

	public List<Ciudad> getCiudadesPosibles(Ciudad origen){
		if (!ciudadesUsadas.containsKey(origen)){
			ciudadesUsadas.put(origen, new ArrayList<Ciudad>(4));
		}
		List<Ciudad> posibles = ciudadesUsadas.get(origen);
		Iterator<InformacionCiudad> it = listaInformacion.iterator();
		while (posibles.size() < 4 && it.hasNext()){
			InformacionCiudad info = it.next();
			Ciudad nuevaCiudad = new Ciudad(info);
			posibles.add(nuevaCiudad);
			it.remove();
		}
		return posibles;
	}
	
		
	public boolean sonConsecutivas(Ciudad ciudadPrevia, Ciudad ciudadPosterior) {
		int indicePrevio = ciudades.indexOf(ciudadPrevia);
		int indicePosterior = ciudades.indexOf(ciudadPosterior);
		if (indicePrevio == -1 || indicePosterior == -1){
			// Si ninguna ciudad del recorrido, devuelvo que no son consecutivas
			return false;
		}
		return indicePrevio == indicePosterior - 1;
	}


// RENOVATION TIME!
*/
	
	