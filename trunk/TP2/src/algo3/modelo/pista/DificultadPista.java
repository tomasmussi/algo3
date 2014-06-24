package algo3.modelo.pista;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.entidad.Entidad;

public abstract class DificultadPista {

	private static final String VARIABLE = "%variable%";
	private static final String PISTA_NULL = "Lo lamento, no hemos visto al sospechoso.";

	public String darPista(Edificio edificio) {
		// Si entidad es NULL, se devuelve una "pista" default de que no se ha visto el ladron.
		if ((edificio.getElemento() == null) && (edificio.getCaracteristicaLadron() == null)) {
			return PISTA_NULL;
		}
		String pista = obtenerPistaPara(edificio.getElemento());
		if (edificio.getCaracteristicaLadron() != null) {
			pista = pista + darPistaLadron(edificio.getCaracteristicaLadron());
		}
		return pista;
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

	public String darPistaLadron(String caracteristicaLadron) {
		String pistaLadron = "";
		Random rdm = new Random();
		int random = rdm.nextInt(3);
		if (random == 2) {
			if (getColoresDePelo().contains(caracteristicaLadron)) {
				pistaLadron = " Tenia el pelo " + caracteristicaLadron + "."; // mandar a constante.
			} else {
				pistaLadron = " " + getPistas().get(caracteristicaLadron);
			}
		}
		return pistaLadron;
	}

	private List<String> getColoresDePelo() {
		List<String> coloresDePelo = new ArrayList<String>();
		coloresDePelo.add("Rojo");
		coloresDePelo.add("Negro");
		coloresDePelo.add("Rubio");
		coloresDePelo.add("Castanio");
		return coloresDePelo;
	}

	protected abstract String getNivelDificultad();

}
