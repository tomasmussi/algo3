package algo3.modelo.mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import algo3.controlador.XMLParser;

public class Mapa {
	private Map<String, Ciudad> ciudadesDelMundo;
	private static Mapa instance;

	private Mapa(){
		ciudadesDelMundo = new HashMap<String, Ciudad>();
		List<InformacionCiudad> ciudades = XMLParser.cargarCiudades();
		this.cargarListadoCiudades(ciudades);
	}

	public synchronized static Mapa getInstance() {
		if (instance == null) {
			instance = new Mapa();
		}
		return instance;
	}

	public void agregarCiudad(Ciudad ciudad) {
		ciudadesDelMundo.put(ciudad.getNombre(), ciudad);
	}

	/**
	 * Devuelve una Ciudad a partir del nombre de la misma
	 * @return Ciudad si esta dentro de las ciudades que contiene el mapa
	 *  o null si no esta dicha ciudad
	 * */
	public Ciudad getCiudadDeNombre(String nombreCiudad) {
		return ciudadesDelMundo.get(nombreCiudad);
	}

	public List<Ciudad> getListadoCiudades(){
		return new ArrayList<Ciudad>(ciudadesDelMundo.values());
	}

	public void cargarListadoCiudades(List<InformacionCiudad> ciudades) {
		//TODO: Mal, Mapa deberia manejar informacion ciudad.
		// Despues de crear objetos que hago con el edificio y las coordenadas etc?
		Iterator<InformacionCiudad> iter = ciudades.iterator();
		while (iter.hasNext()){
			InformacionCiudad informacionCiudad = iter.next();
			agregarCiudad(CiudadFactory.crearCiudadComun(informacionCiudad));
		}
	}
}
