package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaLaboral;
import logico.Solicitud;

public class ListarSolicitudes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private static DefaultTableModel model;
	private static Object[] row;

	public static void main(String[] args) {
		try {
			ListarSolicitudes dialog = new ListarSolicitudes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarSolicitudes() {
		setResizable(false);
		setTitle("Lista de Solicitudes");
		setBounds(100, 100, 780, 400);
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
					String[] headers = {"Codigo", "Candidato", "Area", "Cargo deseado",
							"Jornada", "Salario Min", "Salario Max", "Estado"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table = new JTable();
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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

	public static void loadSolicitudes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Solicitud s : BolsaLaboral.getInstancia().getLasSolicitudes()) {
			row[0] = s.getCodigo();
			row[1] = s.getSolicitante().getNombre();
			row[2] = s.getArea();
			row[3] = s.getCargoDeseado();
			row[4] = s.getTipoJornada();
			row[5] = s.getSalarioMinimo();
			row[6] = s.getSalarioMaximo();
			row[7] = s.getEstado();
			model.addRow(row);
		}
	}
}