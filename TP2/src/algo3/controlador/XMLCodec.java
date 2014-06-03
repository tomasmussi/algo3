package algo3.controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import algo3.modelo.ladron.Ladron;

import com.thoughtworks.xstream.XStream;

public class XMLCodec {

	public static void main(String[] args) throws IOException {
		leerLadrones();
	}

	private static final XStream xmlReader = new XStream();

	private static void leerLadrones() throws IOException {
		InputStream in = Properties.class.getResourceAsStream("/algo3/controlador/ladrones.xml");
		List<Ladron> ladrones = (List<Ladron>)xmlReader.fromXML(in);
		in.close();
		Iterator<Ladron> it = ladrones.iterator();
		while (it.hasNext()){
			Ladron ladron = it.next();
			System.out.println(ladron);
		}
	}

	private static void leerPaises() throws IOException {
		//		InputStream in = Properties.class.getResourceAsStream("/algo3/controlador/paises.xml");
		//		List<Pais> ladrones = (List<Pais>)xmlReader.fromXML(in);
		//		in.close();
		//		Iterator<Ladron> it = ladrones.iterator();
		//		while (it.hasNext()){
		//			Ladron ladron = it.next();
		//			System.out.println(ladron);
		//		}
	}

	public static void encode(Object objeto) throws IOException{
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Tomas\\Desktop\\objeto.xml"));
		xmlReader.toXML(objeto, out);
		out.close();
	}

	public static Object decode() throws IOException {
		InputStream in = Properties.class.getResourceAsStream("/algo3/controlador/ladrones.xml");
		return xmlReader.fromXML(in);
	}

}
