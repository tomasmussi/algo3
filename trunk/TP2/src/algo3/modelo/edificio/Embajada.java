package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;
import algo3.modelo.ladron.CaracteristicaLadron;

public class Embajada extends Edificio {

	private Entidad entidad;

	public Embajada(CaracteristicaLadron caracteristica, Entidad entidad) {
		super(caracteristica);
		this.entidad = entidad;
	}

	@Override
	public Entidad getElemento() {
		return entidad;
	}

	@Override
	public String getNombre() {
		return "Embajada";
	}

}
