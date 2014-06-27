package algo3.modelo.mapa;

import java.io.IOException;
import java.util.Properties;

import algo3.controlador.Logger;

public class InformacionCiudadProvider {


	private static final String SEPARADOR = ";";
	private static final String SEPARADOR_SECUNDARIO = ",";
	private static InformacionCiudadProvider instance;
	private Properties prop;

	private InformacionCiudadProvider() {
		super();
		cargarInformacion();
	}

	public static InformacionCiudadProvider getInstance() {
		if (instance == null) {
			instance = new InformacionCiudadProvider();
		}
		return instance;
	}

	/**
	 * Crea la informacion a partir de un archivo properties con toda las informacion de las ciudades.
	 */
	private void cargarInformacion() {
		try {
			prop = new Properties();
			prop.load(Properties.class.getResourceAsStream("/ciudades.properties"));
		} catch (IOException e) {
			Logger.loguearError("Error cargando informacion de ciudades desde archivo de propiedades." + e.toString());
		}
	}

	/**
	 * Devuelve una InformacionCiudad a partir del nombre de una ciudad. Ver NombresCiudad por nombre correto.
	 * 
	 * @returns una InformacionCiudad.
	 */
	public InformacionCiudad getInformacionPara(String nombreCiudad) {
		String propiedades = prop.getProperty(nombreCiudad);
		if (propiedades == null) {
			return null;
		}
		return crearInformacionCiudad(nombreCiudad, propiedades);
	}

	private InformacionCiudad crearInformacionCiudad(String nombreCiudad, String propiedades) {
		InformacionCiudad informacionCiudad = new InformacionCiudad();
		String[] infoPropiedades = propiedades.split(SEPARADOR);
		int i = 0;
		informacionCiudad.setNombreCiudad(nombreCiudad);
		informacionCiudad.setColoresBandera(infoPropiedades[i++]);
		informacionCiudad.setMoneda(infoPropiedades[i++]);
		informacionCiudad.setGeografia(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setLugaresDeReferencia(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setIndustria(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setAnimales(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setGente(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setIdiomas(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setArte(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setReligion(infoPropiedades[i++].split(SEPARADOR_SECUNDARIO));
		informacionCiudad.setGobierno(infoPropiedades[i++]);
		informacionCiudad.setLatitud(infoPropiedades[i++]);
		informacionCiudad.setLongitud(infoPropiedades[i++]);
		return informacionCiudad;
	}

}