package algo3.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.pista.Pista;
import algo3.modelo.policia.Policia;

import com.thoughtworks.xstream.XStream;

@SuppressWarnings("unchecked")
public class XMLParser {

	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DEFAULT = System.getProperty("user.home");
	private static final String EXTENSION_XML = ".xml";
	private static String PATH;
	private static final String LADRONES = "ladrones";
	private static final String CIUDADES = "ciudades";
	private static final String OBJETOS = "objetos";
	private static final String PISTAS = "pistas";

	private static final XStream xmlStream = new XStream();




	public static Map<String, Set<String>> cargarCaracteristicasExpedientes() {
		Iterator<CaracteristicaLadron> caracteristicas = cargarExpedientes().iterator();
		Map<String, Set<String>> caracteristicasPorTipo = new HashMap<String, Set<String>>();
		while (caracteristicas.hasNext()){
			CaracteristicaLadron siguiente = caracteristicas.next();
			Iterator<Entry<String, String>> iter = siguiente.getCaracteristicasPorTipo().entrySet().iterator();
			while (iter.hasNext()){
				Entry<String, String> entry = iter.next();
				if (caracteristicasPorTipo.containsKey(entry.getKey())){
					caracteristicasPorTipo.get(entry.getKey()).add(entry.getValue());
				} else {
					Set<String> tipoCaracteristica = new HashSet<String>();
					tipoCaracteristica.add(entry.getValue());
					caracteristicasPorTipo.put(entry.getKey(), tipoCaracteristica);
				}
			}
		}
		return caracteristicasPorTipo;
	}

	public static List<CaracteristicaLadron> cargarExpedientesDefault() {
		try {
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/estacionPolicia/ladrones.xml");
			List<CaracteristicaLadron> expedientes = (List<CaracteristicaLadron>) xmlStream.fromXML(in);

			in.close();
			return expedientes;
		} catch (IOException e) {
			Logger.loguearError(e);
		}
		return null;
	}

	public static List<CaracteristicaLadron> cargarExpedientes() {
		try {
			InputStream in = new FileInputStream(new File(PATH + FILE_SEP + LADRONES +EXTENSION_XML));
			List<CaracteristicaLadron> expedientes = (List<CaracteristicaLadron>) xmlStream.fromXML(in);
			in.close();
			return expedientes;
		} catch (Exception e) {
			Logger.loguearError(e);
		}
		return cargarExpedientesDefault();
	}

	public static List<InformacionCiudad> cargarCiudades() {
		try {
			InputStream in = new FileInputStream(new File(PATH + FILE_SEP + CIUDADES + EXTENSION_XML));
			List<InformacionCiudad> infoCiudades = (List<InformacionCiudad>) xmlStream.fromXML(in);
			in.close();
			return infoCiudades;
		} catch (Exception e) {
			Logger.loguearError(e);
		}
		return cargarCiudadesDefault();
	}

	public static List<InformacionCiudad> cargarCiudadesDefault() {
		try {
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/mapa/ciudades.xml");
			List<InformacionCiudad> infoCiudades = (List<InformacionCiudad>) xmlStream.fromXML(in);

			in.close();
			return infoCiudades;
		} catch (IOException e) {
			Logger.loguearError(e);
		}
		return null;
	}

	public static List<CaracteristicaObjeto> cargarObjetosDefault() {
		try {
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/objeto/objetos.xml");
			List<CaracteristicaObjeto> objetos = (List<CaracteristicaObjeto>) xmlStream.fromXML(in);
			in.close();
			return objetos;
		} catch (IOException e) {
			Logger.loguearError(e);
		}
		return null;
	}

	public static List<CaracteristicaObjeto> cargarObjetos() {
		try {
			InputStream in = new FileInputStream(new File(PATH + FILE_SEP + OBJETOS + EXTENSION_XML));
			List<CaracteristicaObjeto> objetos = (List<CaracteristicaObjeto>) xmlStream.fromXML(in);
			in.close();
			return objetos;
		} catch (Exception e) {
			Logger.loguearError(e);
		}
		return cargarObjetosDefault();
	}

	public static Map<String, Map<String, String>> cargarPistas() {
		Map<String, Map<String, String>> pistas = new HashMap<String, Map<String, String>>();
		try {
			InputStream in = new FileInputStream(new File(PATH + FILE_SEP + PISTAS + EXTENSION_XML));
			List<Pista> pistasDelXML = (List<Pista>) xmlStream.fromXML(in);
			for (Pista pista : pistasDelXML) {
				Map<String, String> pistasPorEntidad = new HashMap<String, String>();
				if (pistas.containsKey(pista.getDificultad())) {
					pistasPorEntidad = pistas.get(pista.getDificultad());
				}
				pistasPorEntidad.put(pista.getEntidad(), pista.getContenido());
				pistas.put(pista.getDificultad(), pistasPorEntidad);
			}
			in.close();
			return pistas;
		} catch (Exception e) {
			Logger.loguearError(e);
		}
		return cargarPistasDefault();
	}



	public static Map<String, Map<String, String>> cargarPistasDefault() {
		Map<String, Map<String, String>> pistas = new HashMap<String, Map<String, String>>();
		try {
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/pista/pistas.xml");
			List<Pista> pistasDelXML = (List<Pista>) xmlStream.fromXML(in);
			for (Pista pista : pistasDelXML) {
				Map<String, String> pistasPorEntidad = new HashMap<String, String>();
				if (pistas.containsKey(pista.getDificultad())) {
					pistasPorEntidad = pistas.get(pista.getDificultad());
				}
				pistasPorEntidad.put(pista.getEntidad(), pista.getContenido());
				pistas.put(pista.getDificultad(), pistasPorEntidad);
			}
			in.close();
		} catch (IOException e) {
			Logger.loguearError(e);
		}
		return pistas;
	}

	public static boolean guardarPolicia(Policia policia, String path) {
		try {
			File archivo = null;
			if (path != null){
				archivo = new File(path + FILE_SEP + policia.getNombre() + EXTENSION_XML);
			} else {
				archivo = new File(DEFAULT + policia.getNombre() + EXTENSION_XML);
			}
			FileOutputStream out = new FileOutputStream(archivo);
			xmlStream.toXML(policia.clone(), out);
		} catch (FileNotFoundException e) {
			Logger.loguearError(e);
			return false;
		}
		return true;
	}

	public static Policia cargarPolicia() {
		Policia policia = null;
		try {
			InputStream in = new FileInputStream(new File("C:\\Users\\EM\\Desktop\\objet.xml"));
			policia = (Policia) xmlStream.fromXML(in);
		} catch (Exception e) {
			Logger.loguearError(e);
			policia = new Policia();
		}
		return policia;
	}

	public static Policia cargarPoliciaDeArchivo(String path, String nombreJugador){
		//Busco primero si existe el jugador
		Policia policia = null;
		try {
			String archivo = path + FILE_SEP + nombreJugador + EXTENSION_XML;
			File registro = new File(archivo);
			if (registro.exists()){
				policia = (Policia) xmlStream.fromXML(registro);
			} else {
				policia = new Policia(nombreJugador);
			}
		} catch (Exception e) {
			Logger.loguearError(e);
			policia = new Policia(nombreJugador);
		}
		return policia;
	}


	public static void setPath(String path) {
		PATH = path;
	}


}
