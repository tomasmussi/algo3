package algo3.controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import algo3.modelo.ladron.CaracteristicaLadron;

import com.thoughtworks.xstream.XStream;

public class XMLParser {

	public static void main(String[] args) throws IOException {
		leerLadrones();
	}

	private static final XStream xmlReader = new XStream();

	@SuppressWarnings("unchecked")
	private static void leerLadrones() throws IOException {
		InputStream in = Properties.class.getResourceAsStream("/algo3/controlador/ladrones.xml");
		List<CaracteristicaLadron> ladrones = (List<CaracteristicaLadron>)xmlReader.fromXML(in);
		in.close();
		Iterator<CaracteristicaLadron> it = ladrones.iterator();
		while (it.hasNext()){
			CaracteristicaLadron ladron = it.next();
			System.out.println(ladron);
		}
	}

	//	private static void leerPaises() throws IOException {
	//		InputStream in = Properties.class.getResourceAsStream("/algo3/controlador/paises.xml");
	//		List<Pais> ladrones = (List<Pais>)xmlReader.fromXML(in);
	//		in.close();
	//		Iterator<Ladron> it = ladrones.iterator();
	//		while (it.hasNext()){
	//			Ladron ladron = it.next();
	//			System.out.println(ladron);
	//		}
	//	}

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

	public static void encode(Object objeto) throws IOException{
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Tomas\\Desktop\\objeto.xml"));
		xmlReader.toXML(objeto, out);
		out.close();
	}

	public static Object decode() throws IOException {
		Object decodificado = null;
		try{
			InputStream in = Properties.class.getResourceAsStream("/algo3/controlador/ladrones.xml");
			decodificado = xmlReader.fromXML(in);
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
			decodificado = xmlReader.fromXML(in);
			if (in != null){
				in.close();
			}
		} catch (IOException e){
			Logger.loguearWarning(e);
			decodificado = XMLParser.decode();
		}
		return decodificado;
	}

}
