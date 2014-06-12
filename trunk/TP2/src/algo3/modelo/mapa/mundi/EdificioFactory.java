package algo3.modelo.mapa.mundi;

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

}
