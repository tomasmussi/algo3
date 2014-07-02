package algo3.modelo.entidad;

import algo3.modelo.policia.Policia;

public class EntidadVacia implements Entidad {

	@Override
	public String getNombre() {
		return "Turbio";
	}

	@Override
	public String getInformacion() {
		return "Turbio";
	}

	@Override
	public void atacarPolicia(Policia policia) {
		policia.atacado();
	}

}
