package com.homework.aydin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAydin {
	Connection connection = null;
	public DBAydin(String dbName,String username,String password)
	{
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    		return;
    	}
    	try {
    		connection = DriverManager
    		.getConnection("jdbc:mysql://localhost:3306/"+dbName,username,password);

    	} catch (SQLException e) {
    		e.printStackTrace();
    		return;
    	}
    }
	public Connection getConnection()
	{
		return connection;
	}
}
