package algo3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import algo3.controlador.ControladorEntrarEdificio;
import algo3.modelo.juego.Juego;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrameDeEdificios extends JFrame {

	private static final long serialVersionUID = -1869602327896771994L;

	private JLabel lblEdificio;
	private JComboBox<String> cmbEdificios;
	private JButton btnAccion;
	private JButton btnCancelar;


	public FrameDeEdificios(Juego juego, String lblInformacion, String lblBoton, String[] informacionCombo) {
		setResizable(false);
		setSize(250, 130);
		setLocation(50, 50);

		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		lblEdificio = new JLabel(lblInformacion);
		lblEdificio.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblEdificio, "2, 2, 3, 1, fill, fill");

		cmbEdificios = new JComboBox<String>();
		cargarCombo(informacionCombo);
		getContentPane().add(cmbEdificios, "2, 4, 3, 1, fill, fill");

		btnAccion = new JButton(lblBoton);
		btnAccion.addActionListener(new ControladorEntrarEdificio(juego, this));
		getContentPane().add(btnAccion, "2, 6, fill, fill");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().add(btnCancelar, "4, 6, fill, fill");
	}

	private void cargarCombo(String[] informacionCombo) {
		for (String informacion : informacionCombo) {
			cmbEdificios.addItem(informacion);
		}
	}

	public int getEdificioSeleccionado() {
		return cmbEdificios.getSelectedIndex();
	}



}
