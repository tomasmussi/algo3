package algo3.modelo.edificio;

import algo3.modelo.entidad.Bandera;
import algo3.modelo.entidad.Entidad;
import algo3.modelo.entidad.EntidadCultural;
import algo3.modelo.entidad.Gobierno;
import algo3.modelo.entidad.Moneda;
import algo3.modelo.mapa.mundi.InformacionCiudad;
import algo3.modelo.mapa.mundi.InformacionCultural;
import algo3.modelo.mapa.mundi.InformacionDeViaje;
import algo3.modelo.mapa.mundi.InformacionFinanciera;

public class EdificioFactory {

	public static Edificio crearEdificioDeViajeConEntidad(InformacionDeViaje infoCiudad) {
		Entidad bandera = new Bandera(infoCiudad.getInformacionViaje());
		return new Aeropuerto(bandera);
	}

	public static Edificio crearEdificioFinancieroConEntidad(InformacionFinanciera infoCiudad) {
		Entidad moneda = new Moneda(infoCiudad.getInformacionFinanciera());
		return new Banco(moneda);
	}

	public static Edificio crearEdificioCulturalConEntidad(InformacionCultural infoCiudad) {
		String[] infoHistorica = infoCiudad.getInformacionHistorica().split("\\|");
		Entidad algoCultural = new EntidadCultural(infoHistorica[0], infoHistorica[1]);
		return new Embajada(algoCultural);
	}

}
