package algo3.modelo.objeto;

public abstract class Objeto implements Robable, Comparable<Objeto> {

	private String nombre;

	private String ciudadOrigen;

	public Objeto(String nombre, String ciudadOrigen) {
		this.nombre = nombre;
		this.ciudadOrigen = ciudadOrigen;
	}

	@Override
	public abstract Integer getCantidadDeCiudades();

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getCiudadOrigen(){
		return ciudadOrigen;
	}

	@Override
	public int compareTo(Objeto o) {
		return nombre.compareTo(o.nombre);
	}

}
