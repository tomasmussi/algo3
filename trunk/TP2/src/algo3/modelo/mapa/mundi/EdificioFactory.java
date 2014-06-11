package algo3.modelo.mapa.mundi;

public class EdificioFactory {

	public static Edificio crearEdificioFinanciero(InformacionFinanciera infoCiudad) {
		return new Banco(infoCiudad.getMoneda());
	}

	public static Edificio crearEdificioCultural(InformacionCultural infoCiudad) {		
		return new Embajada(infoCiudad.getGobierno());
	}

	public static Edificio crearEdificioDeViaje(InformacionDeViaje infoCiudad) {
		return new Aeropuerto(infoCiudad.getColoresBandera());
	}
	
	


}
