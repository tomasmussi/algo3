package algo3.modelo.entidad;

public class Bandera implements Entidad {

	private static final String BANDERA = "Bandera";

	private String informacion;

	public Bandera(String bandera) {
		this.informacion = bandera;
	}

	@Override
	public String getNombre() {
		return BANDERA;
	}

	@Override
	public String getInformacion() {
		return informacion;
	}

}
