package algo3.modelo.policia;

import algo3.modelo.ladron.CaracteristicaLadron;

public class OrdenDeArresto {

	private CaracteristicaLadron caracteristicaLadron;

	/**
	 * Crea una orden de arresto de acuerdo a las caracteristicas del ladron
	 * */
	public OrdenDeArresto(CaracteristicaLadron caracteristica) {
		this.caracteristicaLadron = caracteristica;
	}

	public CaracteristicaLadron getCaracteristicaLadron() {
		return caracteristicaLadron;
	}


}
