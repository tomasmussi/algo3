package algo3.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PanelCiudad extends JPanel {

	private static final String CIUDADES = "ciudades/";
	private static final String EXTENSION_JPG = ".jpg";

	private static final long serialVersionUID = -2467583339589323677L;
	private Image bgimage = null;
	private MediaTracker mt;

	public PanelCiudad(String nombreCiudad) {
		mt = new MediaTracker(this);
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
	}

}