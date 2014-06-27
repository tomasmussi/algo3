package algo3.modelo.edificio;

import java.util.Random;

import algo3.modelo.entidad.Bandera;
import algo3.modelo.entidad.Entidad;
import algo3.modelo.entidad.EntidadCultural;
import algo3.modelo.entidad.EntidadVacia;
import algo3.modelo.entidad.Moneda;
import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.InformacionCultural;
import algo3.modelo.mapa.InformacionDeViaje;
import algo3.modelo.mapa.InformacionFinanciera;

public class EdificioFactory {

	public static Edificio crearEdificioDeViajeConEntidad(InformacionDeViaje infoCiudad) {
		return crearEdificioDeViajeConEntidad(null, infoCiudad);
	}

	public static Edificio crearEdificioDeViajeConEntidad(CaracteristicaLadron caracteristicas, InformacionDeViaje infoCiudad) {
		Entidad bandera = new Bandera(infoCiudad.getInformacionViaje());
		return getEdificioDeViajeRandom(caracteristicas, bandera);
	}

	public static Edificio crearEdificioFinancieroConEntidad(InformacionFinanciera infoCiudad) {
		return crearEdificioFinancieroConEntidad(null, infoCiudad);
	}

	public static Edificio crearEdificioFinancieroConEntidad(CaracteristicaLadron caracteristicas, InformacionFinanciera infoCiudad) {
		Entidad moneda = new Moneda(infoCiudad.getInformacionFinanciera());
		return getEdificioFinancieroRandom(caracteristicas, moneda);
	}

	public static Edificio crearEdificioCulturalConEntidad(InformacionCultural infoCiudad) {
		String[] infoHistorica = infoCiudad.getInformacionCultural().split("\\|");
		Entidad algoCultural = new EntidadCultural(infoHistorica[0], infoHistorica[1]);
		return new Embajada(null, algoCultural);
	}

	public static Edificio crearEdificioCulturalConEntidad(CaracteristicaLadron caracteristicas, InformacionCultural infoCiudad) {
		String[] infoHistorica = infoCiudad.getInformacionCultural().split("\\|");
		Entidad algoCultural = new EntidadCultural(infoHistorica[0], infoHistorica[1]);
		return new Embajada(caracteristicas, algoCultural);
	}

	private static Edificio getEdificioDeViajeRandom(CaracteristicaLadron caracteristicas, Entidad bandera) {
		Random rdm = new Random();
		Edificio edificio;
		int edificioNro = rdm.nextInt(2); // Esto devuelve 0 o 1.
		if (0 == edificioNro) {
			edificio = new Aeropuerto(caracteristicas, bandera);
		} else {
			edificio = new Puerto(caracteristicas, bandera);
		}
		return edificio;
	}

	private static Edificio getEdificioFinancieroRandom(CaracteristicaLadron caracteristicas, Entidad moneda) {
		Random rdm = new Random();
		Edificio edificio;
		int edificioNro = rdm.nextInt(2); // Esto devuelve 0 o 1.
		if (0 == edificioNro) {
			edificio = new Banco(caracteristicas, moneda);
		} else {
			edificio = new Bolsa(caracteristicas, moneda);
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

	public static Edificio[] crearEdificioFinalDeCiudad(){
		Edificio[] edificios = new Edificio[3];
		edificios[0] = getEdificioFinancieroRandom(null, new EntidadVacia());
		edificios[1] = getEdificioDeViajeRandom(null, new EntidadVacia());
		edificios[2] = getEdificioDeViajeRandom(null, new EntidadVacia());
		return edificios;
	}

}
