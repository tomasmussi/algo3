package algo3.modelo.policia;

import algo3.modelo.ladron.CaracteristicaLadron;

public class OrdenDeArresto {

	private CaracteristicaLadron caracteristicaLadron;

	public OrdenDeArresto(CaracteristicaLadron caracteristica) {
		this.caracteristicaLadron = caracteristica;
	}

	public CaracteristicaLadron getCaracteristicaLadron() {
		return caracteristicaLadron;
	}


}
