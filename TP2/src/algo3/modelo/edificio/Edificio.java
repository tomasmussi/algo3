package algo3.modelo.edificio;

import java.util.Observable;
import java.util.Random;

import algo3.modelo.entidad.Entidad;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.policia.Policia;

public abstract class Edificio extends Observable {

	private Ladron ladron;

	private CaracteristicaLadron caracteristicasLadron;

	protected static final String MSJ_LADRON_NO_ESTUVO_AQUI = "Lo lamento, no hemos visto al sospechoso aqui.";

	public static final String LADRON_ENCONTRADO = "LADRON ENCONTRADO";

	public abstract Entidad getElemento();

	protected Edificio(CaracteristicaLadron caracteristicas) {
		this.caracteristicasLadron = caracteristicas;
	}

	public void refugiarLadron(Ladron ladron) {
		this.ladron = ladron;
	}

	public boolean estaLadron() {
		if(ladron!=null){
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
		//return (ladron != null);
	}

	public String getCaracteristicaLadron() {
		return getCaracteristcaRandom();
	}

	private String getCaracteristcaRandom() {
		if (caracteristicasLadron == null) {
			return null;
		}
		Random rdm = new Random();
		int posicion = rdm.nextInt(caracteristicasLadron.getCaracteristicasFisicas().size());
		String caracteristica = caracteristicasLadron.getCaracteristicasFisicas().get(posicion);
		while (caracteristica == null) {
			posicion = rdm.nextInt(caracteristicasLadron.getCaracteristicasFisicas().size());
		}
		return caracteristicasLadron.getCaracteristicasFisicas().get(posicion);
	}

	public String visitar(Policia policia) {
		if (estaLadron()){
			return LADRON_ENCONTRADO;
		} else {
			return policia.getGrado().getPista(this);
		}

	}

}
