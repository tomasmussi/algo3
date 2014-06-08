package algo3.modelo.viaje;

import java.util.List;

import algo3.modelo.mapa.mundi.Ciudad;

public class Recorrido {

	private List<Ciudad> ciudades;
	private Integer posicion;

	public Recorrido(List<Ciudad> ciudades) {
		super();
		this.ciudades = ciudades;
		posicion = Integer.valueOf(0);
	}

	// Devolver la longitud de la lista de paises del recorrido.
	public static int longitudEnPaises() {
		return 0;
	}

	public Ciudad siguienteCiudad() {
		if (posicion.intValue() == ciudades.size()) {
			posicion = ciudades.size() - 1; // Para que este siempre en la ultima ciudad cuando no tenga mas ciudades.
		}
		return ciudades.get(posicion++);
	}

}
