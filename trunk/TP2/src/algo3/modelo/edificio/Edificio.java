package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;

public abstract class Edificio {

	protected static final String MSJ_LADRON_NO_ESTUVO_AQUI = "Lo lamento, no hemos visto al sospechoso aqui.";

	public abstract String darPista();

	public abstract Entidad getElemento();
}
