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
	private JComboBox<String> cmbJornada;
	private JSpinner spnSalarioMin;
	private JSpinner spnSalarioMax;
	private JCheckBox chkMudarse;
	private JComboBox<String> cmbEspecialidad;
	private JComboBox<String> cmbModalidad;

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
			cargarEspecialidades(ofertaBase.getArea());
			cmbEspecialidad.setSelectedItem(ofertaBase.getEspecialidad());
			cmbJornada.setSelectedItem(capitalizar(ofertaBase.getTipoJornada()));
			spnSalarioMin.setValue((int) ofertaBase.getSalarioMinimo());
			spnSalarioMax.setValue((int) ofertaBase.getSalarioMaximo());
			chkMudarse.setSelected(ofertaBase.isRequiereDispMudarse());
			cmbModalidad.setSelectedItem(capitalizar(ofertaBase.getModalidad()));
		}
	}

	public RegSolicitud(Persona userLogueado, Solicitud solicitudAEditar) {
		this.candidatoActual = userLogueado;
		this.solicitudAEditar = solicitudAEditar;
		construirVentana();

		cmbArea.setSelectedItem(solicitudAEditar.getArea());
		cargarEspecialidades(solicitudAEditar.getArea());
		if (solicitudAEditar.getCargoDeseado() != null) {
			cmbEspecialidad.setSelectedItem(solicitudAEditar.getCargoDeseado());
		}
		cmbJornada.setSelectedItem(capitalizar(solicitudAEditar.getTipoJornada()));
		spnSalarioMin.setValue(solicitudAEditar.getSalarioMinimo());
		spnSalarioMax.setValue(solicitudAEditar.getSalarioMaximo());
		chkMudarse.setSelected(solicitudAEditar.isDispuestoMudarse());
		cmbModalidad.setSelectedItem(capitalizar(solicitudAEditar.getModalidad()));
	}

	private void construirVentana() {
		setTitle(solicitudAEditar == null ? "Crear Solicitud de Empleo" : "Modificar Solicitud de Empleo");
		setResizable(false);
		setBounds(100, 100, 489, 334);
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
		lblArea.setBounds(20, 20, 100, 16);
		panelDatos.add(lblArea);

		cmbArea = new JComboBox<>();
		cmbArea.addItem("Tecnologia");
		cmbArea.addItem("Salud");
		cmbArea.addItem("Administracion");
		cmbArea.addItem("Construccion");
		cmbArea.addItem("Educacion");
		cmbArea.addItem("Ventas");
		cmbArea.addItem("Servicios Generales");
		cmbArea.setBounds(20, 38, 200, 26);
		panelDatos.add(cmbArea);

		JLabel lblCargo = new JLabel("Cargo deseado:");
		lblCargo.setBounds(240, 20, 150, 16);
		panelDatos.add(lblCargo);

		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setBounds(20, 84, 100, 16);
		panelDatos.add(lblJornada);

		cmbJornada = new JComboBox<>();
		cmbJornada.addItem("Tiempo completo");
		cmbJornada.addItem("Tiempo parcial");
		cmbJornada.addItem("Por hora");
		cmbJornada.setBounds(20, 102, 200, 26);
		panelDatos.add(cmbJornada);

		JLabel lblSalarioMin = new JLabel("Salario minimo:");
		lblSalarioMin.setBounds(20, 156, 120, 16);
		panelDatos.add(lblSalarioMin);

		cmbArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarEspecialidades(cmbArea.getSelectedItem().toString());
			}
		});

		spnSalarioMin = new JSpinner();
		spnSalarioMin.setModel(new SpinnerNumberModel(0, 0, null, 500));
		spnSalarioMin.setBounds(20, 174, 110, 26);
		panelDatos.add(spnSalarioMin);

		JLabel lblSalarioMax = new JLabel("Salario maximo:");
		lblSalarioMax.setBounds(178, 156, 120, 16);
		panelDatos.add(lblSalarioMax);

		spnSalarioMax = new JSpinner();
		spnSalarioMax.setModel(new SpinnerNumberModel(0, 0, null, 500));
		spnSalarioMax.setBounds(178, 174, 110, 26);
		panelDatos.add(spnSalarioMax);

		chkMudarse = new JCheckBox("Dispuesto a mudarse");
		chkMudarse.setBackground(Color.WHITE);
		chkMudarse.setBounds(304, 175, 150, 24);
		panelDatos.add(chkMudarse);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(70, 225, 100, 40);
		panelDatos.add(btnCancelar);

		JButton btnGuardar = new JButton(solicitudAEditar == null ? "Crear Solicitud" : "Actualizar Solicitud");
		btnGuardar.setBackground(new Color(30, 144, 255));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String area = cmbArea.getSelectedItem().toString();
				String especialidad = (String) cmbEspecialidad.getSelectedItem();
				String jornada = cmbJornada.getSelectedItem().toString();
				String modalidad = cmbModalidad.getSelectedItem().toString();
				float salMin = ((Number) spnSalarioMin.getValue()).floatValue();
				float salMax = ((Number) spnSalarioMax.getValue()).floatValue();
				boolean mudarse = chkMudarse.isSelected();


				if (especialidad.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecciona la especialidad deseada.",
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
					Solicitud nuevaSolicitud = new Solicitud(codigoSol, candidatoActual, area, especialidad,
							modalidad, jornada, salMin, salMax, mudarse);
					BolsaLaboral.getInstancia().registrarSolicitud(nuevaSolicitud);
					JOptionPane.showMessageDialog(null, "Solicitud registrada con exito!",
							"Exito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					solicitudAEditar.setArea(area);
					solicitudAEditar.setCargoDeseado(especialidad);
					solicitudAEditar.setTipoJornada(jornada);
					solicitudAEditar.setModalidad(modalidad);
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
		btnGuardar.setBounds(190, 225, 200, 40);
		panelDatos.add(btnGuardar);

		cmbEspecialidad = new JComboBox<String>();
		cmbEspecialidad.setBounds(240, 38, 200, 26);
		panelDatos.add(cmbEspecialidad);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setBounds(240, 84, 100, 16);
		panelDatos.add(lblModalidad);

		cmbModalidad = new JComboBox<String>();
		cmbModalidad.setBounds(240, 102, 200, 26);
		cmbModalidad.addItem("Presencial");
		cmbModalidad.addItem("Remoto");
		cmbModalidad.addItem("Hibrido");
		panelDatos.add(cmbModalidad);

		cargarEspecialidades(cmbArea.getSelectedItem().toString());

	}

	private String capitalizar(String texto) {
		if (texto == null || texto.isEmpty()) return texto;
		return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
	}

	private void cargarEspecialidades(String area) {
		cmbEspecialidad.removeAllItems();

		if (area.equalsIgnoreCase("Tecnologia")) {
			cmbEspecialidad.addItem("Desarrollo de Software");
			cmbEspecialidad.addItem("Redes y Telecomunicaciones");
			cmbEspecialidad.addItem("Ciberseguridad");
			cmbEspecialidad.addItem("Soporte Tecnico");
			cmbEspecialidad.addItem("Bases de Datos");
			cmbEspecialidad.addItem("Diseno Web / UX");
			cmbEspecialidad.addItem("Analisis de Datos");

		} else if (area.equalsIgnoreCase("Salud")) {
			cmbEspecialidad.addItem("Enfermeria");
			cmbEspecialidad.addItem("Medicina General");
			cmbEspecialidad.addItem("Odontologia");
			cmbEspecialidad.addItem("Farmacia");
			cmbEspecialidad.addItem("Laboratorio Clinico");
			cmbEspecialidad.addItem("Fisioterapia");
			cmbEspecialidad.addItem("Psicologia Clinica");

		} else if (area.equalsIgnoreCase("Administracion")) {
			cmbEspecialidad.addItem("Contabilidad");
			cmbEspecialidad.addItem("Recursos Humanos");
			cmbEspecialidad.addItem("Mercadeo y Publicidad");
			cmbEspecialidad.addItem("Finanzas");
			cmbEspecialidad.addItem("Logistica y Cadena de Suministro");
			cmbEspecialidad.addItem("Administracion de Empresas");

		} else if (area.equalsIgnoreCase("Construccion")) {
			cmbEspecialidad.addItem("Albanileria");
			cmbEspecialidad.addItem("Electricidad Industrial / Residencial");
			cmbEspecialidad.addItem("Plomeria");
			cmbEspecialidad.addItem("Carpinteria");
			cmbEspecialidad.addItem("Refrigeracion y Aire Acondicionado");
			cmbEspecialidad.addItem("Mecanica Automotriz");
			cmbEspecialidad.addItem("Supervision de Obra");

		} else if (area.equalsIgnoreCase("Educacion")) {
			cmbEspecialidad.addItem("Educacion Inicial / Parvularia");
			cmbEspecialidad.addItem("Docencia Primaria");
			cmbEspecialidad.addItem("Docencia Secundaria");
			cmbEspecialidad.addItem("Docencia Universitaria");
			cmbEspecialidad.addItem("Educacion Especial");
			cmbEspecialidad.addItem("Orientacion / Psicologia Escolar");

		} else if (area.equalsIgnoreCase("Ventas")) {
			cmbEspecialidad.addItem("Ventas al Detalle");
			cmbEspecialidad.addItem("Ventas Corporativas");
			cmbEspecialidad.addItem("Atencion al Cliente");
			cmbEspecialidad.addItem("Telemercadeo");
			cmbEspecialidad.addItem("Cajeros / Facturacion");

		} else if (area.equalsIgnoreCase("Servicios Generales")) {
			cmbEspecialidad.addItem("Limpieza y Conserjeria");
			cmbEspecialidad.addItem("Mantenimiento General");
			cmbEspecialidad.addItem("Seguridad / Vigilancia");
			cmbEspecialidad.addItem("Jardineria");
			cmbEspecialidad.addItem("Chofer / Conductor");
			cmbEspecialidad.addItem("Ayudante General");
		}
	}
}