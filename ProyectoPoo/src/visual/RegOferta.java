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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import logico.BolsaLaboral;
import logico.Empresa;
import logico.Oferta;

public class RegOferta extends JDialog {

	private JTextField txtDescripcion;
	private JTextField txtCodigo;
	private JSpinner spnCantidad;
	private JSpinner spnSalarioMin;
	private JSpinner spnSalarioMax;
	private JSpinner spnPorcentaje;
	private JComboBox<String> cmbNivel;
	private JComboBox<String> cmbSexo;
	private JComboBox<String> cmbJornada;
	private JComboBox<String> cmbProvincia;
	private JComboBox<String> cmbModalidad;
	private JComboBox<String> cmbArea;
	private JComboBox<String> cmbEspecialidad;
	private JCheckBox chkLicencia;
	private JCheckBox chkMudarse;
	private Empresa empresa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegOferta dialog = new RegOferta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegOferta(Empresa empresa) {
		this.empresa = empresa;
		setTitle("Registrar Oferta");
		setResizable(false);
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Datos generales", TitledBorder.CENTER, TitledBorder.TOP));
		panelDatos.setLayout(null);
		panelDatos.setBounds(10, 10, 560, 190);
		panel.add(panelDatos);

		JLabel lblDescripcion = new JLabel("Descripcion del puesto:");
		lblDescripcion.setBounds(10, 20, 160, 16);
		panelDatos.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(10, 38, 250, 26);
		panelDatos.add(txtDescripcion);

		JLabel lblCantidad = new JLabel("Cantidad de puestos:");
		lblCantidad.setBounds(270, 20, 140, 16);
		panelDatos.add(lblCantidad);

		spnCantidad = new JSpinner();
		spnCantidad.setModel(new SpinnerNumberModel(1, 1, null, 1));
		spnCantidad.setBounds(270, 38, 80, 26);
		panelDatos.add(spnCantidad);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(420, 20, 60, 16);
		panelDatos.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(420, 38, 120, 26);
		txtCodigo.setText("O-" + BolsaLaboral.generadorIdOferta);
		panelDatos.add(txtCodigo);

		JLabel lblNivel = new JLabel("Nivel academico:");
		lblNivel.setBounds(10, 78, 120, 16);
		panelDatos.add(lblNivel);

		cmbNivel = new JComboBox<>();
		cmbNivel.addItem("Universitario");
		cmbNivel.addItem("Tecnico");
		cmbNivel.addItem("Obrero");
		cmbNivel.setBounds(10, 96, 160, 26);
		panelDatos.add(cmbNivel);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(185, 78, 60, 16);
		panelDatos.add(lblSexo);

		cmbSexo = new JComboBox<>();
		cmbSexo.addItem("Cualquiera");
		cmbSexo.addItem("Masculino");
		cmbSexo.addItem("Femenino");
		cmbSexo.setBounds(185, 96, 160, 26);
		panelDatos.add(cmbSexo);

		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setBounds(360, 78, 80, 16);
		panelDatos.add(lblJornada);

		cmbJornada = new JComboBox<>();
		cmbJornada.addItem("Tiempo completo");
		cmbJornada.addItem("Tiempo parcial");
		cmbJornada.addItem("Por hora");
		cmbJornada.setBounds(360, 96, 180, 26);
		panelDatos.add(cmbJornada);

		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(10, 136, 100, 16);
		panelDatos.add(lblArea);

		cmbArea = new JComboBox<>();
		
		cmbArea.addItem("Tecnologia");
		cmbArea.addItem("Salud");
		cmbArea.addItem("Administracion");
		cmbArea.addItem("Construccion");
		cmbArea.addItem("Educacion");
		cmbArea.addItem("Ventas");
		cmbArea.setBounds(10, 154, 180, 26);
		panelDatos.add(cmbArea);

		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(200, 136, 150, 16);
		panelDatos.add(lblEspecialidad);

		cmbEspecialidad = new JComboBox<>();
		cmbEspecialidad.setBounds(200, 154, 200, 26);
		panelDatos.add(cmbEspecialidad);

		cmbArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarEspecialidades(cmbArea.getSelectedItem().toString());
			}
		});
		
		JPanel panelSalario = new JPanel();
		panelSalario.setBackground(Color.WHITE);
		panelSalario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Salario y ubicacion", TitledBorder.CENTER, TitledBorder.TOP));
		panelSalario.setLayout(null);
		panelSalario.setBounds(10, 210, 560, 80);
		panel.add(panelSalario);

		JLabel lblMin = new JLabel("Salario minimo:");
		lblMin.setBounds(10, 20, 100, 16);
		panelSalario.add(lblMin);

		spnSalarioMin = new JSpinner();
		spnSalarioMin.setModel(new SpinnerNumberModel(0, 0, null, 500));
		spnSalarioMin.setBounds(10, 38, 110, 26);
		panelSalario.add(spnSalarioMin);

		JLabel lblMax = new JLabel("Salario maximo:");
		lblMax.setBounds(135, 20, 100, 16);
		panelSalario.add(lblMax);

		spnSalarioMax = new JSpinner();
		spnSalarioMax.setModel(new SpinnerNumberModel(0, 0, null, 500));
		spnSalarioMax.setBounds(135, 38, 110, 26);
		panelSalario.add(spnSalarioMax);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(260, 20, 80, 16);
		panelSalario.add(lblProvincia);

		cmbProvincia = new JComboBox<>();
		cmbProvincia.addItem("Santiago");
		cmbProvincia.addItem("Santo Domingo");
		cmbProvincia.addItem("La Vega");
		cmbProvincia.addItem("Puerto Plata");
		cmbProvincia.setBounds(260, 38, 140, 26);
		panelSalario.add(cmbProvincia);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setBounds(415, 20, 80, 16);
		panelSalario.add(lblModalidad);

		cmbModalidad = new JComboBox<>();
		cmbModalidad.addItem("Presencial");
		cmbModalidad.addItem("Remoto");
		cmbModalidad.addItem("Hibrido");
		cmbModalidad.setBounds(415, 38, 130, 26);
		panelSalario.add(cmbModalidad);

		JPanel panelReq = new JPanel();
		panelReq.setBackground(Color.WHITE);
		panelReq.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Requerimientos", TitledBorder.CENTER, TitledBorder.TOP));
		panelReq.setLayout(null);
		panelReq.setBounds(10, 300, 560, 70);
		panel.add(panelReq);

		chkLicencia = new JCheckBox("Requiere licencia de conducir");
		chkLicencia.setBackground(Color.WHITE);
		chkLicencia.setBounds(10, 28, 200, 24);
		panelReq.add(chkLicencia);

		chkMudarse = new JCheckBox("Requiere mudarse");
		chkMudarse.setBackground(Color.WHITE);
		chkMudarse.setBounds(220, 28, 165, 24);
		panelReq.add(chkMudarse);

		JLabel lblPorcentaje = new JLabel("Coincidencia minima (%):");
		lblPorcentaje.setBounds(390, 15, 160, 16);
		panelReq.add(lblPorcentaje);

		spnPorcentaje = new JSpinner();
		spnPorcentaje.setModel(new SpinnerNumberModel(75, 0, 100, 5));
		spnPorcentaje.setBounds(454, 32, 80, 26);
		panelReq.add(spnPorcentaje);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(new Color(30, 144, 255));
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDescripcion.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, 
							"Escribe la descripcion del puesto.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String nivel = cmbNivel.getSelectedItem().toString().toLowerCase();
				String sexo = cmbSexo.getSelectedItem().toString().toLowerCase();
				String jornada = cmbJornada.getSelectedItem().toString().toLowerCase();
				String provincia = cmbProvincia.getSelectedItem().toString();
				String modalidad = cmbModalidad.getSelectedItem().toString().toLowerCase();
				String area = cmbArea.getSelectedItem().toString();
				String especialidad = cmbEspecialidad.getSelectedItem().toString();
				float salMin = ((Number) spnSalarioMin.getValue()).floatValue();
				float salMax = ((Number) spnSalarioMax.getValue()).floatValue();
				float porcentaje = ((Number) spnPorcentaje.getValue()).floatValue();
				int cantidad = (Integer) spnCantidad.getValue();

				Oferta oferta = new Oferta(
						txtCodigo.getText(),
						area,
						txtDescripcion.getText(),
						especialidad,
						cantidad,
						sexo,
						nivel,
						chkLicencia.isSelected(),
						chkMudarse.isSelected(),
						modalidad,
						jornada,
						provincia,
						salMin,
						salMax,
						porcentaje,
						new Date(),
						empresa
						);

				BolsaLaboral.getInstancia().registrarOferta(oferta);
				JOptionPane.showMessageDialog(null, "Oferta registrada!", 
						"Informacion", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);
		cargarEspecialidades(cmbArea.getSelectedItem().toString());
	}

	private void cargarEspecialidades(String area) {
		cmbEspecialidad.removeAllItems();

		if (area.equalsIgnoreCase("Tecnologia")) {
			cmbEspecialidad.addItem("Desarrollo de Software");
			cmbEspecialidad.addItem("Redes");
			cmbEspecialidad.addItem("Ciberseguridad");
			cmbEspecialidad.addItem("Soporte Tecnico");
			cmbEspecialidad.addItem("Bases de Datos");
		} else if (area.equalsIgnoreCase("Salud")) {
			cmbEspecialidad.addItem("Enfermeria");
			cmbEspecialidad.addItem("Medicina General");
			cmbEspecialidad.addItem("Farmacia");
			cmbEspecialidad.addItem("Laboratorio Clinico");
			cmbEspecialidad.addItem("Fisioterapia");
		} else if (area.equalsIgnoreCase("Administracion")) {
			cmbEspecialidad.addItem("Contabilidad");
			cmbEspecialidad.addItem("Recursos Humanos");
			cmbEspecialidad.addItem("Mercadeo");
			cmbEspecialidad.addItem("Finanzas");
			cmbEspecialidad.addItem("Logistica");
		} else if (area.equalsIgnoreCase("Construccion")) {
			cmbEspecialidad.addItem("Albanileria");
			cmbEspecialidad.addItem("Electricidad");
			cmbEspecialidad.addItem("Plomeria");
			cmbEspecialidad.addItem("Carpinteria");
			cmbEspecialidad.addItem("Supervision de Obra");
		} else if (area.equalsIgnoreCase("Educacion")) {
			cmbEspecialidad.addItem("Docencia Primaria");
			cmbEspecialidad.addItem("Docencia Secundaria");
			cmbEspecialidad.addItem("Educacion Especial");
			cmbEspecialidad.addItem("Psicologia Escolar");
		} else if (area.equalsIgnoreCase("Ventas")) {
			cmbEspecialidad.addItem("Ventas al Detalle");
			cmbEspecialidad.addItem("Ventas Corporativas");
			cmbEspecialidad.addItem("Atencion al Cliente");
			cmbEspecialidad.addItem("Telemercadeo");
		}
	}

}