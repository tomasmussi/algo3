package algo3.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 2528876481084656378L;
	
	private JFrame ingresarInformacion;
	private JPanel imagenPrincipal;

	public FramePrincipal() {
		ingresarInformacion = new VentanaIngresarNombre(this);
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		imagenPrincipal = new JPanelPrincipal();
		getContentPane().add(imagenPrincipal, "4, 2, 1, 3, fill, fill");

		JButton btnEmpezarJuego = new JButton("Empezar Juego");
		btnEmpezarJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaIngresoNombre();
			}
		});
		getContentPane().add(btnEmpezarJuego, "2, 2, fill, fill");

		JButton btnCargarPartida = new JButton("Cargar Partida");
		getContentPane().add(btnCargarPartida, "2, 4, fill, fill");

		requestFocusInWindow();
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
