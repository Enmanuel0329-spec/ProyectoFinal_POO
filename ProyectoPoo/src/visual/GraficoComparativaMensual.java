package visual;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import logico.BolsaLaboral;
import logico.Empresa;
import logico.Oferta;
import logico.Solicitud;

public class GraficoComparativaMensual extends JDialog {

	private JPanel panel;
	private static final String[] NOMBRES_MES = {
			"Ene", "Feb", "Mar", "Abr", "May", "Jun",
			"Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
	};

	public GraficoComparativaMensual() {
		setTitle("Comparativa Mensual: Empresas Solicitantes vs Personas Contratadas");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
	}

	private void init() {
		panel = new JPanel();
		getContentPane().add(panel);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int[] empresasPorMes = new int[12];
		int[] contratacionesPorMes = new int[12];

		ArrayList<Oferta> todasLasOfertas = BolsaLaboral.getInstancia().getLasOfertas();
		ArrayList<String>[] empresasContadasPorMes = new ArrayList[12];
		for (int m = 0; m < 12; m++) {
			empresasContadasPorMes[m] = new ArrayList<String>();
		}

		for (Oferta o : todasLasOfertas) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(o.getFecha());
			int mes = cal.get(Calendar.MONTH);

			Empresa emp = o.getEmpresa();
			if (!empresasContadasPorMes[mes].contains(emp.getRnc())) {
				empresasContadasPorMes[mes].add(emp.getRnc());
				empresasPorMes[mes]++;
			}
		}

		ArrayList<Solicitud> todasLasSolicitudes = BolsaLaboral.getInstancia().getLasSolicitudes();
		for (Solicitud s : todasLasSolicitudes) {
			if (s.getEstado().equalsIgnoreCase("completada") && s.getFechaContratacion() != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(s.getFechaContratacion());
				int mes = cal.get(Calendar.MONTH);
				contratacionesPorMes[mes]++;
			}
		}

		for (int m = 0; m < 12; m++) {
			dataset.setValue(empresasPorMes[m], "Empresas Solicitantes", NOMBRES_MES[m]);
			dataset.setValue(contratacionesPorMes[m], "Personas Contratadas", NOMBRES_MES[m]);
		}

		JFreeChart chart = ChartFactory.createBarChart3D(
				"Comparativa Mensual", "Mes", "Cantidad",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.WHITE);
		chart.getTitle().setPaint(Color.BLACK);

		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.LIGHT_GRAY);

		NumberAxis ejeY = (NumberAxis) p.getRangeAxis();
		ejeY.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel chartPanel = new ChartPanel(chart);
		panel.add(chartPanel);
		
	}

	public static void main(String args[]) {
		GraficoComparativaMensual dialog = new GraficoComparativaMensual();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}