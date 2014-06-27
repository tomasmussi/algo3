package algo3.controlador;

import java.io.IOException;

import algo3.modelo.excepcion.CiudadNoEncontradaException;

public class Logger {

	public static void loguearWarning(IOException e) {
		//TODO: Donde loguear??
	}

	public static void loguearError(IOException e) {
		//TODO: Donde loguear??
	}

	public static void loguearError(String string, IOException e) {
		// TODO Auto-generated method stub

	}

	public static void loguearError(String string) {
		// TODO Auto-generated method stub

	}

	public static void loguearError(CiudadNoEncontradaException e) {
		System.out.println("FALLO " + e.getMessage());

	}

	public static void loguearError(Exception e) {

	}

}
