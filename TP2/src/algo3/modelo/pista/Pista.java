package algo3.modelo.pista;

public class Pista {

	// Moneda-Bandera-Gobierno (por el momento)
	private String entidad;
	// Lo que se mostrará por pantlla
	private String contenido;
	// facil - media - dificil
	private String dificultad;

	public Pista() {
		super();
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

}
