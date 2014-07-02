package algo3.modelo.entidad;

import algo3.modelo.policia.Policia;

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

	@Override
	public void atacarPolicia(Policia policia) {
		//No hacer nada. Lo tiene que atacar la entidad vacia
	}

}
