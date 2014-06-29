package algo3.controlador;

import java.io.IOException;

import algo3.modelo.excepcion.CiudadNoEncontradaException;

public class Logger {

	public static void loguearWarning(IOException e) {
		System.out.println("FALLO " + e.getMessage());
	}

	public static void loguearError(IOException e) {
		System.out.println("FALLO " + e.getMessage());
	}

	public static void loguearError(String string, IOException e) {
		System.out.println("FALLO " + e.getMessage());
	}

	public static void loguearError(String string) {
		System.out.println("FALLO " + string);
	}

	public static void loguearError(CiudadNoEncontradaException e) {
		System.out.println("FALLO " + e.getMessage());

	}

	public static void loguearError(Exception e) {
		System.out.println("FALLO " + e.getMessage());
	}

}
