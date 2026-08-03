package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaLaboral;
import logico.Persona;
import logico.Solicitud;

public class ListarMisSolicitudes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private static DefaultTableModel model;
	private static Object[] row;
	private Persona candidatoActual;
	private Solicitud selected;
	private JButton btnModificar;

	public ListarMisSolicitudes(Persona candidatoActual) {
		this.candidatoActual = candidatoActual;

		setResizable(false);
		setTitle("Mis Solicitudes");
		setBounds(100, 100, 700, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Codigo", "Area", "Cargo deseado", "Jornada",
							"Salario Min", "Salario Max", "Estado"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table = new JTable();
					table.setModel(model);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if (index >= 0) {
								String codigo = table.getValueAt(index, 0).toString();
								selected = BolsaLaboral.getInstancia().buscarSolicitud(codigo);
								btnModificar.setEnabled(selected != null
										&& selected.getEstado().equalsIgnoreCase("activa"));
							}
						}
					});
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setForeground(new Color(255, 255, 255));
				btnModificar.setBackground(new Color(100, 149, 237));
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							RegSolicitud reg = new RegSolicitud(candidatoActual, selected);
							reg.setModal(true);
							reg.setVisible(true);
							loadSolicitudes();
						}
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		loadSolicitudes();
	}

	public void loadSolicitudes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		ArrayList<Solicitud> misSolicitudes = BolsaLaboral.getInstancia().solicitudesPorPersona(candidatoActual);
		for (Solicitud s : misSolicitudes) {
			row[0] = s.getCodigo();
			row[1] = s.getArea();
			row[2] = s.getCargoDeseado();
			row[3] = s.getTipoJornada();
			row[4] = s.getSalarioMinimo();
			row[5] = s.getSalarioMaximo();
			row[6] = s.getEstado();
			model.addRow(row);
		}
	}
}