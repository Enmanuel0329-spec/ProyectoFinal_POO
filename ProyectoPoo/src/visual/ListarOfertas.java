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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaLaboral;
import logico.Empresa;
import logico.Oferta;
import logico.Persona;

public class ListarOfertas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private static DefaultTableModel model;
	private static Object[] row;
	private Oferta selected;

	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAplicar;
	private JButton btnMatchear;
	
	private boolean esEmpresa;
	private boolean esAdmin;
	private Empresa empresaActual;
	private Persona candidatoActual;

	public static void main(String[] args) {
		try {
			ListarOfertas dialog = new ListarOfertas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarOfertas(Object usuarioLogueado) {
		if (usuarioLogueado == null) {
			esAdmin = true;
		}{
			esEmpresa = usuarioLogueado instanceof Empresa;
			if (esEmpresa) {
				empresaActual = (Empresa) usuarioLogueado;
			} else if (usuarioLogueado instanceof Persona) {
				candidatoActual = (Persona) usuarioLogueado;
			}
		}
		

		setResizable(false);
		setTitle(esEmpresa ? "Mis Ofertas" : "Catalogo de Ofertas");
		setBounds(100, 100, 780, 420);
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
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Codigo", "Puesto", "Area", "Jornada", "Provincia",
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
								selected = BolsaLaboral.getInstancia().buscarOferta(codigo);

								if (esEmpresa || esAdmin) {
									btnModificar.setEnabled(true);
									btnEliminar.setEnabled(true);
									if (esEmpresa) {
					                    btnMatchear.setEnabled(true);
					                }
								} else {
									btnAplicar.setEnabled(selected != null && selected.isActiva()
											&& selected.getCantidadPuestos() > 0);
								}
							}
						}
					});
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			if (esEmpresa || esAdmin) {
				
				if (esEmpresa) {					
					btnMatchear = new JButton("Buscar candidatos");
					btnMatchear.setForeground(new Color(255, 255, 255));
					btnMatchear.setBackground(new Color(100, 237, 149));
					btnMatchear.setEnabled(false);
					btnMatchear.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (selected != null) {
								ResultadosMatcheo dialog = new ResultadosMatcheo(selected);
								dialog.setModal(true);
								dialog.setVisible(true);
							}
						}
					});
					buttonPane.add(btnMatchear);
				}
				
				btnModificar = new JButton("Modificar");
				btnModificar.setForeground(new Color(255, 255, 255));
				btnModificar.setBackground(new Color(100, 149, 237));
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							Empresa empresaDeLaOferta = esAdmin ? selected.getEmpresa() : empresaActual;
							RegOferta reg = new RegOferta(empresaDeLaOferta, selected);
							reg.setModal(true);
							reg.setVisible(true);
							cargarOfertas();
						}
					}
				});
				
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);

				btnEliminar = new JButton("Eliminar");
				btnEliminar.setForeground(new Color(255, 255, 255));
				btnEliminar.setBackground(new Color(220, 20, 60));
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							int confirmacion = JOptionPane.showConfirmDialog(null,
									"Estas seguro que deseas eliminar la oferta " + selected.getCodigo() + "?",
									"Eliminar Oferta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if (confirmacion == JOptionPane.YES_OPTION) {
								BolsaLaboral.getInstancia().eliminarOferta(selected);
								btnModificar.setEnabled(false);
								btnEliminar.setEnabled(false);
								
								if (esEmpresa) {
				                    btnMatchear.setEnabled(false);
				                }

								cargarOfertas();
							}
						}
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);

			} else {
				btnAplicar = new JButton("Aplicar Solicitud");
				btnAplicar.setForeground(new Color(255, 255, 255));
				btnAplicar.setBackground(new Color(30, 144, 255));
				btnAplicar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null && candidatoActual != null) {
							RegSolicitud reg = new RegSolicitud(candidatoActual, selected);
							reg.setModal(true);
							reg.setVisible(true);
							dispose();
						}
					}
				});
				btnAplicar.setEnabled(false);
				buttonPane.add(btnAplicar);
			}

			JButton cancelButton = new JButton("Cerrar");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPane.add(cancelButton);
		}

		cargarOfertas();
	}

	private void cargarOfertas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];

		ArrayList<Oferta> ofertas;
		if (esEmpresa) {
			ofertas = BolsaLaboral.getInstancia().ofertasPorEmpresa(empresaActual);
		}
		else if (esAdmin) {
	        ofertas = BolsaLaboral.getInstancia().getLasOfertas();
		}
		else {
			ofertas = new ArrayList<Oferta>();
			for (Oferta o : BolsaLaboral.getInstancia().getLasOfertas()) {
				if (o.isActiva() && o.getCantidadPuestos() > 0) {
					ofertas.add(o);
				}
			}
		}

		for (Oferta o : ofertas) {
			row[0] = o.getCodigo();
			row[1] = o.getDescripcionPuesto();
			row[2] = o.getArea();
			row[3] = o.getTipoJornada();
			row[4] = o.getProvincia();
			row[5] = o.getSalarioMinimo();
			row[6] = o.getSalarioMaximo();
			row[7] = o.isActiva() ? "Activa" : "Cerrada";
			model.addRow(row);
		}
	}
}