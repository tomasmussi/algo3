package algo3.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import algo3.controlador.Juego;
import algo3.modelo.policia.Policia;

public class VistaConsola implements Vista, Observer {


	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			System.out.println("Error tratando de generar un buffered Reader a partir del standard ind");
		}
		String linea;
		Policia policia = new Policia();
		Juego juego = new Juego(policia);
		try {
			while (!(linea = reader.readLine()).equalsIgnoreCase("fin")){
				if (linea.contains("ciudades_posibles")){
					System.out.println(juego.getCiudadesPosibles());
				} else if (linea.contains("emitir_orden")){

				} else if (linea.contains("viajar")){
					juego.viajar(linea.split(";")[1]);
				} else if (linea.contains("entrar")){
					String edificioNumero = linea.split(" ")[1];
					System.out.println(juego.buscarPista(edificioNumero));
				} else if (linea.contains("iniciar_caso")){
					juego.iniciar();
				} else if (linea.contains("ciudad_actual")){
					System.out.println(juego.ciudadActual());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Juego juego;

	public VistaConsola(Juego juego){
		this.juego = juego;
	}


	public void inicializarVista(){

	}


	@Override
	public void update(Observable o, Object arg) {

	}



}
