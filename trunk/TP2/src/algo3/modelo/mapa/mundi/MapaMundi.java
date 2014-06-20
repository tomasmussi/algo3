package algo3.modelo.mapa.mundi;

import java.util.ArrayList;
import java.util.List;

import algo3.controlador.XMLParser;

public class MapaMundi {
	private List<Ciudad> ciudadesDelMundo;
	private static MapaMundi instance;

	private MapaMundi(){
		this.ciudadesDelMundo = new ArrayList<Ciudad>();
		List<InformacionCiudad> ciudades = XMLParser.cargarCiudades();
		this.cargarListadoCiudades(ciudades);
	}

	public static MapaMundi getInstance() {
		if (instance == null) {
			instance = new MapaMundi();
		}
		return instance;
	}

	public void agregarCiudad(Ciudad estaCiudad) {
		this.ciudadesDelMundo.add(estaCiudad);
	}

	public Ciudad getCiudadDeNombre(String nombreCiudad) {
		Ciudad estaCiudad = null;
		for (int i = 0; i < this.ciudadesDelMundo.size(); i++){
			Ciudad unaCiudad = ciudadesDelMundo.get(i);
			if (unaCiudad.getNombre() == nombreCiudad){
				estaCiudad = unaCiudad;
			}
		}
		return estaCiudad;
	}

	public List<Ciudad> getListadoCiudades(){
		return this.ciudadesDelMundo;
	}

	public void cargarListadoCiudades(List<InformacionCiudad> ciudades) {
		for (int i = 0; i < ciudades.size(); i++){
			Ciudad estaCiudad = new Ciudad(0,0,null,null,null,ciudades.get(i));
			this.agregarCiudad(estaCiudad);
		}
	}
}
