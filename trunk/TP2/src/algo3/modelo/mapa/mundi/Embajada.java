package algo3.modelo.mapa.mundi;

public class Embajada extends Edificio {

	private String gobierno;

	public Embajada() {
		super();
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

}
