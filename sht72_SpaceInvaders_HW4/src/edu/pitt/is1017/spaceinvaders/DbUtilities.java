package edu.pitt.is1017.spaceinvaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Provides methods for: 
 * 1. Retrieving data sets from MySQL databases. 
 * 2. For executing UPDATE, INSERT, DELETE queries 
 * 3. For building tables to populate data grids (JTable)
 * @author Dmitriy Babichenko, modified by Daniel Murray
 * @version 1.1
 */
public class DbUtilities {
	
    private Connection conn = null; // connection object
    private String hostName = "sis-teach-01.sis.pitt.edu:3306";
    private String dbName = "alieninvasion";
    private String dbUserName = "alienhunter";
    private String dbPassword = "A1i3nHun13r";

    /**
     * Default constructor creates a connection to database at the time of instantiation.
     */
    public DbUtilities() {
        createDbConnection();
    }
    
    
    /**
     * Alternate constructor - use it to connect to any MySQL database
     * @param hostName - server address/name of MySQL database
     * @param dbName - name of the database to connect to
     * @param dbUserName - user name for MySQL database
     * @param dbPassword - password that matches dbUserName for MySQL database
     */
    public DbUtilities(String hostName, String dbName, String dbUserName, String dbPassword) {
    	// Set class-level (instance) variables
        this.hostName = hostName;
        this.dbName = dbName;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
        // Create new database connection
        createDbConnection();
    }
    
    
    /**
     * Creates database connection using credentials stored in class variables.  
     * Connection to database is the most resource-consuming part of the database transaction. 
     * That's why we create a connection once when the object is instantiated and keep it alive through the life of this object.
     * Note that this is a private method and cannot be accessed from outside of this class.
     */
    private void createDbConnection(){
        try {
        	// Build connection string
            String mySqlConn = "jdbc:mysql://" + this.hostName + "/" + this.dbName + "?user=" + this.dbUserName + "&password=" + this.dbPassword;
            //System.out.println("Establishing DB connection:\n"+mySqlConn);//hostName = "+hostName+"\ndbName = "+dbName+"\ndbUserName = "+dbUserName+"\ndbPassword = "+dbPassword+"\n");
            // Load the MySQL database connector driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            // Connect to the database
            this.conn = DriverManager.getConnection(mySqlConn);
            
        } catch (Exception e) {
            System.err.println(e.toString());
            System.err.println("Unable to connect to database");
        }
    }


    /**
     * Get SQL result set (data set) based on an SQL query
     * @param sql - SQL SELECT query
     * @return - ResultSet - java.sql.ResultSet object, contains results from SQL query argument
     * @throws SQLException
     */
    public ResultSet getResultSet(String sql) {//throws SQLException {  
        try {
            if(this.conn == null){ // Check if connection object already exists
                createDbConnection(); // If does not exist, create new connection
            }
            
            // Create a statement
            Statement statement = this.conn.createStatement();
            
            // Execute SQL query and get results
            return statement.executeQuery(sql); 
            
        } catch (Exception e) {
        	e.printStackTrace(); // debug
        }
        return null;
    }
    
    
    /**
     * Executes INSERT, UPDATE, DELETE queries
     * @param sql - SQL statement - a well-formed INSERT, UPDATE, or DELETE query
     * @return true if execution succeeded, false if failed 
     */
    public boolean executeQuery(String sql){
        try {
            if(this.conn == null){
                createDbConnection();
            }
            
            // Create a statement
            Statement statement = this.conn.createStatement();
            // Execute SQL query
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
        	e.printStackTrace(); // debug
        }
        return false;
    }
    
    public void closeConnection(){
    	if (conn != null){
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
}
