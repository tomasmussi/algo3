package algo3.modelo.edificio;

import algo3.modelo.entidad.Entidad;
import algo3.modelo.ladron.CaracteristicaLadron;

public class Aeropuerto extends Edificio {

	private Entidad bandera;

	public Aeropuerto(CaracteristicaLadron caracteristica, Entidad entidad) {
		super(caracteristica);
		this.bandera = entidad;
	}

	@Override
	public Entidad getElemento() {
		return bandera;
	}

}
