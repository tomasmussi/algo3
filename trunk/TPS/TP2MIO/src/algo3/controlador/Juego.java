package algo3.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import algo3.modelo.ladron.CaracteristicaLadron;

public class Juego {


	public static void main(String[] args) {

		Juego juego = new Juego();
		if (!juego.leerConfiguracion(args)){
			return;
		}
		//TODO controlar el juego

	}

	public Juego(){

	}



	private boolean leerConfiguracion(String[] args) {
		try {
			InputStream in = null;
			if (args.length > 0){
				in = new FileInputStream(new File(args[0]));
			}
			List<CaracteristicaLadron> expedientes = (List<CaracteristicaLadron>) XMLParser.decode(in);
		} catch (FileNotFoundException e) {
			Logger.loguearError(e);
			return false;
		} catch (IOException e){
			Logger.loguearError(e);
			return false;
		}
		return true;
	}



}
