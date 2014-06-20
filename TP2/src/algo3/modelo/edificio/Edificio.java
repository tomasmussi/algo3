package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;
import algo3.modelo.ladron.Ladron;

public abstract class Edificio {

	//asi static(?)
	private static Ladron ladron ;

	protected static final String MSJ_LADRON_NO_ESTUVO_AQUI = "Lo lamento, no hemos visto al sospechoso aqui.";

	public abstract String darPista();

	public abstract Entidad getElemento();

	public void refugiarLadron(Ladron ladron) {
		this.ladron = ladron;

	}

	public Boolean estaLadron(){
		return (ladron != null);
	}


}
