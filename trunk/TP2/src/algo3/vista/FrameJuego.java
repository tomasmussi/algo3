package algo3.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import algo3.controlador.ControladorMoverCiudades;
import algo3.controlador.ControladorPosiblesDestinos;
import algo3.modelo.juego.Juego;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;

public class FrameJuego extends JFrame {

	private static final long serialVersionUID = 9051874880109659757L;

	private static String MARKER = "&markers=";
	private static String GOOGLE_MAPS_URL = "http://maps.google.com/maps/api/staticmap?center=30,0&zoom=1&scale=2&size=900x400&sensor=false";

	private FrameExpedientes expedientes;
	private FramePrincipal framePrincipal;
	private Browser browser;
	private Juego juego;

	// private Image imagenCiudad;
	// private JPanel panelPrincipal;
	private JButton btnVerPosiblesDestinos;
	private JButton btnViajar;
	private JButton btnBuscar;
	private JButton bntExpedientes;
	private JMenuBar menuBar;
	private JMenu mnJuego;
	private JMenuItem mntmCerrarSesion;
	private JMenuItem mntmSalir;
	private JLabel lblReloj;
	private JLabel lblCiudadActual;

	public FrameJuego(final FramePrincipal framePrincipal, String nombrePolicia) {
		this.framePrincipal = framePrincipal;

		iniciarFrameExpedientes();

		browser = BrowserFactory.create();


		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		lblCiudadActual = new JLabel("");
		lblCiudadActual.setBackground(new Color(255, 99, 71));
		lblCiudadActual.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblCiudadActual.setBackground(new Color(255, 99, 71));
		lblCiudadActual.setOpaque(true);
		lblCiudadActual.setForeground(Color.BLACK);
		lblCiudadActual.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCiudadActual, "2, 2, fill, fill");
		// btnVerPosiblesDestinos.setBackground(new Color(255, 99, 71));

		btnVerPosiblesDestinos = new JButton("Ver posibles destinos");


		getContentPane().add(btnVerPosiblesDestinos, "8, 2, 1, 3, fill, fill");

		lblReloj = new JLabel();
		lblReloj.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblReloj.setBackground(new Color(255, 99, 71));
		lblReloj.setOpaque(true);
		lblReloj.setForeground(Color.BLACK);
		lblReloj.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblReloj, "4, 2, 3, 1, fill, fill");

		btnViajar = new JButton("Viajar");
		// btnViajar.setBackground(new Color(255, 99, 71));

		getContentPane().add(btnViajar, "8, 6, 1, 3, fill, fill");

		btnBuscar = new JButton("Buscar");
		// btnBuscar.setBackground(new Color(255, 99, 71));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// mostrar los edificios. Usar imagenes segun los tipos de edificio.
				// reemplazar el JPanel por uno nuevo que reciba los edificios. ejemplo JEdificiosPanel.
				String[] edificios = { "Banco", "Aeropuerto", "Biblioteca" };
				mostarFrameDeViaje("Entrar en:", "Buscar", edificios);
			}
		});
		getContentPane().add(btnBuscar, "8, 10, 1, 3, fill, fill");
		bntExpedientes = new JButton("Expedientes");
		// bntExpedientes.setBackground(new Color(255, 99, 71));
		bntExpedientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaExpedientes();
			}
		});
		getContentPane().add(bntExpedientes, "8, 14, 1, 3, fill, fill");

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnJuego = new JMenu("Juego");
		menuBar.add(mnJuego);

		mntmCerrarSesion = new JMenuItem("Cerrar sesion");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opcion = JOptionPane.showConfirmDialog(null, "Al cerrar sesion se guardara el estado del policia.\nEl caso se perdera.");
				if (opcion == JOptionPane.OK_OPTION) {
					cerrarSesion();
				}
			}
		});
		mnJuego.add(mntmCerrarSesion);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir?\nLa informacion no guardada se perdera.");
				if (opcion == JOptionPane.OK_OPTION) {
					cerrarJuego();
				}
			}
		});
		mnJuego.add(mntmSalir);

		// Let the game begin...
		iniciarJuego(nombrePolicia);
		btnVerPosiblesDestinos.addActionListener(new ControladorPosiblesDestinos(juego));
		btnViajar.addActionListener(new ControladorMoverCiudades(this, juego));
		browser.loadURL(GOOGLE_MAPS_URL + getMarkers());
	}

	private void iniciarJuego(String nombrePolicia) {
		this.juego = new Juego(nombrePolicia, lblReloj, lblCiudadActual);
	}

	private void iniciarFrameExpedientes() {
		expedientes = new FrameExpedientes();
		expedientes.setVisible(false);
	}

	public void mostarFrameDeViaje(String lblInformacion, String lblBoton, String[] informacionCombo) {
		FrameDeViaje fViajar = new FrameDeViaje(juego, lblInformacion, lblBoton, informacionCombo);
		fViajar.setVisible(true);
	}

	private String getMarkers() {
		// Armar un marker por cada ciudad, esta lista de ciudades son las mismas que se tienen que ver en el boton
		// de ver posibles destinos.
		// Para cada ciudad agregar su marker. Ejemplo:
		String markers = "";
		String[] ciudades = juego.getCiudadesPosibles();
		for (String ciudad : ciudades) {
			markers = markers + MARKER + ciudad.replace(" ", "%"); // Es necesario reemplazar los espacios de los nombres por %
		}
		return markers;
	}

	protected void cerrarSesion() {
		// Guardar informacion(estado) del policia. No guardar el caso.
		// Si el caso se cierra sin haberse ganado, se pierde.
		framePrincipal.setVisible(true);
		this.dispose();
	}

	protected void cerrarJuego() {
		// Cierra el juego definitivamente.
		System.exit(0);
	}

	public void mostrarGoogleMaps() {
		getContentPane().add(browser.getView().getComponent(), "2, 4, 5, 13, fill, fill");
		while (browser.isLoading()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Cargando mapa, por favor aguarde.");
		}
		// Modifica el tamanio para que Swing pueda identificar el cambio del estado de la ventana.
		setSize(Frame.MAXIMIZED_HORIZ, Frame.MAXIMIZED_VERT);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	protected void mostrarVentanaExpedientes() {
		expedientes.setVisible(true);
	}

}
