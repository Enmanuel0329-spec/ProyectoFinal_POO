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
import logico.Empresa;
import logico.Solicitud;

public class ListarMisEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private static DefaultTableModel model;
	private static Object[] row;
	private Empresa empresaActual;
	private Solicitud selected;
	private JButton btnDespedir;

	public ListarMisEmpleados(Empresa empresaActual) {
		this.empresaActual = empresaActual;

		setResizable(false);
		setTitle("Mis Empleados");
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
					String[] headers = {"Codigo Solicitud", "Nombre", "Cargo", "Area", "Nivel"};
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
								btnDespedir.setEnabled(selected != null);
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
				btnDespedir = new JButton("Despedir");
				btnDespedir.setForeground(new Color(255, 255, 255));
				btnDespedir.setBackground(new Color(220, 20, 60));
				btnDespedir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							int confirmacion = JOptionPane.showConfirmDialog(null,
									"Estas seguro que deseas despedir a " + selected.getSolicitante().getNombre() + "?",
									"Confirmar Despido", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if (confirmacion == JOptionPane.YES_OPTION) {
								BolsaLaboral.getInstancia().despedirCandidato(selected);
								JOptionPane.showMessageDialog(null,
										selected.getSolicitante().getNombre() + " ha sido despedido satisfactoriamente.",
										"Despido realizado", JOptionPane.INFORMATION_MESSAGE);
								btnDespedir.setEnabled(false);
								cargarEmpleados();
							}
						}
					}
				});
				btnDespedir.setEnabled(false);
				buttonPane.add(btnDespedir);
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
		cargarEmpleados();
	}

	private void cargarEmpleados() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		ArrayList<Solicitud> empleados = BolsaLaboral.getInstancia().empleadosPorEmpresa(empresaActual);
		for (Solicitud s : empleados) {
			row[0] = s.getCodigo();
			row[1] = s.getSolicitante().getNombre();
			row[2] = s.getCargoDeseado();
			row[3] = s.getArea();
			row[4] = s.getSolicitante().getClass().getSimpleName();
			model.addRow(row);
		}
	}
}