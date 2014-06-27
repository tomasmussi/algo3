package algo3.vista;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;

public class FrameJuego extends JFrame {

	private Image imagenCiudad;
	private FrameExpedientes expedientes;
	private JPanel panelPrincipal;
	private Browser browser;

	public FrameJuego() {
		expedientes = new FrameExpedientes();
		expedientes.setVisible(false);

		browser = BrowserFactory.create();
		browser.loadURL("https://maps.google.com");

		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
								FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));


		JButton btnVerPosiblesDestinos = new JButton("Ver posibles destinos");
		btnVerPosiblesDestinos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// mostrar la lista de ciudades posibles. En un JPanel??
				 String[] ciudadesPosibles = { "Ciudad 1", "Ciudad 2", "Ciudad 3", "Ciudad 4" };
				JOptionPane.showMessageDialog(null, ciudadesPosibles, "Ciudades posibles", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		getContentPane().add(btnVerPosiblesDestinos, "6, 2, fill, fill");

		JButton btnViajar = new JButton("Viajar");
		btnViajar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// situar al policia en la ciudad elegida.
				// obtener la ciudad a la que se viajo, obetener el nombre y elegir la foto a mostrar.
				// Toolkit.getDefaultToolkit().getImage(ciudad.getNombre()); ???
				// Ver como interactuar con google maps y las coordenadas, esto por ahora es solo de adorno,
				// todavia no tiene funcionalidad, es solo para hacerlo "cheto".
				mostrarGoogleMaps(browser);
			}
		});
		getContentPane().add(btnViajar, "6, 4, fill, fill");

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// mostrar los edificios. Usar imagenes segun los tipos de edificio.
				// reemplazar el JPanel por uno nuevo que reciba los edificios. ejemplo JEdificiosPanel.
				panelPrincipal = new EdificiosPanel();
			}
		});
		getContentPane().add(btnBuscar, "6, 6, fill, fill");

		JButton bntExpedientes = new JButton("Expedientes");
		bntExpedientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaExpedientes();
			}
		});
		getContentPane().add(bntExpedientes, "6, 8, fill, fill");

	}

	protected void mostrarGoogleMaps(Browser browser) {
		getContentPane().add(browser.getView().getComponent(), "2, 2, 1, 7, fill, fill");
		while (browser.isLoading()) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Cargando mapa, por favor aguarde.");
		}
		// Modifica el tamaño para que Swing pueda identificar el cambio del estado de la ventana.
		setSize(Frame.MAXIMIZED_HORIZ, Frame.MAXIMIZED_VERT);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	protected void mostrarVentanaExpedientes() {
		expedientes.setVisible(true);
	}
}
