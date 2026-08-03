package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


import logico.BolsaLaboral;

public class GraficoEstadisticaAnual extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GraficoEstadisticaAnual dialog = new GraficoEstadisticaAnual();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GraficoEstadisticaAnual() {
		setTitle("Estadistica Anual");
		setBounds(100, 100, 517, 392);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(e -> dispose());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		cargarGrafico();
	}
	public void cargarGrafico() {
		int solicitudQueued= BolsaLaboral.getInstancia().cantSolicitudesPendientes();
		int solicitudComplete =BolsaLaboral.getInstancia().cantSolicitudesCompletados();
		int ofertaQueued = BolsaLaboral.getInstancia().cantOfertasPendientes();
		int ofertaComplete= BolsaLaboral.getInstancia().cantOfertasCompletadas();
		DefaultPieDataset dataset=new DefaultPieDataset();
		
		dataset.setValue("Solicitudes pendiente", solicitudQueued);
		dataset.setValue("Solicitudes completada", solicitudComplete);
		dataset.setValue("Ofertas pendiente", ofertaQueued);
		dataset.setValue("Ofertas completada", ofertaComplete);
		
		JFreeChart graficoCircular = ChartFactory.createPieChart("Estado Anual de Solicitudes y Ofertas",
				dataset, true, true, false);
		
		ChartPanel panelGrafico = new ChartPanel(graficoCircular);
		contentPanel.add(panelGrafico, BorderLayout.CENTER);
	}

}
