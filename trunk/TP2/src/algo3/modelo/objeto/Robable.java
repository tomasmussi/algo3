package algo3.modelo.objeto;

public interface Robable extends Comparable<Robable> {

	/**
	 * Cantidad de ciudades por las cuales se escapa el ladron cuando se roba el objeto
	 * */
	public abstract Integer getCantidadDeCiudades();

	/**
	 * @return nombre del objeto robado
	 * */
	public abstract String getNombre();

	/**
	 * @return ciudad origen del objeto robado
	 * */
	public abstract String getCiudadOrigen();

}
