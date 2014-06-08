package algo3.modelo.policia;

import algo3.modelo.ladron.CaracteristicaLadron;

public class OrdenDeArresto {

	private CaracteristicaLadron caracteristicaLadron;

	public OrdenDeArresto() {
		super();
	}

	public OrdenDeArresto(CaracteristicaLadron caracteristica) {
		this.caracteristicaLadron = caracteristica;
	}

	public CaracteristicaLadron getCaracteristicaLadron() {
		return caracteristicaLadron;
	}

	public void setCaracteristicaLadron(CaracteristicaLadron caracteristicaLadron) {
		this.caracteristicaLadron = caracteristicaLadron;
	}

}
