package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;
import algo3.modelo.ladron.CaracteristicaLadron;

public class Puerto extends Edificio {

	private Entidad entidad;

	public Puerto(CaracteristicaLadron caracteristica, Entidad entidad) {
		super(caracteristica);
		this.entidad = entidad;
	}

	@Override
	public Entidad getElemento() {
		return entidad;
	}

	@Override
	public String getNombre() {
		return "Puerto";
	}

}
