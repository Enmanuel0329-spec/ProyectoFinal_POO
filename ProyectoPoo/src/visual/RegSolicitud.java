package visual;

import java.awt.BorderLayout;
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
import java.util.ArrayList;

import logico.BolsaLaboral;
import logico.Oferta;
import logico.Persona;
import logico.Solicitud;

public class RegSolicitud extends JDialog {

	private Persona candidatoActual;
	private Solicitud solicitudExistente = null;

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
		this(userLogueado, null);
	}

	public RegSolicitud(Persona userLogueado, Oferta ofertaBase) {
		this.candidatoActual = userLogueado;

		setTitle("Gestion de Solicitud de Empleo");
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

		ArrayList<Solicitud> misSolicitudes = BolsaLaboral.getInstancia().solicitudesPorPersona(candidatoActual);
		int i = 0;
		boolean encontrada = false;
		while (!encontrada && i < misSolicitudes.size()) {
			if (misSolicitudes.get(i).getEstado().equalsIgnoreCase("activa")) {
				solicitudExistente = misSolicitudes.get(i);
				encontrada = true;
			}
			i++;
		}

		if (solicitudExistente != null) {
			cmbArea.setSelectedItem(solicitudExistente.getArea());
			txtCargo.setText(solicitudExistente.getCargoDeseado());
			cmbJornada.setSelectedItem(solicitudExistente.getTipoJornada());
			spnSalarioMin.setValue(solicitudExistente.getSalarioMinimo());
			spnSalarioMax.setValue(solicitudExistente.getSalarioMaximo());
			chkMudarse.setSelected(solicitudExistente.isDispuestoMudarse());
		} else if (ofertaBase != null) {
			cmbArea.setSelectedItem(ofertaBase.getArea());
			txtCargo.setText(ofertaBase.getDescripcionPuesto());
			cmbJornada.setSelectedItem(capitalizar(ofertaBase.getTipoJornada()));
			spnSalarioMin.setValue((int) ofertaBase.getSalarioMinimo());
			spnSalarioMax.setValue((int) ofertaBase.getSalarioMaximo());
			chkMudarse.setSelected(ofertaBase.isRequiereDispMudarse());
		}

		JButton btnGuardar = new JButton(solicitudExistente == null ? "Crear Solicitud" : "Actualizar Solicitud");
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

				if (solicitudExistente == null) {
					String codigoSol = "SOL-" + BolsaLaboral.generadorIdSolicitud;
					Solicitud nuevaSolicitud = new Solicitud(codigoSol, candidatoActual, area, cargo,
							jornada, salMin, salMax, mudarse);
					BolsaLaboral.getInstancia().registrarSolicitud(nuevaSolicitud);
					JOptionPane.showMessageDialog(null, "Solicitud registrada con exito!",
							"Exito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					solicitudExistente.setArea(area);
					solicitudExistente.setCargoDeseado(cargo);
					solicitudExistente.setTipoJornada(jornada);
					solicitudExistente.setSalarioMinimo(salMin);
					solicitudExistente.setSalarioMaximo(salMax);
					solicitudExistente.setDispuestoMudarse(mudarse);
					BolsaLaboral.getInstancia().guardarMemoria();
					JOptionPane.showMessageDialog(null, "Tu solicitud ha sido actualizada!",
							"Exito", JOptionPane.INFORMATION_MESSAGE);
				}
				dispose();
			}
		});
		btnGuardar.setBounds(150, 290, 180, 40);
		panelDatos.add(btnGuardar);
	}

	private String capitalizar(String texto) {
		if (texto == null || texto.isEmpty()) return texto;
		return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
	}
}