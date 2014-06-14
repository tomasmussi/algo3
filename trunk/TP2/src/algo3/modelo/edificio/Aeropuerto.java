package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;

public class Aeropuerto extends Edificio {

	private String coloresBanderas;
	private Entidad bandera;

	public Aeropuerto() {
		super();
	}

	public Aeropuerto(Entidad bandera) {
		super();
		this.bandera = bandera;
	}

	public Aeropuerto(String coloresBanderas) {
		super();
		this.coloresBanderas = coloresBanderas;
	}

	public void setBandera(String coloresBanderas) {
		this.coloresBanderas = coloresBanderas;
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
		return bandera;
	}

}
