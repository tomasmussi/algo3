package algo3.modelo.juego;

import algo3.controlador.XMLParser;
import algo3.vista.FramePrincipal;

public class Aplicacion {

	public static void main(String args[]) {
		String path = System.getProperty("user.home");
		if (args.length >= 1){
			path = args[0];
		}
		XMLParser.setPath(path);
		FramePrincipal ventana = new FramePrincipal(path);
		ventana.setVisible(true);
		ventana.setExtendedState(FramePrincipal.MAXIMIZED_BOTH);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(FramePrincipal.EXIT_ON_CLOSE);
	}

}
