package algo3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrameExpedientes extends JFrame {

	public FrameExpedientes() {
		setSize(400, 250);
		setResizable(false);
		setLocation(450, 250);

		setTitle("Emitir orden de arresto");
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblSexo, "1, 4, fill, fill");

		JComboBox<String> cmbSexo = new JComboBox<String>();
		cmbSexo.addItem("Seleccione sexo...");
		cmbSexo.addItem("Masculino");
		cmbSexo.addItem("Femenino");
		getContentPane().add(cmbSexo, "3, 4, fill, default");

		JLabel lblHobby = new JLabel("Hobby");
		lblHobby.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblHobby, "1, 6, fill, fill");

		JComboBox<String> cmbHobby = new JComboBox<String>();
		cmbHobby.addItem("Seleccione hobby...");
		cmbHobby.addItem("Tenis");
		cmbHobby.addItem("Croquet");
		cmbHobby.addItem("Mountain Climbing");
		getContentPane().add(cmbHobby, "3, 6, fill, default");

		JLabel lblPelo = new JLabel("Color de Pelo");
		lblPelo.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPelo, "1, 8, fill, fill");

		JComboBox<String> cmbPelos = new JComboBox<String>();
		cmbPelos.addItem("Seleccione color de cabello...");
		cmbPelos.addItem("Rubio");
		cmbPelos.addItem("Castanio");
		cmbPelos.addItem("Negro");
		cmbPelos.addItem("Rojo");
		getContentPane().add(cmbPelos, "3, 8, fill, default");

		JLabel lblCaracteristicas = new JLabel("Caracteristica");
		lblCaracteristicas.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCaracteristicas, "1, 10, fill, fill");

		JComboBox<String> cmbCaracteristicas = new JComboBox<String>();
		cmbCaracteristicas.addItem("Seleccione caracteristica...");
		cmbCaracteristicas.addItem("Tatuaje");
		cmbCaracteristicas.addItem("Anillo");
		cmbCaracteristicas.addItem("Joyeria");
		getContentPane().add(cmbCaracteristicas, "3, 10, fill, default");

		JLabel lblVehiculo = new JLabel("Vehiculo");
		lblVehiculo.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblVehiculo, "1, 12, fill, fill");

		JComboBox<String> cmbVehiculos = new JComboBox<String>();
		cmbVehiculos.addItem("Seleccione vehiculo...");
		cmbVehiculos.addItem("Limusina");
		cmbVehiculos.addItem("Motocicleta");
		cmbVehiculos.addItem("Descapotable");
		getContentPane().add(cmbVehiculos, "3, 12, fill, default");

		JButton btnNewButton = new JButton("Emitir orden");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// emitir orden. Setearla en el caso.
			}
		});
		getContentPane().add(btnNewButton, "1, 16");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		getContentPane().add(btnCancelar, "3, 16");

	}

	protected void cerrarVentana() {
		// La esconde, no la cierra.
		this.setVisible(false);
	}

}
