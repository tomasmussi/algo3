package algo3.modelo.pista;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import algo3.controlador.Logger;

import com.thoughtworks.xstream.XStream;

public class PistasProvider {

	private static PistasProvider provider;

	// Dificultad=[pista1,pista2,..]
	private Map<String, Map<String, String>> pistas;

	private PistasProvider() {
		super();
		pistas = new HashMap<String, Map<String, String>>();
		cargarPistas();
	}

	private void cargarPistas() {
		try {
			XStream xmlReader = new XStream();
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/pista/pistas.xml");
			List<Pista> pistasDelXML = (List<Pista>) xmlReader.fromXML(in);
			for (Pista pista : pistasDelXML) {
				Map<String, String> pistasPorEntidad = new HashMap<String, String>();
				if (pistas.containsKey(pista.getDificultad())) {
					pistasPorEntidad = pistas.get(pista.getDificultad());
				}
				pistasPorEntidad.put(pista.getEntidad(), pista.getContenido());
				pistas.put(pista.getDificultad(), pistasPorEntidad);
			}
			in.close();
		} catch (IOException e) {
			Logger.loguearError(e);
		}
	}

	public static PistasProvider getInstance() {
		if (provider == null) {
			provider = new PistasProvider();
		}
		return provider;
	}

	// TODO: Agregar las pistas de "Algo turbio esta pasando en la ciudad", etc, etc.
	public Map<String, String> getPistasPara(String dificultad) {
		return pistas.get(dificultad);
	}

}
