package com.homework.yunus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Database implementation class for Yunus package.
 * Uses jdbc to access mysql data
 * @author Yunus
 *
 */
public class DbYunus {
	Connection connection = null;
	/**
	 * 
	 * @param dbName database name of the package user
	 * @param username mysql username default "root"
	 * @param password mysql password
	 */
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
	/**
	 * Gets the connection
	 * @return connection
	 */
	public Connection getConnection()
	{
		return connection;
	}
	/**
	 * Initializes the database to given data list from sparql query result.
	 * @param data parsed and modeled data that comes from sparql query result
	 */
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
	/**
	 * Deletes all data on the table to reset the database.
	 * Initialization is usually used after this method.
	 */
	public void delete() {
		try {
			Statement stm = connection.createStatement();
			String sql = "DELETE FROM yunusitems";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Searchs data on mysql database according to given input string
	 * @param str Country name
	 * @return data that is the result of the search
	 */
	public Vector<ModelYunus> search(String str) {
		Vector<ModelYunus> returnList = new Vector<ModelYunus>();
		str = str.replaceAll(" ", "%");
		//System.out.println(str);
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
	/**
	 * Inserts the saved data to database according to given string values
	 * @param timestamp time that search is made
	 * @param query query that is searched
	 * @param selecteditems number of rows that selected
	 */
	public void saveSearch(String timestamp,String query,String selecteditems) {
		try {
			Statement stm = connection.createStatement();
			String sql = "INSERT INTO yunussaves(timestamp,query,selecteditems) VALUES('"+timestamp+"\',\'"+query+"\',\'"+selecteditems+"\')";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gets the saved history data from database and returns it as html formatted string
	 * @return string that holds history data as html format
	 */
	public String getHistory() {
		String returnString = "";
		try {
			Statement stm = connection.createStatement();
			String sql = "SELECT * FROM yunussaves";
			ResultSet rs = stm.executeQuery(sql);
		    while(rs.next()){
		    	returnString+=rs.getString("timestamp")+"&nbsp;"; 
		    	returnString+=rs.getString("query")+"&nbsp;"; 
		    	returnString+=rs.getString("selecteditems")+"</br>"; 
		       }
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
