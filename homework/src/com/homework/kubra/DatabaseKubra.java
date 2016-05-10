package com.homework.kubra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DatabaseKubra {
	Connection connection = null;

	public DatabaseKubra(String dbName,String username,String password)
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
	
	public void init(Vector<Datas> data) {
		try {
			Statement stm = connection.createStatement();
			String sql = "INSERT INTO kubraitems(planet,discoverer) VALUES('";
			String planet,discoverer;
			for(int i=0;i<data.size();i++)
			{
				planet = data.elementAt(i).getPlanet();
				discoverer = data.elementAt(i).getDiscoverer();
				stm.executeUpdate(sql+planet+"','"+discoverer+"')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete() {
		
		try {
			Statement stm = connection.createStatement();
			String sql = "DELETE FROM kubraitems";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<Datas> search(String str) {
		Vector<Datas> returnList = new Vector<Datas>();
		try {
			Statement stm = connection.createStatement();
			String sql = "SELECT * FROM kubraitems WHERE LIKE '%" + str + "%'";
			ResultSet rs = stm.executeQuery(sql);
		    while(rs.next()){
		    	String planet  = rs.getString("planet"); 
		    	String discoverer = rs.getString("discoverer");
		    	returnList.add(new Datas(planet,discoverer));
		       }
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}
	
	
}
