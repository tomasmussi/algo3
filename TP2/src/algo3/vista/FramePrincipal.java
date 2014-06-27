package algo3.vista;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePrincipal extends JFrame {

	private JFrame ventanaJuego;
	private JFrame ingresarInformacion;
	private JPanel imagenPrincipal;

	public FramePrincipal() {
		ventanaJuego = new VentanaDeJuego();
		ventanaJuego.setVisible(false);

		ingresarInformacion = new VentanaIngresarNombre(this);
		ingresarInformacion.setVisible(false);

		JButton btnCargarPartida = new JButton("Cargar Partida");
		getContentPane().add(btnCargarPartida, BorderLayout.EAST);

		JButton btnEmpezarJuego = new JButton("Empezar Juego");
		btnEmpezarJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaIngresoNombre();
			}
		});
		getContentPane().add(btnEmpezarJuego, BorderLayout.WEST);

		imagenPrincipal = new JPanelPrincipal();
		getContentPane().add(imagenPrincipal, BorderLayout.CENTER);
	}

	protected void mostrarVentanaIngresoNombre() {
		ingresarInformacion.setVisible(true);
	}

	class JPanelPrincipal extends JPanel {

		private static final long serialVersionUID = 1174752589700819501L;

		Image bgimage = null;

		JPanelPrincipal() {
			MediaTracker mt = new MediaTracker(this);
			bgimage = Toolkit.getDefaultToolkit().getImage("carmen-sandiego.jpg");
			mt.addImage(bgimage, 0);
			try {
				mt.waitForAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgimage, 1, 1, null);
		}
	}
}
