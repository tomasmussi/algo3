package algo3.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import algo3.controlador.ControladorOrdenArresto;
import algo3.modelo.juego.Juego;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrameExpedientes extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final String SEXO = "SEXO";
	public static final String HOBBY = "HOBBY";
	public static final String CABELLO = "CABELLO";
	public static final String CARACTERISTICA = "CARACTERISTICA";
	public static final String VEHICULO = "VEHICULO";
	public static final String SELECCIONAR = "Seleccione...";
	private Map<String, Set<String>> caracteristicas;

	private JComboBox<String> cmbSexo;
	JComboBox<String> cmbHobby;
	JComboBox<String> cmbPelos;
	JComboBox<String> cmbCaracteristicas;
	JComboBox<String> cmbVehiculos;

	public FrameExpedientes(Juego juego, Map<String, Set<String>> caracteristicas) {
		this.caracteristicas = caracteristicas;

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

		cmbSexo = new JComboBox<String>();
		cargarComboDeTipo(SEXO, cmbSexo);
		getContentPane().add(cmbSexo, "3, 4, fill, default");

		JLabel lblHobby = new JLabel("Hobby");
		lblHobby.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblHobby, "1, 6, fill, fill");

		cmbHobby = new JComboBox<String>();
		cargarComboDeTipo(HOBBY, cmbHobby);
		getContentPane().add(cmbHobby, "3, 6, fill, default");

		JLabel lblPelo = new JLabel("Color de Pelo");
		lblPelo.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPelo, "1, 8, fill, fill");

		cmbPelos = new JComboBox<String>();
		cargarComboDeTipo(CABELLO, cmbPelos);
		getContentPane().add(cmbPelos, "3, 8, fill, default");

		JLabel lblCaracteristicas = new JLabel("Caracteristica");
		lblCaracteristicas.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCaracteristicas, "1, 10, fill, fill");

		cmbCaracteristicas = new JComboBox<String>();
		cargarComboDeTipo(CARACTERISTICA, cmbCaracteristicas);
		getContentPane().add(cmbCaracteristicas, "3, 10, fill, default");

		JLabel lblVehiculo = new JLabel("Vehiculo");
		lblVehiculo.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblVehiculo, "1, 12, fill, fill");

		cmbVehiculos = new JComboBox<String>();
		cargarComboDeTipo(VEHICULO, cmbVehiculos);
		getContentPane().add(cmbVehiculos, "3, 12, fill, default");

		JButton btnNewButton = new JButton("Emitir orden");
		getContentPane().add(btnNewButton, "1, 16");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		getContentPane().add(btnCancelar, "3, 16");
		btnNewButton.addActionListener(new ControladorOrdenArresto(juego, this));

	}

	protected void cerrarVentana() {
		// La esconde, no la cierra.
		this.setVisible(false);
	}

	private void cargarComboDeTipo(String tipo, JComboBox<String> combo){
		combo.addItem(SELECCIONAR);
		Iterator<String> elementos = caracteristicas.get(tipo).iterator();
		while (elementos.hasNext()){
			String elemento = elementos.next();
			combo.addItem(elemento);
		}
	}

	public List<String> getListaCaracteristicas() {
		List<String> caracteristicas = new ArrayList<String>();
		caracteristicas.add(getItem(cmbSexo.getSelectedItem()));
		caracteristicas.add(getItem(cmbHobby.getSelectedItem()));
		caracteristicas.add(getItem(cmbPelos.getSelectedItem()));
		caracteristicas.add(getItem(cmbCaracteristicas.getSelectedItem()));
		caracteristicas.add(getItem(cmbVehiculos.getSelectedItem()));
		return caracteristicas;
	}

	private String getItem(Object item){
		String elemento = (String) item;
		return elemento.equals(SELECCIONAR) ? null : elemento;
	}

	public void mostrarOrdenEmitida(String[] nombres) {
		String mensaje = "";
		if (nombres.length == 0){
			mensaje = "No hay ladrones que coincidan con esas caracteristicas";
		} else {
			mensaje = "Orden de arresto\n";
			for (int i = 0; i < nombres.length; i++){
				mensaje += nombres[i] + "\n";
			}
		}
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public void resetear() {
		cmbSexo.setSelectedItem(SELECCIONAR);
		cmbHobby.setSelectedItem(SELECCIONAR);
		cmbPelos.setSelectedItem(SELECCIONAR);
		cmbCaracteristicas.setSelectedItem(SELECCIONAR);
		cmbVehiculos.setSelectedItem(SELECCIONAR);
	}


}
