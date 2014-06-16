package algo3.modelo.entidad;

public class Gobierno implements Entidad {

	private static final String GOBIERNO = "Gobierno";

	private String informacion;

	public Gobierno(String gobierno) {
		this.informacion = gobierno;
	}

	@Override
	public String getNombre() {
		return GOBIERNO;
	}

	@Override
	public String getInformacion() {
		return informacion;
	}

}
