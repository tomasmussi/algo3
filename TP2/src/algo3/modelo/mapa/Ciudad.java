package algo3.modelo.mapa;

import algo3.modelo.edificio.Edificio;

public class Ciudad {

	private static final int RADIO_TIERRA = 6371;
	private float latitud;
	private float longitud;
	private Edificio edificio1;
	private Edificio edificio2;
	private Edificio edificio3;
	private String nombre;
	private InformacionCiudad informacion;

	/**
	 * Construye una ciudad a partir de 3 edificios y la informacion propia de la ciudad
	 * Almacena tambien la latitud y longitud de la misma
	 * */
	public Ciudad(Edificio edificio1, Edificio edificio2, Edificio edificio3, InformacionCiudad informacion) {
		this.nombre = informacion.getNombreCiudad();
		this.latitud = Float.valueOf(informacion.getLatitud());
		this.longitud = Float.valueOf(informacion.getLongitud());
		this.edificio1 = edificio1;
		this.edificio2 = edificio2;
		this.edificio3 = edificio3;
		this.informacion = informacion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getColoresBandera() {
		return informacion.getInformacionViaje();
	}

	public Edificio[] getTodosLosEdificios() {
		Edificio[] edificios = { edificio1, edificio2, edificio3 };
		return edificios;
	}

	@Override
	public String toString() {
		return "Ciudad: " + this.nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Ciudad other = (Ciudad) obj;
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		return true;
	}

	public int getDistanciaCon(Ciudad ciudad) {
		return distFrom(latitud, longitud, ciudad.latitud, ciudad.longitud).intValue();
	}

	/**
	 * Metodo que calcula la distancia entre dos ciudades a partir de sus latitudes y longitudes
	 * @return distancia en kilometros que separa ambas ciudades recorridas por el globo
	 * */
	public static Float distFrom(float latitud1, float longitud1, float latitud2, float longitud2) {
		double dLat = Math.toRadians(latitud2-latitud1);
		double dLng = Math.toRadians(longitud2-longitud1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2)) *
				Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		float dist = (float) (RADIO_TIERRA * c);

		return dist;
	}

}
