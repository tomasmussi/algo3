package algo3.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrameCiudad extends JFrame{

	private static final String CIUDADES = "ciudades/";
	private static final String EXTENSION_JPG = ".jpg";
	private static final long serialVersionUID = 1L;


	public FrameCiudad(String nombreCiudad) {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JPanelPrincipal panel = new JPanelPrincipal(nombreCiudad);
		getContentPane().add(panel, "2, 2, fill, fill");
		setAlwaysOnTop(true);
		setVisible(true);
		setSize(1200, 700);
		setLocation(50, 40);
	}


	class JPanelPrincipal extends JPanel {

		private static final long serialVersionUID = 1174752589700819501L;

		Image bgimage = null;

		JPanelPrincipal(String nombreCiudad) {
			MediaTracker mt = new MediaTracker(this);
			bgimage = Toolkit.getDefaultToolkit().getImage(CIUDADES + nombreCiudad + EXTENSION_JPG);
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
			int widht = bgimage.getWidth(null) <= 1000 ? bgimage.getWidth(null) : 1000;
			int height = bgimage.getHeight(null) <= 1000 ? bgimage.getHeight(null) : 1000;
			FrameCiudad.this.setSize(widht,height);
		}
	}

}