package algo3.vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class VentanaDeJuego extends JFrame {

	private static final long serialVersionUID = -4601737718372115147L;

	public VentanaDeJuego() {
		setTitle("¿Donde esta Carmen SanDiego?");
		setSize(290, 320);
		setResizable(false);
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("30dlu"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("30dlu"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("30dlu"),
						FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("30dlu"), }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("20dlu"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("30dlu"),
						FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("max(125dlu;default)"), }));

		JLabel lblInformacion = new JLabel("");
		lblInformacion.setBackground(Color.WHITE);
		getContentPane().add(lblInformacion, "2, 2, 7, 1");

		JButton btnVer = new JButton();
		btnVer.setBackground(Color.BLACK);
		btnVer.setIcon(new ImageIcon("verTest.png"));
		btnVer.setToolTipText("Ver posibles destinos");
		getContentPane().add(btnVer, "2, 4");

		JButton btnViajar = new JButton("");
		btnViajar.setIcon(new ImageIcon("viajarTest.jpg"));
		btnViajar.setBackground(Color.BLACK);
		btnViajar.setForeground(Color.DARK_GRAY);
		btnViajar.setToolTipText("Viajar a alguna ciudad");
		getContentPane().add(btnViajar, "4, 4, fill, fill");

		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon("buscar.jpg"));
		btnBuscar.setBackground(Color.BLACK);
		btnBuscar.setForeground(Color.DARK_GRAY);
		btnBuscar.setToolTipText("Buscar pistas");
		getContentPane().add(btnBuscar, "6, 4, fill, fill");

		JButton btnExpedientes = new JButton("");
		btnExpedientes.setIcon(new ImageIcon("C:expedientes2.jpg"));
		btnExpedientes.setBackground(Color.BLACK);
		btnExpedientes.setForeground(Color.DARK_GRAY);
		btnExpedientes.setToolTipText("Emitir orden de arresto");
		getContentPane().add(btnExpedientes, "8, 4, fill, fill");

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, "2, 6, 7, 1");

	}

}
