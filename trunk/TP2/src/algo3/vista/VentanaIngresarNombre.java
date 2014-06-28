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

public class VentanaIngresarNombre extends JFrame {

	private JTextField textField;
	private FramePrincipal framePrincipal;

	public VentanaIngresarNombre(final FramePrincipal framePrincipal) {
		this.framePrincipal = framePrincipal;
		setSize(450, 180);
		setLocation(500, 300);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);

		JLabel lblInformacion = new JLabel("Policia al teclado, por favor identifiquese:");
		lblInformacion.setFont(new Font("Calibri Light", Font.BOLD, 15));
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);

		JButton bntAceptar = new JButton("Aceptar");
		bntAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((textField.getText() == null) || (textField.getText().trim().isEmpty())) {
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
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addGap(58).addComponent(textField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE).addGap(56)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap().addComponent(lblInformacion, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(textField).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(bntAceptar).addComponent(bntCancelar)).addGap(137)));
		getContentPane().setLayout(groupLayout);
	}

	public void clearTextField() {
		textField.setText("");
	}

	protected void iniciarYMostrarFrameDeJuego() {
		// llama a la ventana de Juego posta.
		FrameJuego frameJuego = new FrameJuego(framePrincipal);
		// frameJuego.setResizable(false);
		frameJuego.setVisible(true);
		frameJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frameJuego.setDefaultCloseOperation(VentanaDeJuego.EXIT_ON_CLOSE);
		this.dispose();
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
