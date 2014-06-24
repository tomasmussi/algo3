package algo3.modelo.edificio;

import java.util.Random;

import algo3.modelo.entidad.Bandera;
import algo3.modelo.entidad.Entidad;
import algo3.modelo.entidad.EntidadCultural;
import algo3.modelo.entidad.Moneda;
import algo3.modelo.ladron.Ladron;
import algo3.modelo.mapa.InformacionCultural;
import algo3.modelo.mapa.InformacionDeViaje;
import algo3.modelo.mapa.InformacionFinanciera;

public class EdificioFactory {

	public static Edificio crearEdificioDeViajeConEntidad(InformacionDeViaje infoCiudad) {
		Entidad bandera = new Bandera(infoCiudad.getInformacionViaje());
		return getEdificioDeViajeRandom(bandera);
	}

	public static Edificio crearEdificioFinancieroConEntidad(InformacionFinanciera infoCiudad) {
		Entidad moneda = new Moneda(infoCiudad.getInformacionFinanciera());
		return getEdificioFinancieroRandom(moneda);
	}

	public static Edificio crearEdificioCulturalConEntidad(InformacionCultural infoCiudad) {
		String[] infoHistorica = infoCiudad.getInformacionCultural().split("\\|");
		Entidad algoCultural = new EntidadCultural(infoHistorica[0], infoHistorica[1]);
		return new Embajada(null, algoCultural);
	}

	public static Edificio crearEdificioCulturalConEntidad(Ladron ladron, InformacionCultural infoCiudad) {
		String[] infoHistorica = infoCiudad.getInformacionCultural().split("\\|");
		Entidad algoCultural = new EntidadCultural(infoHistorica[0], infoHistorica[1]);
		return new Embajada(ladron.getCaracteristicasLadron(), algoCultural);
	}

	private static Edificio getEdificioDeViajeRandom(Entidad bandera) {
		Random rdm = new Random();
		Edificio edificio;
		int edificioNro = rdm.nextInt(2); // Esto devuelve 0 o 1.
		if (0 == edificioNro) {
			edificio = new Aeropuerto(null, bandera);
		} else {
			edificio = new Puerto(null, bandera);
		}
		return edificio;
	}

	private static Edificio getEdificioFinancieroRandom(Entidad moneda) {
		Random rdm = new Random();
		Edificio edificio;
		int edificioNro = rdm.nextInt(2); // Esto devuelve 0 o 1.
		if (0 == edificioNro) {
			edificio = new Banco(null, moneda);
		} else {
			edificio = new Bolsa(null, moneda);
		}
		return edificio;
	}

	public static Edificio[] crearEdificiosSinInformacion() {
		Edificio[] edificios = new Edificio[3];
		edificios[0] = new Aeropuerto(null, null);
		edificios[1] = new Banco(null, null);
		edificios[2] = new Aeropuerto(null, null);
		return edificios;
	}

}
