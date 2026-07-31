package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Panel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import logico.Persona;
import logico.Tecnico;
import logico.Universitario;
import logico.Usuario;
import logico.Obrero;
import logico.BolsaLaboral;
public class RegPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JLabel lblAcedemico;
	private JLabel lblDatoProfesional;
	private JTextField txtDatoProfesional;
	private JComboBox<String> comboBoxAcademico;
	private JComboBox<String> comboBoxSexo;
	private JComboBox<String> comboBoxProvincia;
	private JCheckBox CheckBoxLicencia;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegPersona dialog = new RegPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegPersona() {
		setTitle(" Registrar Persona");
		setBounds(100, 100, 554, 540);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelngresos = new JPanel();
			panelngresos.setToolTipText("");
			panelngresos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Personales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelngresos.setBounds(15, 13, 509, 149);
			contentPanel.add(panelngresos);
			panelngresos.setLayout(null);
			{
				JLabel lblUser = new JLabel("Nombre:");
				lblUser.setBounds(23, 11, 170, 16);
				panelngresos.add(lblUser);
			}

			txtName = new JTextField();
			txtName.setText("");
			txtName.setBounds(23, 40, 281, 22);
			panelngresos.add(txtName);
			txtName.setColumns(10);
			{
				JLabel lblEmail = new JLabel("Email: ");
				lblEmail.setBounds(23, 75, 106, 16);
				panelngresos.add(lblEmail);
			}
			{
				txtEmail = new JTextField();
				txtEmail.setBounds(23, 106, 281, 22);
				panelngresos.add(txtEmail);
				txtEmail.setColumns(10);
			}

			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setBounds(420, 8, 89, 22);
			txtId.setText("P-"+BolsaLaboral.generadorIdPersona);
			panelngresos.add(txtId);
			txtId.setColumns(10);
		}
		{
			JPanel panelGenerales = new JPanel();
			panelGenerales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos generales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelGenerales.setBounds(15, 170, 509, 200);
			contentPanel.add(panelGenerales);
			panelGenerales.setLayout(null);
			{
				JLabel lblTelefono = new JLabel("Telefono:");
				lblTelefono.setBounds(23, 13, 68, 16);
				panelGenerales.add(lblTelefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setBounds(23, 42, 130, 22);
				panelGenerales.add(txtTelefono);
				txtTelefono.setColumns(10);
			}
			{
				JLabel lblDireccion = new JLabel("Direccion:");
				lblDireccion.setBounds(23, 77, 68, 16);
				panelGenerales.add(lblDireccion);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setBounds(23, 102, 169, 22);
				panelGenerales.add(txtDireccion);
				txtDireccion.setColumns(10);
			}
			{
				JLabel lblSexo = new JLabel("Sexo: ");
				lblSexo.setBounds(316, 13, 73, 16);
				panelGenerales.add(lblSexo);
			}

			comboBoxSexo = new JComboBox();
			comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Femenino ", "Masculino"}));
			comboBoxSexo.setToolTipText("");
			comboBoxSexo.setBounds(316, 42, 106, 22);
			panelGenerales.add(comboBoxSexo);
			{
				JLabel lblProvincia = new JLabel("Provincia:");
				lblProvincia.setBounds(316, 77, 73, 16);
				panelGenerales.add(lblProvincia);
			}
			{
				comboBoxProvincia = new JComboBox();
				comboBoxProvincia.setModel(new DefaultComboBoxModel(new String[] {"Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "S\u00E1nchez Ram\u00EDrez", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
				comboBoxProvincia.setMaximumRowCount(5);
				comboBoxProvincia.setBounds(316, 102, 135, 22);
				panelGenerales.add(comboBoxProvincia);
			}

			CheckBoxLicencia = new JCheckBox("");
			CheckBoxLicencia.setVerticalAlignment(SwingConstants.TOP);
			CheckBoxLicencia.setHorizontalAlignment(SwingConstants.LEFT);
			CheckBoxLicencia.setBounds(87, 146, 25, 25);
			panelGenerales.add(CheckBoxLicencia);
			{
				JLabel lblLicencia = new JLabel("Licencia:");
				lblLicencia.setBounds(23, 150, 56, 16);
				panelGenerales.add(lblLicencia);
			}
		}

		JPanel panelProfesional = new JPanel();
		panelProfesional.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos profesionales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProfesional.setBounds(15, 380, 509, 71);
		contentPanel.add(panelProfesional);
		panelProfesional.setLayout(null);
		{
			lblAcedemico = new JLabel("");
			lblAcedemico.setBounds(23, 26, 107, 16);
			panelProfesional.add(lblAcedemico);
		}
		{
			JLabel lblAcademico = new JLabel("Nivel academico:");
			lblAcademico.setBounds(26, 23, 97, 16);
			panelProfesional.add(lblAcademico);
		}

		comboBoxAcademico = new JComboBox();
		comboBoxAcademico.setModel(new DefaultComboBoxModel(new String[] {"Tecnico", "Universitario", "Obrero"}));
		comboBoxAcademico.setBounds(135, 23, 107, 22);
		panelProfesional.add(comboBoxAcademico);

		lblDatoProfesional=new JLabel("Especialidad: ");
		lblDatoProfesional.setBounds(254, 26, 84, 16);
		panelProfesional.add(lblDatoProfesional);

		txtDatoProfesional = new JTextField();
		txtDatoProfesional.setBounds(339, 23, 136, 22);
		panelProfesional.add(txtDatoProfesional);

		comboBoxAcademico.addActionListener(e -> actualizarCampoProfesional());
		txtDatoProfesional.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent e) {


						Persona persona = crearPersona();
						if(persona!=null)
						{
							BolsaLaboral.getInstancia().registrarPersona(persona);
							Usuario usuarioGenerado = BolsaLaboral.getInstancia().crearUsuarioDesdePersona(persona);
							JOptionPane.showMessageDialog(null,
									"Persona registrada correctamente.\n" +
											"Su usuario es: " + usuarioGenerado.getUsername() + "\n" +
											"Su contrasena es: " + usuarioGenerado.getPassword());
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
	private void actualizarCampoProfesional ()
	{
		String nivel = comboBoxAcademico.getSelectedItem().toString();
		if (nivel.equals("Universitario"))
		{
			lblDatoProfesional.setText("Título:");
		}
		else if (nivel.equals("Tecnico"))
		{
			lblDatoProfesional.setText("Especialidad:");
		}
		else if (nivel.equals("Obrero"))
		{
			lblDatoProfesional.setText("Habilidades:");
		}
	}
	private void clear() {

		txtId.setText("P-" + BolsaLaboral.generadorIdPersona);
		txtName.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtDatoProfesional.setText("");

		comboBoxSexo.setSelectedIndex(0);
		comboBoxProvincia.setSelectedIndex(0);
		comboBoxAcademico.setSelectedIndex(0);
		CheckBoxLicencia.setSelected(false);
	}
	private Persona crearPersona()
	{
		if(!camposCompletos())
		{
			JOptionPane.showMessageDialog(this, "Debe completar todos los campos", 
					"Advertencia", JOptionPane.WARNING_MESSAGE);

			return null;
		}
		
		String email=txtEmail.getText();
		
		if (!email.contains("@")) {
	        JOptionPane.showMessageDialog(this, "El correo debe contener un @ valido.", 
	                "Correo invalido", JOptionPane.WARNING_MESSAGE);
	        return null;
	    }
	    
	    if (BolsaLaboral.getInstancia().existeCorreo(email)) {
	        JOptionPane.showMessageDialog(this, "Ya existe un usuario registrado con ese correo.", 
	                "Correo duplicado", JOptionPane.WARNING_MESSAGE);
	        return null;
	    }
	    
		String nivel = comboBoxAcademico.getSelectedItem().toString();
		Persona person= null;
		String id=txtId.getText();
		String username= txtName.getText();
		String telefono=txtTelefono.getText();
		String direccion=txtDireccion.getText();
		String sexo = comboBoxSexo.getSelectedItem().toString();
		String provincia = comboBoxProvincia.getSelectedItem().toString();
		boolean licencia = CheckBoxLicencia.isSelected();
		String datoProfesional = txtDatoProfesional.getText();

		if(nivel.equals("Universitario"))
		{
			person = new Universitario(id, username, email,
					telefono, true, direccion, provincia, sexo, licencia, datoProfesional);
		}
		else if (nivel.equals("Tecnico"))
		{

			person = new Tecnico(id, username, 
					email, telefono, true, direccion, provincia, sexo, licencia, datoProfesional);
		}
		else if (nivel.equals("Obrero"))
		{
			Obrero obrero = new Obrero(id, username, email, telefono,
					true, direccion, provincia, sexo, licencia);
			obrero.getHabilidades().add(datoProfesional);

			person = obrero;
		}

		return person;

	}
	public boolean camposCompletos () {

		return !txtId.getText().trim().isEmpty()
				&& !txtName.getText().trim().isEmpty()
				&& !txtEmail.getText().trim().isEmpty()
				&& !txtTelefono.getText().trim().isEmpty()
				&& !txtDireccion.getText().trim().isEmpty();



	}
}
