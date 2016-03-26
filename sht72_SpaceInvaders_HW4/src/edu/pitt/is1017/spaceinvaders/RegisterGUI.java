package edu.pitt.is1017.spaceinvaders;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is for register operation.
 * @author Shuning Tong
 */
public class RegisterGUI {

	private JFrame frame;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblComfirmPassword;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtConfirmPassword;
	private JButton btnRegister;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI window = new RegisterGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(37, 32, 95, 16);
		frame.getContentPane().add(lblFirstName);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(37, 73, 95, 16);
		frame.getContentPane().add(lblLastName);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(37, 115, 95, 16);
		frame.getContentPane().add(lblEmail);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(37, 155, 95, 16);
		frame.getContentPane().add(lblPassword);
		
		lblComfirmPassword = new JLabel("Comfirm Password:");
		lblComfirmPassword.setBounds(37, 190, 129, 16);
		frame.getContentPane().add(lblComfirmPassword);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(171, 27, 219, 26);
		frame.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(171, 68, 219, 26);
		frame.getContentPane().add(txtLastName);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(171, 110, 219, 26);
		frame.getContentPane().add(txtEmail);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(171, 150, 219, 26);
		frame.getContentPane().add(txtPassword);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setColumns(10);
		txtConfirmPassword.setBounds(171, 185, 219, 26);
		frame.getContentPane().add(txtConfirmPassword);
		
		btnRegister = new JButton("Register");
		
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if("".equals(txtLastName.getText()) || "".equals(txtFirstName.getText()) || "".equals(txtEmail.getText()) || "".equals(txtPassword.getText()) || "".equals(txtConfirmPassword.getText())){
					JOptionPane.showMessageDialog(null, "Incomplete information!");
				}else{
					if(txtPassword.getText().equals(txtConfirmPassword.getText())){
						User new_user = new User(txtLastName.getText(), txtFirstName.getText(), txtEmail.getText(), txtPassword.getText());
						JOptionPane.showMessageDialog(null, "Successfully registered!");
						frame.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Password does not match the confirm password!");
					}
				}
			}});
		
		btnRegister.setBounds(171, 230, 117, 29);
		frame.getContentPane().add(btnRegister);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(300, 230, 117, 29);
		frame.getContentPane().add(btnCancel);
	}

	public JFrame getFrame() {
		return frame;
	}
}
