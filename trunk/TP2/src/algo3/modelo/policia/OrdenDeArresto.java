package algo3.modelo.policia;

import algo3.modelo.ladron.CaracteristicaLadron;

public class OrdenDeArresto {

	//TODO No me gusta esto.... o sea, la clase OrdenDeArresto esta vacia y tiene un getter unicamente

	private CaracteristicaLadron caracteristicaLadron;

	public OrdenDeArresto(CaracteristicaLadron caracteristica) {
		this.caracteristicaLadron = caracteristica;
	}

	public CaracteristicaLadron getCaracteristicaLadron() {
		return caracteristicaLadron;
	}


}
