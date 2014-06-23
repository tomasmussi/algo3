package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;

public class Puerto extends Edificio {

	private String coloresBanderas;
	private Entidad bandera;

	public Puerto(Entidad bandera) {
		this.bandera = bandera;
	}

	@Override
	public String darPista() {
		if (coloresBanderas == null) {
			return Edificio.MSJ_LADRON_NO_ESTUVO_AQUI;
		}
		return "Me dicen mis fuentes que se fue en un avion con " + this.coloresBanderas + " en sus alas.";
	}

	@Override
	public Entidad getElemento() {
		// TODO Auto-generated method stub
		return null;
	}

}
