package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Calendar; 
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis; 
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import logico.BolsaLaboral;
import logico.Persona;

public class EvoMesDatos extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public EvoMesDatos() {
        setTitle("Evolución Mensual de Indicadores Laborales");
        setBounds(100, 100, 750, 500); 
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        DefaultCategoryDataset datos = crearDataset();

        JFreeChart graficoLineas = ChartFactory.createLineChart("Tendencia de Empleabilidad y Solicitudes", "Meses", "Cantidad",                                 
                												 datos, PlotOrientation.VERTICAL, true, true, false );
        CategoryPlot plot = graficoLineas.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE); 
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); 
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(255, 69, 0));  
        renderer.setSeriesPaint(1, new Color(34, 139, 34)); 
        renderer.setSeriesPaint(2, new Color(30, 144, 255));
        renderer.setBaseShapesVisible(true);
        ChartPanel panelGrafico = new ChartPanel(graficoLineas);
        contentPanel.add(panelGrafico, BorderLayout.CENTER);
    }

    private DefaultCategoryDataset crearDataset() 
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] mesesDelAnio = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        int desempleadosReales = 0;
        int contratadosReales = 0;

        for (Persona p : BolsaLaboral.getInstancia().getlasPersonas()) 
        {
            if (p.isDisponible()) 
            {
                desempleadosReales++;
            } else {
                contratadosReales++;
            }
        }
        int solicitudesReales = BolsaLaboral.getInstancia().getLasSolicitudes().size();
        for (int i = 0; i < mesesDelAnio.length; i++) 
        {
            
            if (i == mesActual) 
            { 
                dataset.addValue(desempleadosReales, "Desempleados", mesesDelAnio[i]);
                dataset.addValue(contratadosReales, "Contratados", mesesDelAnio[i]);
                dataset.addValue(solicitudesReales, "Solicitudes", mesesDelAnio[i]);
            } 
            else if (i < mesActual) 
            {
            	dataset.addValue(0, "Desempleados", mesesDelAnio[i]);
                dataset.addValue(0, "Contratados", mesesDelAnio[i]);
                dataset.addValue(0, "Solicitudes", mesesDelAnio[i]);
            }
            else 
            {
                dataset.addValue(null, "Desempleados", mesesDelAnio[i]);
                dataset.addValue(null, "Contratados", mesesDelAnio[i]);
                dataset.addValue(null, "Solicitudes", mesesDelAnio[i]);
            }
        }
        return dataset;
    }}