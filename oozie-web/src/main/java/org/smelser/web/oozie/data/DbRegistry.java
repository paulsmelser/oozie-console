package org.smelser.web.oozie.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.smelser.web.oozie.utilities.Registry;


/**
 * @author TransitDroid Team
 *  Provides a connection to the database setup in the resources.properties file
 * 
 */
public class DbRegistry {

	private static DbRegistry registry = null;
	private static Connection conn;
	private DbRegistry(){}
	/**
	 *  Creates the single instance of the DbRegistry
	 * @return DbRegistry
	 */
	static DbRegistry getInstance(){
		if (registry == null){
			registry = new DbRegistry();
		}
		return registry;
	}
	/**
	 *  Checks to see if the database is connected and connect if it is not.
	 * @return Connection
	 * @throws SQLException
	 */
	Connection getConnection() throws SQLException{
		if (!isConnected()){
			connect();
		}
		return conn;
	}
	/**
	 *  Establishes a connection with the database.
	 */
	private static void connect(){
		try {
			String dbDriverClass = Registry.getProperty("mysql.driver");
			String url = Registry.getProperty("mysql.url");
			String username = Registry.getProperty("mysql.username");
			String password = Registry.getProperty("mysql.password");
				
			Class.forName(dbDriverClass);
			conn = DriverManager.getConnection(url,
					username,
					password);
			conn.setAutoCommit(false);
		} 
		catch (Exception e) {
			try {
				String dbDriverClass = Registry.getProperty("mysql.driver");
				String url = Registry.getProperty("mysql.localUrl");
				String username = Registry.getProperty("mysql.localUsername");
				String password = Registry.getProperty("mysql.localPassword");
				
				Class.forName(dbDriverClass);
				conn = DriverManager.getConnection(url,
						username,
						password);
				conn.setAutoCommit(false);
			} catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	/**
	 *  Checks to see if the database is connected.
	 * @return boolean
	 * @throws SQLException
	 */
	private boolean isConnected() throws SQLException {
		return (conn != null && !conn.isClosed());
	}
	/**
	 *  Closes the database connection.
	 * @throws SQLException
	 */
	public static void close() throws SQLException{
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
			conn = null;
	}
	/**
	 *  Ensures a connection is established and creates a prepared statement.
	 * @param sql
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	static PreparedStatement prepareStatement(String sql) throws SQLException{
		if (conn == null || conn.isClosed()){
			connect();
		}
		return conn.prepareStatement(sql);
	}
	
	public static void commit() throws SQLException{
		SQLException se = null;
		try{
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
			se =  e;
		} finally{
			conn.close();
		}
		if (se != null)
			throw se;
	}
	
	public static void rollback() throws SQLException{
		conn.rollback();
	}
}
