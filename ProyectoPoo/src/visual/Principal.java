package visual;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logico.BolsaLaboral;
import logico.Persona;
import logico.RespaldoHilado;
import logico.Empresa;

public class Principal extends JFrame {

    private JPanel contentPane;
    private Dimension dim;
    private JMenu mnCentros;
    private JMenuItem mntmCentConsultar;
    private JMenuItem mntmCentRegistrar; 
    private JMenu mnCandidatos;
    private JMenuItem mntmCandConsultar;
    private JMenuItem mntmCandRegistrar; 
    private JMenu mnCatlogoDeOfertas;
    private JMenuItem mntmCatConsultar;
    private JMenuItem mntmCatRegistrar; 
    private JMenu mnMisSolicitudes;
    private JMenuItem mntmCrearSolicitud;
    private JMenuItem mntmRenunciar;  
    private JMenu mnSolicitudes;
    private JMenuItem mntmSolConsultar;
    private JMenu mnAdministracion;
    private JMenuItem mntmRespaldo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    Principal frame = new Principal(null);
                    frame.setVisible(true);
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }}});
        }

    public Principal(Object usuarioLogueado) 
    {
        setTitle("Bolsa Laboral - Menu Principal");
        setIconImage(Toolkit.getDefaultToolkit().getImage("recursos/icono.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 571, 417);
        dim = super.getToolkit().getScreenSize(); 
        super.setSize(dim.width, dim.height-45);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        mnCentros = new JMenu("Centros de Trabajo");
        mnCentros.setFont(new Font("Arial Narrow", Font.BOLD, 15));
        menuBar.add(mnCentros);
        
        mntmCentConsultar = new JMenuItem("Consultar");
        mntmCentConsultar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListarEmpresas listarEmpresas = new ListarEmpresas();
        		listarEmpresas.setModal(true);
        		listarEmpresas.setVisible(true);
        	}
        });
        mntmCentConsultar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnCentros.add(mntmCentConsultar);
        
        mntmCentRegistrar = new JMenuItem("Registrar");
        mntmCentRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegEmpresa registro = new RegEmpresa();
                registro.setModal(true);
                registro.setVisible(true);
            }
        });
        mntmCentRegistrar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnCentros.add(mntmCentRegistrar);
        
        mnCandidatos = new JMenu("Candidatos");
        mnCandidatos.setFont(new Font("Arial Narrow", Font.BOLD, 15));
        menuBar.add(mnCandidatos);
        
        mntmCandConsultar = new JMenuItem("Consultar");
        mntmCandConsultar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListarCandidatos lista = new ListarCandidatos();
                lista.setModal(true);
                lista.setVisible(true);
        	}
        });
        mntmCandConsultar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnCandidatos.add(mntmCandConsultar);
        
        mntmCandRegistrar = new JMenuItem("Registrar");
        mntmCandRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegPersona reg = new RegPersona();
                reg.setModal(true);
                reg.setVisible(true);
            }
        });
        mntmCandRegistrar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnCandidatos.add(mntmCandRegistrar);
        
        mnCatlogoDeOfertas = new JMenu("Catalogo de Ofertas");
        mnCatlogoDeOfertas.setFont(new Font("Arial Narrow", Font.BOLD, 15));
        menuBar.add(mnCatlogoDeOfertas);
        
        mntmCatConsultar = new JMenuItem("Consultar");
        mntmCatConsultar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListarOfertas listarOfertas = new ListarOfertas(usuarioLogueado);
        		listarOfertas.setModal(true);
        		listarOfertas.setVisible(true);
        	}
        });
        mntmCatConsultar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnCatlogoDeOfertas.add(mntmCatConsultar);
        
        mntmCatRegistrar = new JMenuItem("Registrar");
        mntmCatRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Empresa empresaActual = (Empresa) usuarioLogueado;
                RegOferta reg = new RegOferta(empresaActual);
                reg.setModal(true);
                reg.setVisible(true);
        	}
        });
        mntmCatRegistrar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnCatlogoDeOfertas.add(mntmCatRegistrar);

        mnSolicitudes = new JMenu("Solicitudes");
        mnSolicitudes.setFont(new Font("Arial Narrow", Font.BOLD, 15));
        menuBar.add(mnSolicitudes);

        mntmSolConsultar = new JMenuItem("Consultar");
        mntmSolConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListarSolicitudes lista = new ListarSolicitudes();
                lista.setModal(true);
                lista.setVisible(true);
            }
        });
        mntmSolConsultar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnSolicitudes.add(mntmSolConsultar);
        
        mnMisSolicitudes = new JMenu("Mis Solicitudes");
        mnMisSolicitudes.setFont(new Font("Arial Narrow", Font.BOLD, 15));
        menuBar.add(mnMisSolicitudes);
        
        mntmCrearSolicitud = new JMenuItem("Gestionar mi Solicitud");
        mntmCrearSolicitud.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                RegSolicitud miVentana = new RegSolicitud((Persona) usuarioLogueado);
                miVentana.setModal(true);
                miVentana.setVisible(true);
            }
        });
        mntmCrearSolicitud.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mnMisSolicitudes.add(mntmCrearSolicitud);
        
        mntmRenunciar = new JMenuItem("Renunciar");
        mntmRenunciar.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mntmRenunciar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                int confirm = JOptionPane.showConfirmDialog(null,"¿Estas seguro de que deseas renunciar a tu empleo actual?", "Confirmar Renuncia", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) 
                {
                    Persona candidato = (Persona) usuarioLogueado;
                    candidato.setDisponible(true);
                    BolsaLaboral.getInstancia().procesarRenuncia(candidato);  
                    JOptionPane.showMessageDialog(null, "Has renunciado exitosamente. Vuelves a estar disponible para nuevas ofertas.");
                    aplicarPermisos(usuarioLogueado); 
                }}});
        mnMisSolicitudes.add(mntmRenunciar);
        
        mnAdministracion = new JMenu("Administracion");
        mnAdministracion.setFont(new Font("Arial Narrow", Font.BOLD, 15));
        menuBar.add(mnAdministracion);

        mntmRespaldo = new JMenuItem("Respaldar en la Nube");
        mntmRespaldo.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        mntmRespaldo.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(null, 
                    "Iniciando conexión con el servidor....","Respaldo Iniciado", JOptionPane.INFORMATION_MESSAGE);
                RespaldoHilado hiloNube = new RespaldoHilado();
                hiloNube.start();
            }});
        mnAdministracion.add(mntmRespaldo);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblFondo = new JLabel("");
        lblFondo.setBounds(0,0, getWidth(),getHeight());
        contentPane.add(lblFondo);

        aplicarPermisos(usuarioLogueado);
    }
    private void aplicarPermisos(Object usuario) 
    {
        if (usuario instanceof Persona) {
            Persona candidato = (Persona) usuario;
            mnCentros.setVisible(false); 
            mnCandidatos.setVisible(false);
            mntmCandRegistrar.setVisible(false); 
            mntmCandConsultar.setVisible(false); 
            mntmCatRegistrar.setVisible(false); 
            mnSolicitudes.setVisible(false);
            mnAdministracion.setVisible(false);  
            
            mnMisSolicitudes.setVisible(true);  
            
            if (candidato.isDisponible()) {
                mntmCrearSolicitud.setVisible(true);
                mntmRenunciar.setVisible(false);
            } else {
                mntmCrearSolicitud.setVisible(false);
                mntmRenunciar.setVisible(true);
            }
            
        }
        else if (usuario == null) {
            // Admin
            mntmCentRegistrar.setVisible(false);
            mntmCandRegistrar.setVisible(false);
            mntmCatRegistrar.setVisible(false);
            mnSolicitudes.setVisible(true);
            mnMisSolicitudes.setVisible(false);
            mnAdministracion.setVisible(true);
        }
        else if (usuario instanceof Empresa) {
        	mnCandidatos.setVisible(false);
            mntmCentRegistrar.setVisible(false); 
            mntmCandRegistrar.setVisible(false); 
            mnSolicitudes.setVisible(false);
            mnMisSolicitudes.setVisible(false); 
            mnAdministracion.setVisible(false); 
            
        } else {
            mnMisSolicitudes.setVisible(false);
            mnSolicitudes.setVisible(false);
            mnAdministracion.setVisible(false);
        }
    }
}