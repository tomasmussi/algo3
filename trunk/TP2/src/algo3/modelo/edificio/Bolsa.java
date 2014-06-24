package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;
import algo3.modelo.ladron.CaracteristicaLadron;

public class Bolsa extends Edificio {

	private Entidad moneda;

	public Bolsa(CaracteristicaLadron caracteristica, Entidad entidad) {
		super(caracteristica);
		this.moneda = entidad;
	}

	@Override
	public Entidad getElemento() {
		return moneda;
	}

}
