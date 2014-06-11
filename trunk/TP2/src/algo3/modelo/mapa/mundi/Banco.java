package algo3.modelo.mapa.mundi;

public class Banco extends Edificio {

	private String moneda;

	public Banco() {
		super();
	}

	public Banco(String moneda) {
		super();
		this.moneda = moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@Override
	public String darPista() {
		if (moneda == null) {
			return Edificio.MSJ_LADRON_NO_ESTUVO_AQUI;
		}
		return "Segun mis fuentes estuvo averiguando sobre el valor de " + this.moneda;
	}

}
