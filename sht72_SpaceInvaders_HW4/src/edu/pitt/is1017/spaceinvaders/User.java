package edu.pitt.is1017.spaceinvaders;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 * This is the class for game users.
 * @author Shuning Tong
 */

public class User {
	private int userID;
	private String lastName;
	private String firstName;
	private String email;
	private String password;
	private boolean loggedIn = false;
	
	/**
	 * 	Constructor1: assume we have a userID that already exists in the database, retrieve data from database and create an instance.
	 * @param userID
	 */
	public User(int userID){
		DbUtilities db = new DbUtilities();
		this.userID = userID;
		// The userID is not a string. So, in select query, the userID should not be enclosed within ' '.
		String sql = "SELECT * FROM users WHERE userID = " + userID + ";";
		try{
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.email = rs.getString("email");
				this.password = rs.getString("password");
			}
			db.closeConnection();
			// close connection after select action
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Constructor2 (for Login operation): check whether the information from user matches that from database,
	 * if yes, create an instance and show success message,
	 * if no, only set loggedIn property to false and show failure message.
	 * @param email
	 * @param password
	 */
	public User(String email, String password){
		DbUtilities db = new DbUtilities();
		String sql = "SELECT * FROM users WHERE email = '" + email + "' AND password = MD5('" + password + "');";
		try{
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.userID = rs.getInt("userID");
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.email = rs.getString("email");
				this.password = rs.getString("password");
				this.loggedIn = true;
				JOptionPane.showMessageDialog(null, "Login Succeed!");
			}else{
				this.loggedIn = false;
				JOptionPane.showMessageDialog(null, "Login Failed!");
			}
			db.closeConnection();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	/**
	 * Constructor3 (for register operation): create an instance and insert into the database.
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param password
	 */
	public User(String lastName, String firstName,String email, String password){
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		DbUtilities db = new DbUtilities();
		String sql = "INSERT INTO users (lastName,firstName,email,password) VALUES ('" + lastName + "', '" + firstName + "', '" + email + "', MD5('" + password + "'));";
		db.executeQuery(sql);
	}
	
	/**
	 * Update user table with values currently stored in those instance variables.
	 */
	public void saveUserInfo(){
		DbUtilities db = new DbUtilities();
		String sql = "UPDATE users SET firstName = '" + firstName + "', lastName = '" + lastName + "', email = '" + email + "', password = MD5('" + password + "') WHERE userID = " + userID + ";";
		db.executeQuery(sql);
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public int getUserID() {
		return userID;
	}
}
