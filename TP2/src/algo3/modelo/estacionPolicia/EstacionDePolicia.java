package algo3.modelo.estacionPolicia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import algo3.modelo.ladron.CaracteristicaLadron;

public class EstacionDePolicia {

	private List<CaracteristicaLadron> expedientes;
	private static EstacionDePolicia instance;

	public EstacionDePolicia() {

		List<CaracteristicaLadron> expedientes = new ArrayList<CaracteristicaLadron>();

		CaracteristicaLadron expediente1 = new CaracteristicaLadron("Nick Brunch", "Masculino", "Mountain Climbing", "Negro", "Anillo", "Motocicleta");
		CaracteristicaLadron expediente2 = new CaracteristicaLadron("Len Bulk", "Masculino", "Mountain Climbing", "Rojo", "Tatuaje", "Motocicleta");
		CaracteristicaLadron expediente3 = new CaracteristicaLadron("Ihor Ihorovitch", "Masculino", "Croquet", "Rubio", "Tatuaje", "Limousina");

		expedientes.add(expediente1);
		expedientes.add(expediente2);
		expedientes.add(expediente3);
		this.expedientes = expedientes;
	}

	public static EstacionDePolicia getInstance() {
		if (instance == null) {
			instance = new EstacionDePolicia();
		}
		return instance;
	}

	public List<CaracteristicaLadron> buscarExpediente(CaracteristicaLadron caracteristicaLadron) {

		List<String> caracteristicasSolicitadas = caracteristicaLadron.getCaracteristicas();
		List<CaracteristicaLadron> coincidenciaLadrones = new ArrayList<CaracteristicaLadron>();
		List<String> caracteristicasExpediente;
		boolean contiene = true;

		for (CaracteristicaLadron expediente : this.expedientes) {
			caracteristicasExpediente = expediente.getCaracteristicas();

			Iterator<String> iter = caracteristicasSolicitadas.iterator();
			while (iter.hasNext() && contiene) {
				String caracteristica = iter.next();
				if (caracteristica != null) {
					contiene = caracteristicasExpediente.contains(caracteristica);
				}

			}
			if (contiene) {

				coincidenciaLadrones.add(expediente);
			}

		}
		return coincidenciaLadrones;

	}
}
