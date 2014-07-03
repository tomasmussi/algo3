package algo3.modelo.mapa;

import java.util.ArrayList;
import java.util.List;

import algo3.modelo.edificio.Edificio;
import algo3.modelo.edificio.EdificioFactory;
import algo3.modelo.excepcion.CiudadNoEncontradaException;
import algo3.modelo.ladron.CaracteristicaLadron;

public class CiudadFactory {

	/**
	 * Crea una ciudad a partir de un nombre y le agrega informacion a sus edificios de de la siguiente ciudad.
	 * Ver NombresCiudad para informacion de nombres.
	 * @return Ciudad.
	 */
	public static Ciudad crearCiudadConEdificiosSiguienteCiudad(InformacionCiudad origen, InformacionCiudad destino) {
		return crearCiudadConEdificiosSiguienteCiudad(null, origen, destino);
	}

	/**
	 * Crea una ciudad con informacion en sus edificios de la siguiente ciudad
	 * @return ciudad con los edificios correctos
	 * */
	public static Ciudad crearCiudadConEdificiosSiguienteCiudad(CaracteristicaLadron caracteristicas,
			InformacionCiudad origen, InformacionCiudad destino) {
		Edificio edificio1 = EdificioFactory.crearEdificioCulturalConEntidad(caracteristicas, destino);
		Edificio edificio2 = EdificioFactory.crearEdificioFinancieroConEntidad(caracteristicas, destino);
		Edificio edificio3 = EdificioFactory.crearEdificioDeViajeConEntidad(caracteristicas, destino);
		return new Ciudad(edificio1, edificio2, edificio3, origen);
	}


	/**
	 * Crea una ciudad a partir de un nombre y le agrega informacion a sus edificios de de la siguiente ciudad.
	 * Ver NombresCiudad para informacion de nombres.
	 * @return Ciudad.
	 * @throws CiudadNoEncontradaException
	 */
	public static Ciudad crearCiudadConEdificiosSiguienteCiudad(CaracteristicaLadron caracteristicas, String origen, String destino) throws CiudadNoEncontradaException {
		InformacionCiudad infoOrigen = InformacionMapa.getInstance().getCiudadDeNombre(origen);
		InformacionCiudad infoDestino = InformacionMapa.getInstance().getCiudadDeNombre(destino);
		return CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(caracteristicas, infoOrigen, infoDestino);
	}

	public static Ciudad crearCiudadComun(InformacionCiudad informacionCiudad) {
		Edificio[] edificios = EdificioFactory.crearEdificiosSinInformacion();
		return new Ciudad(edificios[0],edificios[1],edificios[2],informacionCiudad);
	}


	private static Ciudad crearCiudadFinalRecorrido(InformacionCiudad informacionCiudad) {
		Edificio[] edificios = EdificioFactory.crearEdificioFinalDeCiudad();
		return new Ciudad(edificios[0],edificios[1],edificios[2],informacionCiudad);
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
	public static List<Ciudad> crearRecorridoDeCiudades(CaracteristicaLadron caracteristicas,String ciudadOrigen,
			int cantidadCiudades) throws CiudadNoEncontradaException {
		List<InformacionCiudad> ciudades = InformacionMapa.getInstance().getListadoCiudades();
		desordenarCiudades(ciudades);
		List<Ciudad> recorrido = new ArrayList<Ciudad>();

		Ciudad origen = CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(caracteristicas, ciudadOrigen, ciudades.get(0).getNombreCiudad());
		recorrido.add(origen);
		ciudades.remove(InformacionMapa.getInstance().getCiudadDeNombre(ciudadOrigen));
		int i;
		for (i = 0; i < cantidadCiudades - 1; i++){
			InformacionCiudad anterior = ciudades.get(i);
			InformacionCiudad siguiente = ciudades.get(i+1);
			recorrido.add(CiudadFactory.crearCiudadConEdificiosSiguienteCiudad(caracteristicas, anterior, siguiente));
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