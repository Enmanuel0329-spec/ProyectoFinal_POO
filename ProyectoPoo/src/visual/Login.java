package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JButton btnLogin;
	private JButton btnRegCandidato;
	private JButton btnRegEmpresa;
	private JPasswordField txtContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("Inicia Sesion");
		setIconImage(
				Toolkit.getDefaultToolkit()
				.getImage(Login.class.getResource("/resources/imagen_logo_pucmm.png"))
				.getScaledInstance(32, 32, Image.SCALE_SMOOTH)
				);

		setBounds(100, 100, 643, 499);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			panel.setLayout(null);

			JLabel lblNewLabel = new JLabel("Bienvenido!");
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 24));
			lblNewLabel.setBounds(346, 20, 222, 49);
			panel.add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Inicia sesion para continuar");
			lblNewLabel_1.setBounds(346, 71, 209, 16);
			panel.add(lblNewLabel_1);
			{
				JLabel lblNewLabel_2 = new JLabel("Usuario:");
				lblNewLabel_2.setBounds(346, 110, 56, 16);
				panel.add(lblNewLabel_2);
			}

			txtUsuario = new JTextField();
			txtUsuario.setBounds(346, 134, 242, 34);
			panel.add(txtUsuario);
			txtUsuario.setColumns(10);

			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(346, 185, 198, 16);
			panel.add(lblContrasea);

			btnLogin = new JButton("Ingresar");
			btnLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
			btnLogin.setForeground(new Color(245, 245, 245));
			btnLogin.setBackground(new Color(30, 144, 255));
			btnLogin.setBounds(346, 273, 242, 42);
			panel.add(btnLogin);

			JLabel lblEresNuevo = new JLabel("--Eres nuevo?--");
			lblEresNuevo.setForeground(new Color(128, 128, 128));
			lblEresNuevo.setHorizontalAlignment(SwingConstants.CENTER);
			lblEresNuevo.setBounds(346, 339, 242, 16);
			panel.add(lblEresNuevo);

			JLabel lblRegistrate = new JLabel("Registrate:");
			lblRegistrate.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistrate.setBounds(346, 359, 242, 16);
			panel.add(lblRegistrate);

			btnRegCandidato = new JButton("<html>Busco<br>Trabajo</html>");
			btnRegCandidato.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnRegCandidato.setForeground(new Color(25, 25, 112));
			btnRegCandidato.setFont(new Font("Arial Narrow", Font.BOLD, 13));
			btnRegCandidato.setBackground(new Color(255, 255, 255));
			btnRegCandidato.setBounds(346, 380, 120, 54);
			panel.add(btnRegCandidato);

			btnRegEmpresa = new JButton("<html>Busco<br>Empleados</html>");
			btnRegEmpresa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnRegEmpresa.setForeground(new Color(25, 25, 112));
			btnRegEmpresa.setFont(new Font("Arial Narrow", Font.BOLD, 13));
			btnRegEmpresa.setBackground(Color.WHITE);
			btnRegEmpresa.setBounds(468, 380, 120, 54);
			panel.add(btnRegEmpresa);

			JLabel lblNewLabel_3 = new JLabel("New label");
			lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/resources/imagen_login.jpg")));
			lblNewLabel_3.setBounds(28, 23, 300, 411);
			panel.add(lblNewLabel_3);

			txtContrasena = new JPasswordField();
			txtContrasena.setBounds(346, 214, 242, 34);
			panel.add(txtContrasena);
		}
	}
}
