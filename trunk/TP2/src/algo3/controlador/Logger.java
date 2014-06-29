package algo3.controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import algo3.modelo.excepcion.CiudadNoEncontradaException;

public class Logger {

	private static String PATH;
	private static final String LOG = "log.txt";
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static BufferedWriter out;

	public static void loguearWarning(IOException e) {
		try {
			getOutputStream().write("Waringn: " + e.getMessage());
			getOutputStream().write("\n");
			getOutputStream().flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void loguearError(IOException e) {
		try {
			getOutputStream().write("FALLO " + e.getMessage());
			getOutputStream().write("\n");
			getOutputStream().flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void loguearError(String string, IOException e) {
		try {
			getOutputStream().write("FALLO " + e.getMessage());
			getOutputStream().write("\n");
			getOutputStream().flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void loguearError(String string) {
		try {
			getOutputStream().write("FALLO " + string);
			getOutputStream().write("\n");
			getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loguearError(CiudadNoEncontradaException e) {
		try {
			getOutputStream().write("FALLO " + e.getMessage());
			getOutputStream().write("\n");
			getOutputStream().flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public static void loguearError(Exception e) {
		try {
			getOutputStream().write("FALLO " + e.getMessage());
			getOutputStream().write("\n");
			getOutputStream().flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void setPath(String path) {
		PATH = path;
	}

	private static BufferedWriter getOutputStream(){
		if (out == null){
			try {
				out = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(new File(PATH + FILE_SEP + LOG))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return out;
	}



}
