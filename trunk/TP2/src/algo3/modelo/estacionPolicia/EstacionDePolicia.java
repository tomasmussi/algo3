package algo3.modelo.estacionPolicia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import algo3.controlador.XMLParser;
import algo3.modelo.ladron.CaracteristicaLadron;

public class EstacionDePolicia {

	private List<CaracteristicaLadron> expedientes;
	private static EstacionDePolicia instance;

	private EstacionDePolicia() {
		//TODO (TOMAS) Deberia leer cada vez que me consulten un expediente, o Caso me deberia indicar que tengo que leer de vuelta configuracion
		//(FLAVIA) Masuh dijo que era discutible, as� que le pregunto a el despues, me parece demas hacer eso por las dudas le consulto.
		cargarExpedientes();
	}

	private void cargarExpedientes() {
		expedientes = XMLParser.cargarExpedientes();
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
		for (CaracteristicaLadron expediente : expedientes) {
			caracteristicasExpediente = expediente.getCaracteristicas();

			Iterator<String> iter = caracteristicasSolicitadas.iterator();
			boolean contiene = true;
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
