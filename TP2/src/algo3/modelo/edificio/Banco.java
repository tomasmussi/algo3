package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;

public class Banco extends Edificio {

	private String moneda;
	private Entidad eMoneda;

	public Banco(String moneda) {
		this.moneda = moneda;
	}

	public Banco(Entidad moneda) {
		this.eMoneda = moneda;
	}

	@Override
	public String darPista() {
		if (moneda == null) {
			return Edificio.MSJ_LADRON_NO_ESTUVO_AQUI;
		}
		return "Segun mis fuentes estuvo averiguando sobre el valor de " + this.moneda;
	}

	@Override
	public Entidad getElemento() {
		return eMoneda;
	}

}
