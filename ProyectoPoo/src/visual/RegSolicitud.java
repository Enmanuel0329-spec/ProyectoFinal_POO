package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logico.BolsaLaboral;
import logico.Oferta;
import logico.Persona;
import logico.Solicitud;

public class RegSolicitud extends JDialog {

	private Persona candidatoActual;
	private Solicitud solicitudAEditar;

	private JComboBox<String> cmbArea;
	private JTextField txtCargo;
	private JComboBox<String> cmbJornada;
	private JSpinner spnSalarioMin;
	private JSpinner spnSalarioMax;
	private JCheckBox chkMudarse;

	/**
	 * @wbp.parser.constructor
	 */
	public RegSolicitud(Persona userLogueado) {
		this(userLogueado, (Oferta) null);
	}

	public RegSolicitud(Persona userLogueado, Oferta ofertaBase) {
		this.candidatoActual = userLogueado;
		this.solicitudAEditar = null;
		construirVentana();

		if (ofertaBase != null) {
			cmbArea.setSelectedItem(ofertaBase.getArea());
			txtCargo.setText(ofertaBase.getDescripcionPuesto());
			cmbJornada.setSelectedItem(capitalizar(ofertaBase.getTipoJornada()));
			spnSalarioMin.setValue((int) ofertaBase.getSalarioMinimo());
			spnSalarioMax.setValue((int) ofertaBase.getSalarioMaximo());
			chkMudarse.setSelected(ofertaBase.isRequiereDispMudarse());
		}
	}

	public RegSolicitud(Persona userLogueado, Solicitud solicitudAEditar) {
		this.candidatoActual = userLogueado;
		this.solicitudAEditar = solicitudAEditar;
		construirVentana();

		cmbArea.setSelectedItem(solicitudAEditar.getArea());
		txtCargo.setText(solicitudAEditar.getCargoDeseado());
		cmbJornada.setSelectedItem(solicitudAEditar.getTipoJornada());
		spnSalarioMin.setValue(solicitudAEditar.getSalarioMinimo());
		spnSalarioMax.setValue(solicitudAEditar.getSalarioMaximo());
		chkMudarse.setSelected(solicitudAEditar.isDispuestoMudarse());
	}

	private void construirVentana() {
		setTitle(solicitudAEditar == null ? "Crear Solicitud de Empleo" : "Modificar Solicitud de Empleo");
		setResizable(false);
		setBounds(100, 100, 496, 417);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Datos de la solicitud", TitledBorder.CENTER, TitledBorder.TOP));
		panelDatos.setLayout(null);
		panelDatos.setBounds(10, 10, 460, 363);
		panel.add(panelDatos);

		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(10, 20, 100, 16);
		panelDatos.add(lblArea);

		cmbArea = new JComboBox<>();
		cmbArea.addItem("Tecnologia");
		cmbArea.addItem("Salud");
		cmbArea.addItem("Administracion");
		cmbArea.addItem("Construccion");
		cmbArea.addItem("Educacion");
		cmbArea.addItem("Ventas");
		cmbArea.setBounds(10, 38, 200, 26);
		panelDatos.add(cmbArea);

		JLabel lblCargo = new JLabel("Cargo deseado:");
		lblCargo.setBounds(10, 78, 150, 16);
		panelDatos.add(lblCargo);

		txtCargo = new JTextField();
		txtCargo.setBounds(10, 96, 300, 26);
		panelDatos.add(txtCargo);

		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setBounds(10, 136, 100, 16);
		panelDatos.add(lblJornada);

		cmbJornada = new JComboBox<>();
		cmbJornada.addItem("Tiempo completo");
		cmbJornada.addItem("Tiempo parcial");
		cmbJornada.addItem("Por hora");
		cmbJornada.setBounds(10, 154, 200, 26);
		panelDatos.add(cmbJornada);

		JLabel lblSalarioMin = new JLabel("Salario minimo:");
		lblSalarioMin.setBounds(10, 194, 120, 16);
		panelDatos.add(lblSalarioMin);

		spnSalarioMin = new JSpinner();
		spnSalarioMin.setModel(new SpinnerNumberModel(0, 0, null, 500));
		spnSalarioMin.setBounds(10, 212, 110, 26);
		panelDatos.add(spnSalarioMin);

		JLabel lblSalarioMax = new JLabel("Salario maximo:");
		lblSalarioMax.setBounds(150, 194, 120, 16);
		panelDatos.add(lblSalarioMax);

		spnSalarioMax = new JSpinner();
		spnSalarioMax.setModel(new SpinnerNumberModel(0, 0, null, 500));
		spnSalarioMax.setBounds(150, 212, 110, 26);
		panelDatos.add(spnSalarioMax);

		chkMudarse = new JCheckBox("Dispuesto a mudarse");
		chkMudarse.setBackground(Color.WHITE);
		chkMudarse.setBounds(290, 214, 160, 24);
		panelDatos.add(chkMudarse);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(70, 290, 100, 40);
		panelDatos.add(btnCancelar);

		JButton btnGuardar = new JButton(solicitudAEditar == null ? "Crear Solicitud" : "Actualizar Solicitud");
		btnGuardar.setBackground(new Color(30, 144, 255));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String area = cmbArea.getSelectedItem().toString();
				String cargo = txtCargo.getText().trim();
				String jornada = cmbJornada.getSelectedItem().toString();
				float salMin = ((Number) spnSalarioMin.getValue()).floatValue();
				float salMax = ((Number) spnSalarioMax.getValue()).floatValue();
				boolean mudarse = chkMudarse.isSelected();

				if (cargo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Escribe el cargo deseado.",
							"Atencion", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (salMin <= 0 || salMax <= 0) {
					JOptionPane.showMessageDialog(null, "El salario debe ser mayor a cero.",
							"Atencion", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (salMin > salMax) {
					JOptionPane.showMessageDialog(null, "El salario minimo no puede ser mayor que el maximo.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (solicitudAEditar == null) {
					String codigoSol = "SOL-" + BolsaLaboral.generadorIdSolicitud;
					Solicitud nuevaSolicitud = new Solicitud(codigoSol, candidatoActual, area, cargo,
							jornada, salMin, salMax, mudarse);
					BolsaLaboral.getInstancia().registrarSolicitud(nuevaSolicitud);
					JOptionPane.showMessageDialog(null, "Solicitud registrada con exito!",
							"Exito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					solicitudAEditar.setArea(area);
					solicitudAEditar.setCargoDeseado(cargo);
					solicitudAEditar.setTipoJornada(jornada);
					solicitudAEditar.setSalarioMinimo(salMin);
					solicitudAEditar.setSalarioMaximo(salMax);
					solicitudAEditar.setDispuestoMudarse(mudarse);
					BolsaLaboral.getInstancia().guardarMemoria();
					JOptionPane.showMessageDialog(null, "Tu solicitud ha sido actualizada!",
							"Exito", JOptionPane.INFORMATION_MESSAGE);
				}
				dispose();
			}
		});
		btnGuardar.setBounds(190, 290, 200, 40);
		panelDatos.add(btnGuardar);
	}

	private String capitalizar(String texto) {
		if (texto == null || texto.isEmpty()) return texto;
		return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
	}
}