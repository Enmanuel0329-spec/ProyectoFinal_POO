package logico;

import java.util.ArrayList;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 
import javax.swing.JButton;                 

public class MatcHilado extends Thread {
    
    private Oferta ofertaAnalizar;
    private JProgressBar barraProgreso;
    private DefaultTableModel modeloTabla;  
    private JButton btnContratar;           


    public MatcHilado(Oferta oferta, JProgressBar barra, DefaultTableModel modelo, JButton btn) 
    {
        this.ofertaAnalizar = oferta;
        this.barraProgreso = barra;
        this.modeloTabla = modelo;
        this.btnContratar = btn;
    }

    @Override
    public void run() 
    {
        BolsaLaboral bolsa = BolsaLaboral.getInstancia();
        ArrayList<Solicitud> todasLasSolicitudes = bolsa.getLasSolicitudes();

        int total = todasLasSolicitudes.size();
        int procesados = 1;

        // 1. Fase de Simulación y actualización de la Barra
        for (Solicitud sol : todasLasSolicitudes) 
        {
            
            if(!sol.getSolicitante().isDisponible() || sol.getEstado().equalsIgnoreCase("completada")) 
            {
                continue;
            }

            esperarSegundos(1); // Simula la lectura

            int progresoActual = (int) (((float) procesados / total) * 100);
            
            SwingUtilities.invokeLater(new Runnable() 
            {
                public void run() 
                {
                    barraProgreso.setValue(progresoActual);
                }});
            procesados++;
        }

        esperarSegundos(1); 

        ArrayList<ResultadoMatcheo> resultadosFinales = bolsa.matcheoCandidatosParaOferta(ofertaAnalizar);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() 
            {                
                modeloTabla.setRowCount(0); 
                for (ResultadoMatcheo rm : resultadosFinales) 
                {
                    Object[] fila = 
                    	{
                            rm.getSolicitud().getSolicitante().getNombre(), rm.getSolicitud().getSolicitante().getClass().getSimpleName(),
                            rm.getSolicitud().getCargoDeseado(), rm.getSolicitud().getSolicitante().getProvincia(),
                            String.format("%.2f", rm.getPorcentaje()) + "%"
                    };
                    modeloTabla.addRow(fila);
                } 
                btnContratar.setEnabled(!resultadosFinales.isEmpty());                
                JOptionPane.showMessageDialog(null, "¡Proceso completado! Se encontraron " + resultadosFinales.size() + " candidatos.", 
                "Match Finalizado", JOptionPane.INFORMATION_MESSAGE);                
                barraProgreso.setValue(0);
            } });
        }

    private void esperarSegundos(int segundos) 
    {
        try 
        {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }}}