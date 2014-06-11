package algo3.modelo.mapa.mundi;

public class Aeropuerto extends Edificio {

	private String coloresBanderas;

	public Aeropuerto() {
		super();
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
		return "Me dicen mis fuentes que se fue en un avion con una bandera " + this.coloresBanderas + " en sus alas.";
	}

}
