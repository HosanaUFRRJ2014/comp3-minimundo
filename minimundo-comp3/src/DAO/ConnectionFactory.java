package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;

public class ConnectionFactory
{
	
	Connection connection;
	
	public Connection getConnection() {
	    
	    try {
	            //Class.forName("com.mysql.jdbc.Driver");
	            DriverManager.registerDriver(new org.postgresql.Driver()); 
	   //         System.out.println("Conectando ao banco");
	            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","postgres");
	            } catch(SQLException e) {
	                e.printStackTrace();
	                throw new RuntimeException(e);
	             
	        }
	         
	    }

	

}
