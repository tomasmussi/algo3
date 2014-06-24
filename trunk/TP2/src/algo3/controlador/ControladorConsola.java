package algo3.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import algo3.modelo.policia.Policia;
import algo3.vista.Vista;
import algo3.vista.VistaConsolaReloj;
import algo3.vista.VistaViaje;

public class ControladorConsola {

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			System.out.println("Error tratando de generar un buffered Reader a partir del standard ind");
		}
		String linea;
		Policia policia = new Policia();

		Vista vistaReloj = new VistaConsolaReloj();
		Vista vistaViaje = new VistaViaje();
		Juego juego = new Juego(policia, vistaReloj);
		juego.setVistaViaje(vistaViaje);
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

}
