package algo3.modelo.mapa.mundi;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.edificio.EdificioFactory;

public class CiudadFactory {

	/**
	 * Crea una ciudad a partir de un nombre. Ver NombresCiudad para informacion de nombres.
	 * @return Ciudad.
	 */
	public static Ciudad crearCiudadSinEdificios(String nombreCiudad) {
		InformacionCiudad infoCiudad = InformacionCiudadProvider.getInstance().getInformacionPara(nombreCiudad);
		return new Ciudad(infoCiudad);
	}

	/**
	 * Crea una ciudad a partir de un nombre y le agrega informacion a sus edificios de de la siguiente ciudad.
	 * Ver NombresCiudad para informacion de nombres.
	 * @return Ciudad.
	 */
	public static Ciudad crearCiudadConEdificiosSiguienteCiudad(String nombreCiudad, String nombreSiguienteCiudad) {
		InformacionCiudad infoCiudad = InformacionCiudadProvider.getInstance().getInformacionPara(nombreCiudad);
		InformacionCiudad infoSiguienteCiudad = InformacionCiudadProvider.getInstance().getInformacionPara(nombreSiguienteCiudad);

		Edificio edificio1 = EdificioFactory.crearEdificioCulturalConEntidad(infoSiguienteCiudad);
		Edificio edificio2 = EdificioFactory.crearEdificioFinancieroConEntidad(infoSiguienteCiudad);
		Edificio edificio3 = EdificioFactory.crearEdificioDeViajeConEntidad(infoSiguienteCiudad);
		// TODO: meter coordenadas en el properties/informacion.
		return new Ciudad(0, 0, edificio1, edificio2, edificio3, infoCiudad);
	}
}