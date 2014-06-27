package algo3.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import algo3.modelo.ladron.CaracteristicaLadron;
import algo3.modelo.mapa.InformacionCiudad;
import algo3.modelo.objeto.CaracteristicaObjeto;
import algo3.modelo.policia.Policia;

import com.thoughtworks.xstream.XStream;

public class XMLParser {

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

	public static boolean guardarPolicia(Policia policia) {
		try {
			FileOutputStream out = new FileOutputStream(new File("C:\\Users\\EM\\Desktop\\objet.xml"));
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

}
