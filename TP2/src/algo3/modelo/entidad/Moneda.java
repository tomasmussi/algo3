package algo3.modelo.entidad;

import algo3.modelo.policia.Policia;

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

	@Override
	public void atacarPolicia(Policia policia) {
		//No hacer nada. Lo tiene que atacar la entidad vacia
	}

}
