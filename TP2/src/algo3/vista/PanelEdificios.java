package algo3.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import algo3.controlador.ControladorEntrarEdificio;
import algo3.modelo.juego.Juego;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelEdificios extends JPanel {

	private static final long serialVersionUID = -844842521908682167L;
	private static final String EDIFICIOS = "edificios/";
	private static final String EXTENSION_JPG = ".jpg";

	public PanelEdificios(Juego juego, String[] edificios) {
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		setBackground(Color.BLACK);

		ImagenPanel edificio1 = new ImagenPanel(edificios[0]);
		edificio1.setToolTipText(edificios[0]);
		edificio1.addMouseListener(new ControladorEntrarEdificio(juego, 0));
		add(edificio1, "2, 2, fill, fill");

		ImagenPanel edificio2 = new ImagenPanel(edificios[1]);
		edificio2.setToolTipText(edificios[1]);
		edificio2.addMouseListener(new ControladorEntrarEdificio(juego, 1));
		add(edificio2, "4, 2, fill, fill");

		ImagenPanel edificio3 = new ImagenPanel(edificios[2]);
		edificio3.setToolTipText(edificios[2]);
		edificio3.addMouseListener(new ControladorEntrarEdificio(juego, 2));
		add(edificio3, "6, 2, fill, fill");

		JLabel lblNewLabel = new JLabel("Haga click en la imagen para ingresar al edificio.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		add(lblNewLabel, "2, 4, 5, 1, fill, fill");
	}

	private class ImagenPanel extends JPanel {

		private static final long serialVersionUID = 1906591719910482990L;
		private Image image;
		private MediaTracker mt;

		public ImagenPanel(String edificio) {
			mt = new MediaTracker(this);
			image = Toolkit.getDefaultToolkit().getImage(EDIFICIOS + edificio + EXTENSION_JPG);
			mt.addImage(image, 0);
			try {
				mt.waitForAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);
		}

	}

}
