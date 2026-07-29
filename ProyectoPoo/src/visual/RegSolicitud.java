package visual;

import logico.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RegSolicitud extends JDialog {
    
    private Persona candidatoActual; 
    private Solicitud solicitudExistente = null;  
    
    private JTextField txtArea;
    private JTextField txtCargo;
    private JComboBox<String> cbxJornada;
    private JSpinner spnSalarioMin;
    private JSpinner spnSalarioMax;
    private JCheckBox chkMudarse;

    public RegSolicitud(Persona userLogueado) {
        this.candidatoActual = userLogueado;
        
        setTitle("Gestion de Solicitud de Empleo");
        setBounds(100, 100, 700, 500);
        getContentPane().setLayout(null);
        
        ArrayList<Solicitud> misSolicitudes = BolsaLaboral.getInstancia().solicitudesPorPersona(candidatoActual);
        for (Solicitud s : misSolicitudes) 
        {
            if (s.getEstado().equalsIgnoreCase("activa")) 
            {
                solicitudExistente = s;
                break;  
            }}
 
        if (solicitudExistente != null) 
        {        	
            txtArea.setText(solicitudExistente.getArea());
            txtCargo.setText(solicitudExistente.getCargoDeseado());
            cbxJornada.setSelectedItem(solicitudExistente.getTipoJornada());
            spnSalarioMin.setValue(solicitudExistente.getSalarioMinimo());
            spnSalarioMax.setValue(solicitudExistente.getSalarioMaximo());
            chkMudarse.setSelected(solicitudExistente.isDispuestoMudarse());
            
        }
         
        JButton btnGuardar = new JButton(solicitudExistente == null ? "Crear Solicitud" : "Actualizar Solicitud");
        btnGuardar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                
                try 
                { 
                    String area = txtArea.getText().trim();
                    String cargo = txtCargo.getText().trim();
                    String jornada = cbxJornada.getSelectedItem().toString();
                    float salMin = Float.parseFloat(spnSalarioMin.getValue().toString());
                    float salMax = Float.parseFloat(spnSalarioMax.getValue().toString());
                    boolean mudarse = chkMudarse.isSelected();
                     
                    if(area.isEmpty() || cargo.isEmpty()) 
                    {
                        JOptionPane.showMessageDialog(null, "No pueden presentarse campos vacios.", "Atencion", JOptionPane.WARNING_MESSAGE);
                        return;  
                    }
                    if(salMin <= 0 || salMax <= 0) 
                    {
                        JOptionPane.showMessageDialog(null, "El salario debe ser mayor a cero.", "Atencion", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(salMin > salMax) 
                    {
                        JOptionPane.showMessageDialog(null, "El salario minimo no puede ser mayor que el maximo.", "Error Logico", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
 
                    if (solicitudExistente == null) 
                    { 
                        String codigoSol = "SOL-" + BolsaLaboral.generadorIdSolicitud;
                        Solicitud nuevaSolicitud = new Solicitud(codigoSol, candidatoActual, area, cargo, 
                                                                 jornada, salMin, salMax, mudarse);
                        BolsaLaboral.getInstancia().registrarSolicitud(nuevaSolicitud);
                        JOptionPane.showMessageDialog(null, "¡Solicitud registrada con exito!", "exito", JOptionPane.INFORMATION_MESSAGE);
                    } else{ 
                        solicitudExistente.setArea(area);
                        solicitudExistente.setCargoDeseado(cargo);
                        solicitudExistente.setTipoJornada(jornada);
                        solicitudExistente.setSalarioMinimo(salMin);
                        solicitudExistente.setSalarioMaximo(salMax);
                        solicitudExistente.setDispuestoMudarse(mudarse);
                        JOptionPane.showMessageDialog(null, "¡Tu solicitud ha sido actualizada!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }                    
                    dispose();  
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Por favor verifique que los valores sean correctos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnGuardar.setBounds(250, 400, 150, 40);
        getContentPane().add(btnGuardar);
    }
}