package com.homework.yunus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DbYunus {
	Connection connection = null;
	public DbYunus(String dbName,String username,String password)
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
	public void init(Vector<ModelYunus> data) {
		try {
			Statement stm = connection.createStatement();
			String sql = "INSERT INTO yunusitems(country,capital) VALUES('";
			String country,capital;
			for(int i=0;i<data.size();i++)
			{
				country = data.elementAt(i).getCountry();
				capital = data.elementAt(i).getCapital();
				//System.out.println(sql+country+"','"+capital+"')");
				stm.executeUpdate(sql+country+"','"+capital+"')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void delete() {
		try {
			Statement stm = connection.createStatement();
			String sql = "DELETE FROM yunusitems";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Vector<ModelYunus> search(String str) {
		Vector<ModelYunus> returnList = new Vector<ModelYunus>();
		str = str.replaceAll(" ", "%");
		System.out.println(str);
		try {
			Statement stm = connection.createStatement();
			String sql = "SELECT * FROM yunusitems WHERE country LIKE '%" + str + "%'";
			ResultSet rs = stm.executeQuery(sql);
		    while(rs.next()){
		    	String country  = rs.getString("country"); 
		    	String capital = rs.getString("capital");
		    	returnList.add(new ModelYunus(country,capital));
		       }
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}
}
