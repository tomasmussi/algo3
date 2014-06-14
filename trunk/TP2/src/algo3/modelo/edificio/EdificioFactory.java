package algo3.modelo.edificio;

import algo3.modelo.entidad.Bandera;
import algo3.modelo.entidad.Entidad;
import algo3.modelo.entidad.Gobierno;
import algo3.modelo.entidad.Moneda;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.InformacionCultural;
import algo3.modelo.mapa.mundi.InformacionDeViaje;
import algo3.modelo.mapa.mundi.InformacionFinanciera;

public class EdificioFactory {

	public static Edificio crearEdificioFinanciero(InformacionFinanciera infoCiudad) {
		return new Banco(infoCiudad.getInformacionFinanciera());
	}

	public static Edificio crearEdificioCultural(InformacionCultural infoCiudad) {
		return new Embajada(infoCiudad.getInformacionHistorica());
	}

	public static Edificio crearEdificioDeViaje(InformacionDeViaje infoCiudad) {
		return new Aeropuerto(infoCiudad.getInformacionViaje());
	}

	public static Edificio crearEdificioDeViajeConEntidad(InformacionDeViaje infoCiudad) {
		Entidad bandera = new Bandera(infoCiudad.getInformacionViaje());
		return new Aeropuerto(bandera);
	}

	public static Edificio crearEdificioFinancieroConEntidad(InformacionCiudad infoCiudad) {
		Entidad moneda = new Moneda(infoCiudad.getInformacionFinanciera());
		return new Banco(moneda);
	}

	public static Edificio crearEdificioCulturalConEntidad(InformacionCiudad infoCiudad) {
		Entidad algoCultural = new Gobierno(infoCiudad.getInformacionHistorica());
		return new Embajada(algoCultural);
	}

}
