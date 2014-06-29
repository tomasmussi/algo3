package algo3.modelo.juego;

import algo3.vista.FramePrincipal;

public class Aplicacion {

	public static void main(String args[]) {
		FramePrincipal ventana = new FramePrincipal();
		ventana.setPath(args[0]);
		ventana.setVisible(true);
		ventana.setExtendedState(FramePrincipal.MAXIMIZED_BOTH);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(FramePrincipal.EXIT_ON_CLOSE);
	}

}
