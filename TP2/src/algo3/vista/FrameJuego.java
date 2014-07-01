package algo3.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import algo3.controlador.ControladorBuscarEdificos;
import algo3.controlador.ControladorMoverCiudades;
import algo3.controlador.ControladorPosiblesDestinos;
import algo3.controlador.XMLParser;
import algo3.modelo.juego.Juego;
import algo3.modelo.policia.Policia;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;

public class FrameJuego extends JFrame implements Observer {

	private static final long serialVersionUID = 9051874880109659757L;

	private static String MARKER = "&markers=";
	private static final String GOOLGE_URL = "www.google.com";
	private static String GOOGLE_MAPS_URL = "http://maps.google.com/maps/api/staticmap?center=30,0&zoom=1&scale=2&size=900x400&sensor=false";
	private static final String CIUDADES = "ciudades/";
	private static final String EXTENSION_JPG = ".jpg";

	private FrameExpedientes expedientes;
	private FramePrincipal framePrincipal;
	private Browser browser;
	private Juego juego;

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
	private Policia policia;
	private JLabel lblCiudadActualFoto;
	private String path;
	private static Map<String, String> casosEspeciales;

	public FrameJuego(final FramePrincipal framePrincipal, Policia policia, String path) {
		this.framePrincipal = framePrincipal;
		this.policia = policia;
		this.path = path;

		browser = BrowserFactory.create();

		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,},
						new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),}));

		lblCiudadActual = new JLabel("");
		lblCiudadActual.setBackground(new Color(255, 99, 71));
		lblCiudadActual.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblCiudadActual.setBackground(new Color(255, 99, 71));
		lblCiudadActual.setOpaque(true);
		lblCiudadActual.setForeground(Color.BLACK);
		lblCiudadActual.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCiudadActual, "2, 2, fill, fill");
		lblCiudadActualFoto = new JLabel();
		//		Icon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ciudades/Sidney.jpg"));
		//		lblCiudadActualFoto.setIcon(icon);
		//		lblCiudadActualFoto.setHorizontalAlignment(SwingConstants.CENTER);
		//		getContentPane().add(lblCiudadActualFoto, "2, 4, 3, 1, fill, fill");

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

		getContentPane().add(btnViajar, "8, 6, 1, 3, fill, fill");

		btnBuscar = new JButton("Buscar");

		getContentPane().add(btnBuscar, "8, 10, 1, 3, fill, fill");
		bntExpedientes = new JButton("Expedientes");
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
		iniciarJuego();
		btnVerPosiblesDestinos.addActionListener(new ControladorPosiblesDestinos(this, juego));
		btnViajar.addActionListener(new ControladorMoverCiudades(this, juego));
		btnBuscar.addActionListener(new ControladorBuscarEdificos(this,juego));

		iniciarFrameExpedientes(XMLParser.cargarCaracteristicasExpedientes());
		setearImagenCiudad(juego.getCiudadOrigen());
		//mostrarFrameDeCiudad(juego.getCiudadOrigen());
		//mostrarGoogleMaps();
		//refrescarMarcadores();
	}

	private void mostrarMensajeInicio(){
		StringBuilder sb = new StringBuilder();
		sb.append("Mensaje de Interpol:\n");
		sb.append("Una persona de sexo ");
		sb.append(juego.getSexoLadron());
		sb.append(" se robo ");
		sb.append(juego.getNombreObjeto());
		sb.append(" del tesoro nacional de ");
		sb.append(juego.getCiudadOrigen());
		sb.append("\n");
		sb.append("Su mision: Atrapar al ladron antes del Domingo a las 17:00 horas\n");
		sb.append("Buena suerte " + policia.getGrado().getPlaca() + " " + policia.getNombre());
		JOptionPane.showMessageDialog(null, sb.toString(), "Nuevo caso", JOptionPane.INFORMATION_MESSAGE);
	}

	private void notificarAtaque(){
		StringBuilder sb = new StringBuilder();
		sb.append("Has sido atacado.\n");
		JOptionPane.showMessageDialog(null, sb.toString(), "ATAQUE", JOptionPane.INFORMATION_MESSAGE);
	}


	private void iniciarJuego() {
		this.juego = new Juego(policia, lblReloj, lblCiudadActual);
		juego.addObserver(this);
		mostrarMensajeInicio();
	}

	private void iniciarFrameExpedientes(Map<String, Set<String>> caracteristicas) {
		expedientes = new FrameExpedientes(juego, caracteristicas);
		expedientes.setVisible(false);
	}

	public void mostarFrameDeViaje(String lblInformacion, String lblBoton, String[] informacionCombo) {
		FrameDeViaje fViajar = new FrameDeViaje(juego, lblInformacion, lblBoton, informacionCombo, this);
		fViajar.setVisible(true);
	}

	public void setearImagenCiudad(String nombreCiudad){
		Image foto = this.conseguirImagenCiudad(nombreCiudad);
		Icon icon = new ImageIcon(foto);
		lblCiudadActualFoto.setIcon(icon);
		lblCiudadActualFoto.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCiudadActualFoto, "2, 4, 3, 1, fill, fill");
		lblCiudadActualFoto.show();
	}

	private Image conseguirImagenCiudad(String nombreCiudad){
		return Toolkit.getDefaultToolkit().getImage(CIUDADES + nombreCiudad + EXTENSION_JPG);
	}

	public void mostrarFrameDeCiudad(String nombreCiudad) {
		new FrameCiudad(nombreCiudad);
	}

	public void mostarFrameDeEdificios(String lblInformacion, String lblBoton, String[] informacionCombo) {
		this.lblCiudadActualFoto.show();
		FrameDeEdificios fEdificios = new FrameDeEdificios(juego, lblInformacion, lblBoton, informacionCombo);
		fEdificios.setVisible(true);
	}

	private String getMarkers() {
		String markers = "";
		String[] ciudades = juego.getCiudadesPosibles();
		chequearCiudades(ciudades);
		for (String ciudad : ciudades) {
			markers = markers + MARKER + ciudad.replace(" ", "%"); // Es necesario reemplazar los espacios de los nombres por %
		}
		return markers;
	}

	private void chequearCiudades(String[] ciudades){
		for (int i = 0; i < ciudades.length; i++){
			if (casosEspeciales.containsKey(ciudades[i])){
				ciudades[i] = casosEspeciales.get(ciudades[i]);
			}
		}
	}

	static {
		casosEspeciales = new HashMap<String, String>();
		casosEspeciales.put("Ciudad de Mexico", "Mexico");
		casosEspeciales.put("Rio de Janeiro", "Rio do Janeiro");
		casosEspeciales.put("Tokio", "Tokio,Japon");
		casosEspeciales.put("New Delhi", "newdelhi");
	}

	protected void cerrarSesion() {
		// Guardar informacion(estado) del policia. No guardar el caso.
		// Si el caso se cierra sin haberse ganado, se pierde.
		framePrincipal.setVisible(true);
		boolean resultado = juego.guardar(path);
		if (resultado){
			JOptionPane.showMessageDialog(null, "Juego guardado exitosamente");
		} else {
			JOptionPane.showMessageDialog(null, "Error al guardar el estado del juego", "", JOptionPane.ERROR_MESSAGE);
		}
		this.dispose();
	}

	protected void cerrarJuego() {
		// Cierra el juego definitivamente.
		System.exit(0);
	}

	public void refrescarMarcadores() {
		if (hayConexion()){
			browser.loadURL(GOOGLE_MAPS_URL + getMarkers());
		} else {
			mostrarGoogleMapsDefault();
		}
	}

	private boolean hayConexion(){
		//Manera tosca de chequear conexion
		try {
			InetAddress.getByName(GOOLGE_URL);
			return true;
		} catch (IOException e){
			return false;
		}
	}

	public void refrescarMarcadoresDefault() {
		mostrarGoogleMapsDefault();
	}

	public void mostrarGoogleMaps() {
		this.lblCiudadActualFoto.hide();
		if (hayConexion()){
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
		} else {
			mostrarGoogleMapsDefault();
		}
	}

	public void mostrarGoogleMapsDefault() {
		PanelMapa panel = new PanelMapa();
		getContentPane().add(panel, "2, 4, 5, 13, fill, fill");
		panel.setSize(Frame.MAXIMIZED_HORIZ, Frame.MAXIMIZED_VERT);
	}

	//TODO: Implementar esta clase!
	public void ocultarGoogleMaps(){

	}

	protected void mostrarVentanaExpedientes() {
		expedientes.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		String mensaje = (String) arg;

		JOptionPane.showMessageDialog(null, mensaje);
		int respuesta = JOptionPane.showConfirmDialog(null, "Desea jugar de nuevo?");
		juego.guardar(path);
		if (respuesta == JOptionPane.OK_OPTION){
			resetearCaso();
		} else {
			System.exit(0);
		}
	}

	private void resetearCaso(){
		juego.resetear();
		this.repaint();
		expedientes.resetear();
		mostrarMensajeInicio();
		refrescarMarcadores();
		mostrarFrameDeCiudad(juego.getCiudadOrigen());
	}

	private class PanelMapa extends JPanel {


		private static final long serialVersionUID = 1174752589700819501L;
		private static final String MUNDO = "mundo.jpg";

		private Image bgimage = null;

		private PanelMapa() {
			MediaTracker mt = new MediaTracker(this);
			bgimage = Toolkit.getDefaultToolkit().getImage(MUNDO);
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
