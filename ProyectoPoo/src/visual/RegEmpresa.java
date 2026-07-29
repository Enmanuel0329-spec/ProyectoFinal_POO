package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import logico.Persona;
import logico.Tecnico;
import logico.Universitario;
import logico.Obrero;
import logico.BolsaLaboral;
import logico.Empresa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class RegEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRNC;
	private JTextField txtRepresentante;
	private JTextField txtInstitucion;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEmpresa dialog = new RegEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEmpresa() {
		setTitle("Registrar Empresa");
		setBounds(100, 100, 679, 537);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelGenerales = new JPanel();
		panelGenerales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos generales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGenerales.setBounds(26, 13, 609, 232);
		contentPanel.add(panelGenerales);
		panelGenerales.setLayout(null);
		
		JLabel lblInstitucion = new JLabel("Compa\u00F1ia:");
		lblInstitucion.setBounds(21, 170, 99, 16);
		panelGenerales.add(lblInstitucion);
		
		JLabel lblRNC = new JLabel("RNC: ");
		lblRNC.setBounds(21, 46, 99, 16);
		panelGenerales.add(lblRNC);
		
		JLabel lblRepresentante = new JLabel("Representante:");
		lblRepresentante.setBounds(21, 108, 99, 16);
		panelGenerales.add(lblRepresentante);
		
		txtRNC = new JTextField();
		txtRNC.setBounds(149, 43, 239, 22);
		panelGenerales.add(txtRNC);
		txtRNC.setColumns(10);
		
		txtRepresentante = new JTextField();
		txtRepresentante.setBounds(149, 105, 239, 22);
		panelGenerales.add(txtRepresentante);
		txtRepresentante.setColumns(10);
		
		txtInstitucion = new JTextField();
		txtInstitucion.setBounds(149, 167, 239, 22);
		panelGenerales.add(txtInstitucion);
		txtInstitucion.setColumns(10);
		
		JPanel panelRepresentante = new JPanel();
		panelRepresentante.setLayout(null);
		panelRepresentante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Representante.", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelRepresentante.setBounds(26, 258, 609, 177);
		contentPanel.add(panelRepresentante);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(348, 34, 99, 16);
		panelRepresentante.add(lblEmail);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(21, 34, 56, 16);
		panelRepresentante.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(21, 76, 61, 16);
		panelRepresentante.add(lblDireccion);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(401, 31, 183, 22);
		panelRepresentante.add(txtEmail);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(150, 73, 178, 22);
		panelRepresentante.add(txtDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(149, 31, 178, 22);
		panelRepresentante.add(txtTelefono);
		
		JLabel lblTipo = new JLabel("Sector empresarial:");
		lblTipo.setBounds(21, 123, 140, 16);
		panelRepresentante.add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(150, 120, 178, 22);
		panelRepresentante.add(txtTipo);
		txtTipo.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener () {
				public void actionPerformed(ActionEvent e) {
					
					
					Empresa empresa= crearEmpresa();
					if(empresa!=null)
					{
						BolsaLaboral.getInstancia().registrarEmpresa(empresa);
						JOptionPane.showMessageDialog(null,"Empresa registrada correctamente");
					}
					clear();
					
				}			
			});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() 
				{
				    public void actionPerformed(ActionEvent e) 
				    { 
				        cancelButton.setEnabled(false);
				        dispose();
				    }
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	public Empresa crearEmpresa()
	{
		if(!camposCompletos())
		{
			JOptionPane.showMessageDialog(this, "Debe completar todos los campos", 
					"Advertencia", JOptionPane.WARNING_MESSAGE);
			
			return null;
		}
		Empresa e = null;
		String rnc= txtRNC.getText();
		String nombre= txtInstitucion.getText();
		String representante= txtRepresentante.getText();
		String telefono= txtTelefono.getText();
		String direccion= txtDireccion.getText();
		String email= txtEmail.getText();
		String tipo= txtTipo.getText();
		
		e= new Empresa(rnc,nombre,representante,direccion,telefono,email, tipo);
		
		return e;
		
	}
	public void clear()
	{
		
		txtRNC.setText("");
		txtInstitucion.setText("");
		txtRepresentante.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtEmail.setText("");
		txtTipo.setText("");
	}
	public boolean camposCompletos() 
	{
		return !txtRNC.getText().trim().isEmpty()
		&& !txtInstitucion.getText().trim().isEmpty()
		&& !txtRepresentante.getText().trim().isEmpty()
		&& !txtTelefono.getText().trim().isEmpty()
		&& !txtDireccion.getText().trim().isEmpty()
		&& !txtEmail.getText().trim().isEmpty()
		&& !txtTipo.getText().trim().isEmpty();
				
	}
}
