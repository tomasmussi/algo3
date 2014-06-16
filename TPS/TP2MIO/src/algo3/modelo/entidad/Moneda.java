package algo3.modelo.entidad;

public class Moneda implements Entidad {

	private static final String MONEDA = "Moneda";

	private String informacion;

	public Moneda(String moneda) {
		this.informacion = moneda;
	}

	@Override
	public String getNombre() {
		return MONEDA;
	}

	@Override
	public String getInformacion() {
		return informacion;
	}

}
