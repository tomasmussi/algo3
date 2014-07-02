package algo3.modelo.pista;

import java.util.Map;

import algo3.controlador.XMLParser;

public class PistasProvider {

	private static PistasProvider provider;

	// Dificultad=[pista1,pista2,..]
	private Map<String, Map<String, String>> pistas;

	private PistasProvider() {
		super();
		pistas = XMLParser.cargarPistas();
	}


	public static PistasProvider getInstance() {
		if (provider == null) {
			provider = new PistasProvider();
		}
		return provider;
	}

	public Map<String, String> getPistasPara(String dificultad) {
		return pistas.get(dificultad);
	}

}
