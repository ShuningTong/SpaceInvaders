package edu.pitt.is1017.spaceinvaders;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is for login operation.
 * @author Shuning Tong
 */
public class LoginGUI {

	private JFrame frame;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JButton btnRegister;
	private JButton btnCancel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
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
	public LoginGUI() {
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
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(37, 44, 61, 16);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(152, 39, 224, 26);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(37, 99, 78, 16);
		frame.getContentPane().add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(152, 94, 224, 26);
		frame.getContentPane().add(txtPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				if("".equals(email) || "".equals(password)){
					JOptionPane.showMessageDialog(null, "Incomplete information!");
				}else{
					User new_user = new User(email, password);
					if(new_user.isLoggedIn()){
						Thread t = new Thread("GameLaunch"){
							public void run(){
								Game game = new Game(new_user);
								game.gameLoop();
							}
						};
						t.start();
					}
				}
			}
		});
		btnLogin.setBounds(152, 162, 117, 29);
		frame.getContentPane().add(btnLogin);
		
		btnRegister = new JButton("Register");
		/*btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterGUI newRegister = new RegisterGUI();
			}
		});*/
		btnRegister.setBounds(37, 162, 117, 29);
		frame.getContentPane().add(btnRegister);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	
		btnCancel.setBounds(270, 162, 117, 29);
		frame.getContentPane().add(btnCancel);
	}
}
