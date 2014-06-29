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
import algo3.modelo.policia.Policia;

import com.thoughtworks.xstream.XStream;

public class XMLParser {

	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String DEFAULT = System.getProperty("user.home");
	private static final String EXTENSION_XML = ".xml";
	private static String PATH;

	private static final XStream xmlStream = new XStream();

	public static List<CaracteristicaLadron> cargarExpedientes() {
		try {
			XStream xmlReader = new XStream();
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/estacionPolicia/ladrones.xml");
			List<CaracteristicaLadron> expedientes = (List<CaracteristicaLadron>) xmlReader.fromXML(in);

			in.close();
			return expedientes;
		} catch (IOException e) {
			Logger.loguearError(e);
		}
		return null;
	}


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

	public static List<InformacionCiudad> cargarCiudades() {
		try {
			XStream xmlReader = new XStream();
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/mapa/ciudades.xml");
			List<InformacionCiudad> infoCiudades = (List<InformacionCiudad>) xmlReader.fromXML(in);

			in.close();
			return infoCiudades;
		} catch (IOException e) {
			Logger.loguearError(e);
		}
		return null;
	}

	public static List<CaracteristicaObjeto> cargarObjetos() {
		try {
			XStream xmlReader = new XStream();
			InputStream in = Properties.class.getResourceAsStream("/algo3/modelo/objeto/objetos.xml");
			List<CaracteristicaObjeto> objetos = (List<CaracteristicaObjeto>) xmlReader.fromXML(in);

			in.close();
			return objetos;
		} catch (IOException e) {
			Logger.loguearError(e);
		}
		return null;
	}


	public static void encode(Object objeto) throws IOException{
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Tomas\\Desktop\\objeto.xml"));
		xmlStream.toXML(objeto, out);
		out.close();
	}

	public static Object decode() throws IOException {
		Object decodificado = null;
		try{
			InputStream in = Properties.class.getResourceAsStream("/algo3/controlador/ladrones.xml");
			decodificado = xmlStream.fromXML(in);
			if (in != null){
				in.close();
			}
		} catch (IOException e){
			Logger.loguearError("Error al cargar configuracion por defecto", e);
		}
		return decodificado;
	}


	public static Object decode(InputStream in) throws IOException {
		Object decodificado = null;
		try{
			decodificado = xmlStream.fromXML(in);
			if (in != null){
				in.close();
			}
		} catch (IOException e){
			Logger.loguearWarning(e);
			decodificado = XMLParser.decode();
		}
		return decodificado;
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
