package algo3.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import algo3.controlador.XMLParser;
import algo3.modelo.policia.Policia;

public class VentanaIngresarNombre extends JFrame {

	private static final long serialVersionUID = 5114387229747868813L;

	private JTextField txtNombrePolicia;
	private FramePrincipal framePrincipal;
	private String path;

	public VentanaIngresarNombre(final FramePrincipal framePrincipal, String path) {
		this.path = path;
		this.framePrincipal = framePrincipal;
		setSize(450, 180);
		setLocation(500, 300);

		txtNombrePolicia = new JTextField();
		txtNombrePolicia.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombrePolicia.setColumns(10);

		JLabel lblInformacion = new JLabel("Policia al teclado, por favor identifiquese:");
		lblInformacion.setFont(new Font("Calibri Light", Font.BOLD, 15));
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);

		JButton bntAceptar = new JButton("Aceptar");
		bntAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((txtNombrePolicia.getText() == null) || (txtNombrePolicia.getText().trim().isEmpty())) {
					JOptionPane.showMessageDialog(null, "El nombre del policia no puede ser vacio.");
				} else {
					iniciarYMostrarFrameDeJuego();
					framePrincipal.setVisible(false);
					clearTextField();
				}
			}
		});
		bntAceptar.setFont(new Font("Calibri Light", Font.BOLD, 15));

		JButton bntCancelar = new JButton("Cancelar");
		bntCancelar.addActionListener(new volver(this));
		bntCancelar.setFont(new Font("Calibri Light", Font.BOLD, 15));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblInformacion, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
								.addGroup(
										groupLayout.createSequentialGroup().addContainerGap().addComponent(bntAceptar, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE).addGap(18)
										.addComponent(bntCancelar, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))).addContainerGap())
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addGap(58).addComponent(txtNombrePolicia, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE).addGap(56)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap().addComponent(lblInformacion, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(txtNombrePolicia).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(bntAceptar).addComponent(bntCancelar)).addGap(137)));
		getContentPane().setLayout(groupLayout);
	}

	public void clearTextField() {
		txtNombrePolicia.setText("");
	}

	protected void iniciarYMostrarFrameDeJuego() {
		Policia policia = obtenerJugador();
		// llama a la ventana de Juego posta.
		FrameJuego frameJuego = new FrameJuego(framePrincipal, policia);
		// frameJuego.setResizable(false);
		frameJuego.setVisible(true);
		frameJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frameJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.dispose();
	}

	private Policia obtenerJugador() {
		return XMLParser.cargarPoliciaDeArchivo(path, txtNombrePolicia.getText());
	}

	// Accion para cerrar ventana
	class volver implements ActionListener {
		private VentanaIngresarNombre frame;

		public volver(VentanaIngresarNombre frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			frame.clearTextField();
			frame.dispose();
		}
	}

}
