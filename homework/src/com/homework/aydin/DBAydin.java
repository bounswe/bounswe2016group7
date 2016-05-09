package com.homework.aydin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.homework.yunus.ModelYunus;

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
	public void init(Vector<ModelAydin> preData) {
		// TODO Auto-generated method stub
		try {
			Statement stm = connection.createStatement();
			String sql = "INSERT INTO pre_spo(president,spouse) VALUES('";
			for(int i=0;i<preData.size();i++)
			{
				stm.executeUpdate(sql+preData.elementAt(i).getPresident()+"','"+preData.elementAt(i).getSpouse()+"')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void delete() {
		// TODO Auto-generated method stub
		try {
			Statement stm = connection.createStatement();
			String sql = "DELETE FROM pre_spo";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Vector<ModelAydin> search(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}
