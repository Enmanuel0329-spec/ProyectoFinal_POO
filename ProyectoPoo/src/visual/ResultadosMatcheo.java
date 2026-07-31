package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logico.BolsaLaboral;
import logico.Empresa;
import logico.Oferta;
import logico.ResultadoMatcheo;
import logico.Solicitud;
import logico.Universitario;

public class ResultadosMatcheo extends JDialog {

	private Oferta oferta;
	private JTable tablaCandidatos;
	private DefaultTableModel modeloTabla;
	private ArrayList<ResultadoMatcheo> resultadosActuales;
	private JButton btnContratar;
	private JLabel lblPuestosDisponibles;

	public static void main(String[] args) {
		BolsaLaboral bolsa = BolsaLaboral.getInstancia();

		Empresa emp = new Empresa("RNC-999", "EmpresaPrueba", "Test", "Direccion", "000-000-0000", "test@test.com", "Tecnologia");
		bolsa.registrarEmpresa(emp);

		Oferta ofertaPrueba = new Oferta("O-TEST", "Tecnologia", "Desarrollador de Software", "Sistemas",
				2, "cualquiera", "Universitario", false, false, "Remoto",
				"Tiempo Completo", "Santiago", 30000f, 60000f, 60f, new java.util.Date(), emp);
		bolsa.registrarOferta(ofertaPrueba);

		Universitario uni = new Universitario("P-1", "Maria Lopez", "maria@gmail.com", "829-555-1234",
				true, "Centro", "Santiago", "Femenino", true, "Ingenieria de Sistemas");
		bolsa.registrarPersona(uni);

		Solicitud solMaria = new Solicitud("SOL-1", uni, "Tecnologia", "Desarrollador", "Tiempo Completo",
				40000f, 70000f, false);
		bolsa.registrarSolicitud(solMaria);

		ResultadosMatcheo dialog = new ResultadosMatcheo(ofertaPrueba);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	public ResultadosMatcheo(Oferta oferta) {
		this.oferta = oferta;
		setTitle("Resultados del Matcheo");
		setResizable(false);
		setBounds(100, 100, 650, 420);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(30, 144, 255));
		panelSuperior.setLayout(new BorderLayout());
		panelSuperior.setBorder(new EmptyBorder(10, 15, 10, 15));
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Vacante: " + oferta.getDescripcionPuesto());
		lblTitulo.setForeground(Color.WHITE);
		panelSuperior.add(lblTitulo, BorderLayout.WEST);

		lblPuestosDisponibles = new JLabel("Puestos disponibles: " + oferta.getCantidadPuestos());
		lblPuestosDisponibles.setForeground(Color.WHITE);
		panelSuperior.add(lblPuestosDisponibles, BorderLayout.EAST);

		String[] columnas = {"Candidato", "Nivel", "Cargo deseado", "Provincia", "Coincidencia"};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		tablaCandidatos = new JTable(modeloTabla);
		tablaCandidatos.setRowHeight(28);
		tablaCandidatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scroll = new JScrollPane(tablaCandidatos);
		scroll.setBorder(new EmptyBorder(10, 15, 10, 15));
		getContentPane().add(scroll, BorderLayout.CENTER);

		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panelInferior, BorderLayout.SOUTH);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelInferior.add(btnCerrar);

		btnContratar = new JButton("Contratar seleccionado");
		btnContratar.setBackground(new Color(30, 144, 255));
		btnContratar.setForeground(Color.WHITE);
		btnContratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contratarSeleccionado();
			}
		});
		panelInferior.add(btnContratar);

		cargarResultados();
	}

	private void cargarResultados() {
		modeloTabla.setRowCount(0);
		resultadosActuales = BolsaLaboral.getInstancia().matcheoCandidatosParaOferta(oferta);

		if (resultadosActuales.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"No se encontraron candidatos que cumplan con el minimo de coincidencia.",
					"Sin resultados", JOptionPane.INFORMATION_MESSAGE);
			
		}

		int i = 0;
		while (i < resultadosActuales.size()) {
			ResultadoMatcheo rm = resultadosActuales.get(i);
			Object[] fila = {
					rm.getSolicitud().getSolicitante().getNombre(),
					rm.getSolicitud().getSolicitante().getClass().getSimpleName(),
					rm.getSolicitud().getCargoDeseado(),
					rm.getSolicitud().getSolicitante().getProvincia(),
					String.format("%.2f", rm.getPorcentaje()) + "%"
			};
			modeloTabla.addRow(fila);
			i++;
		}

		lblPuestosDisponibles.setText("Puestos disponibles: " + oferta.getCantidadPuestos());
		btnContratar.setEnabled(oferta.isActiva() && oferta.getCantidadPuestos() > 0);
	}

	private void contratarSeleccionado() {
		int filaSeleccionada = tablaCandidatos.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this,
					"Selecciona un candidato de la tabla primero.",
					"Ningun candidato seleccionado", JOptionPane.WARNING_MESSAGE);
			return;
		}

		ResultadoMatcheo seleccionado = resultadosActuales.get(filaSeleccionada);
		String nombreCandidato = seleccionado.getSolicitud().getSolicitante().getNombre();

		int confirmacion = JOptionPane.showConfirmDialog(this,
				"Contratar a " + nombreCandidato + "?",
				"Confirmar contratacion", JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			BolsaLaboral.getInstancia().contratarCandidato(oferta, seleccionado.getSolicitud());
			JOptionPane.showMessageDialog(this,
					nombreCandidato + " ha sido contratado.",
					"Contratacion exitosa", JOptionPane.INFORMATION_MESSAGE);
			cargarResultados();
		}
	}
}