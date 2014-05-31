package algo3.controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import algo3.modelo.caracteres.policia.Policia;

import com.thoughtworks.xstream.XStream;

public class XMLCodec {

	public static void main(String[] args) throws IOException {
		Policia p = new Policia();
		XMLCodec.encode(p);
	}


	private static final XStream xmlReader = new XStream();


	public static void encode(Object objeto) throws IOException{
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Tomas\\Desktop\\objeto.xml"));
		xmlReader.toXML(objeto, out);
		out.close();
	}

	public static Object decode(){
		return null;
	}

}
