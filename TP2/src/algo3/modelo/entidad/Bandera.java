package algo3.modelo.entidad;

import algo3.modelo.policia.Policia;

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

	@Override
	public void atacarPolicia(Policia policia) {
		//No hacer nada. Lo tiene que atacar la entidad vacia
	}

}
