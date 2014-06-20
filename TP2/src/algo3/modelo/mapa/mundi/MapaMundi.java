package algo3.modelo.mapa.mundi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import algo3.controlador.XMLParser;

public class MapaMundi {
	private Map<String, Ciudad> ciudadesDelMundo;
	private static MapaMundi instance;

	private MapaMundi(){
		ciudadesDelMundo = new HashMap<String, Ciudad>();
		List<InformacionCiudad> ciudades = XMLParser.cargarCiudades();
		this.cargarListadoCiudades(ciudades);
	}

	public synchronized static MapaMundi getInstance() {
		if (instance == null) {
			instance = new MapaMundi();
		}
		return instance;
	}

	public void agregarCiudad(Ciudad ciudad) {
		this.ciudadesDelMundo.put(ciudad.getNombre(), ciudad);
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

		Iterator<InformacionCiudad> iter = ciudades.iterator();
		while (iter.hasNext()){
			InformacionCiudad informacionCiudad = iter.next();
			agregarCiudad(new Ciudad(0,0,null,null,null,informacionCiudad));
		}
	}
}
