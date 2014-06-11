package algo3.modelo.viaje;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.mapa.mundi.Ciudad;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.objeto.Robable;

public class Recorrido {

	private List<Ciudad> ciudades;
	private Integer posicion;

	public Recorrido(List<InformacionCiudad> listaInformacion, Robable objeto) {
		// TODO: el recorrido no debería recibir una lista de informacionCiudad porque no debería depender de esta informacion, si no
		// que deberia generarse sola segun la cantidad de paises a recorrer determinada por el objeto robado.
		this.ciudades = this.crearRecorridoDeCiudades(listaInformacion, objeto.getCantidadDeCiudades());
		posicion = Integer.valueOf(0);
	}
	
	public List<Ciudad> crearRecorridoDeCiudades(List<InformacionCiudad> listaInformacion,int cantidadPaises){
		if (cantidadPaises > listaInformacion.size()){
			System.out.println("No se cumple esta condicion");
		}
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		for (int i = 0; i < cantidadPaises; i++){
			ciudades.add(new Ciudad(listaInformacion.get(i)));
		}
		return ciudades;
	}
	
	public List<Ciudad> obtenerCiudadesRecorrido() {
		return this.ciudades;
	}

	// Devolver la longitud de la lista de paises del recorrido.
	public int longitudEnPaises() {
		return ciudades.size();
	}

	public Ciudad siguienteCiudad() {
		if (posicion.intValue() == ciudades.size()) {
			posicion = ciudades.size() - 1; // Para que este siempre en la ultima ciudad cuando no tenga mas ciudades.
		}
		return ciudades.get(posicion++);
	}

}
