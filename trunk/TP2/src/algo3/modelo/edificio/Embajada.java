package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;

public class Embajada extends Edificio {

	private String gobierno;
	private Entidad entidad;

	public Embajada() {
		super();
	}

	public Embajada(Entidad entidad) {
		this.entidad = entidad;
	}

	public Embajada(String gobierno) {
		super();
		this.gobierno = gobierno;
	}

	public void setGobierno(String gobierno) {
		this.gobierno = gobierno;
	}

	@Override
	public String darPista() {
		if (gobierno == null) {
			return Edificio.MSJ_LADRON_NO_ESTUVO_AQUI;
		}
		return "Un sospechoso estuvo aquí averiguando sobre el tipo de gobierno de un " + this.gobierno;
	}

	@Override
	public Entidad getElemento() {
		return entidad;
	}

}
