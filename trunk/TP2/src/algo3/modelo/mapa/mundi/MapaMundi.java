package algo3.modelo.mapa.mundi;

import java.util.ArrayList;
import java.util.List;

public class MapaMundi {
	private List<Ciudad> ciudadesDelMundo;
	
	public MapaMundi(){
		this.ciudadesDelMundo = new ArrayList<Ciudad>();
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
			Ciudad estaCiudad = new Ciudad(ciudades.get(i));
			this.agregarCiudad(estaCiudad);		
		}
	}
}
