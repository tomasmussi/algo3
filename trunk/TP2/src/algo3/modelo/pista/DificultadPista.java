package algo3.modelo.pista;

import java.util.Map;

import algo3.modelo.entidad.Entidad;

public abstract class DificultadPista {

	private static final String VARIABLE = "%variable%";

	public String darPista(Entidad entidad) {
		return obtenerPistaPara(entidad);
	}

	private String obtenerPistaPara(Entidad entidad) {
		// Buscar en el XML una pregunta para esa entidad. (Tiene que ser "moneda","bandera", etc).
		// Busco una pista para este tipo particular de entidad (ej. "bandera")
		String pistaCruda = getPistas().get(entidad.getNombre());
		return pistaCruda.replace(VARIABLE, entidad.getInformacion());
	}

	private Map<String, String> getPistas() {
		return PistasProvider.getInstance().getPistasPara(getNivelDificultad());
	}

	protected abstract String getNivelDificultad();

}
