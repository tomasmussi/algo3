package algo3.modelo.mapa;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.edificio.EdificioFactory;
import algo3.modelo.excepcion.CiudadNoEncontradaException;

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
	private static Ciudad crearCiudadConEdificiosSiguienteCiudad(InformacionCiudad origen, InformacionCiudad destino) {
		Edificio edificio1 = EdificioFactory.crearEdificioCulturalConEntidad(destino);
		Edificio edificio2 = EdificioFactory.crearEdificioFinancieroConEntidad(destino);
		Edificio edificio3 = EdificioFactory.crearEdificioDeViajeConEntidad(destino);
		return new Ciudad(edificio1, edificio2, edificio3, origen);
	}


	/**
	 * Crea una ciudad a partir de un nombre y le agrega informacion a sus edificios de de la siguiente ciudad.
	 * Ver NombresCiudad para informacion de nombres.
	 * @return Ciudad.
	 * @throws CiudadNoEncontradaException
	 */
	private static Ciudad crearCiudadConEdificiosSiguienteCiudad(String origen, String destino) throws CiudadNoEncontradaException {
		InformacionCiudad infoOrigen = InformacionMapa.getInstance().getCiudadDeNombre(origen);
		InformacionCiudad infoDestino = InformacionMapa.getInstance().getCiudadDeNombre(destino);
		return CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(infoOrigen, infoDestino);
	}

	public static Ciudad crearCiudadComun(InformacionCiudad informacionCiudad) {
		Edificio[] edificios = EdificioFactory.crearEdificiosSinInformacion();
		return new Ciudad(edificios[0],edificios[1],edificios[2],informacionCiudad);
	}


	private static Ciudad crearCiudadFinalRecorrido(InformacionCiudad informacionCiudad) {
		// TODO Hacer Ciudad posta, hablar con Nico
		return CiudadFactory.crearCiudadComun(informacionCiudad);
	}

	/**
	 * Crea un recorrido de ciudades
	 * Genera a partir del nombre de una ciudad de origen y una cantidad de ciudades,
	 * un recorrido por el cual el ladron se escapara.
	 * Ademas genera las conexiones necesarias entre ciudades para que cada edificio de
	 * cada ciudad de informacion sobre la siguiente ciudad en el recorrido.
	 * @throws CiudadNoEncontradaException
	 * 
	 */
	public static List<Ciudad> crearRecorridoDeCiudades(String ciudadOrigen, int cantidadCiudades) throws CiudadNoEncontradaException {
		List<InformacionCiudad> ciudades = InformacionMapa.getInstance().getListadoCiudades();
		desordenarCiudades(ciudades);
		List<Ciudad> recorrido = new ArrayList<Ciudad>();

		Ciudad origen = CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(ciudadOrigen, ciudades.get(0).getNombreCiudad());
		recorrido.add(origen);
		int i;
		for (i = 0; i < cantidadCiudades - 1; i++){
			InformacionCiudad anterior = ciudades.get(i);
			InformacionCiudad siguiente = ciudades.get(i+1);
			recorrido.add(CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(anterior, siguiente));
		}
		recorrido.add(CiudadFactory.crearCiudadFinalRecorrido(ciudades.get(i)));
		return recorrido;
	}


	private static void desordenarCiudades(List<InformacionCiudad> ciudades){
		for (int i = 0; i < ciudades.size() - 1; i++){
			InformacionCiudad ciudad = ciudades.get(i);
			int posicion = (int) ((Math.random() * ciudades.size()) % ciudades.size());
			InformacionCiudad otra = ciudades.get(posicion);
			ciudades.set(i, otra);
			ciudades.set(posicion, ciudad);
		}
	}
}