package com.homework.yunus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Database Layer of Yunus package.
 * This class uses jdbc mysql connector library under webapp libs
 * @author Yunus
 *
 */
public class DbYunus {
	Connection connection = null;
	/**
	 * 
	 * @param dbName User's personal database name
	 * @param username Mysql server username
	 * @param password Mysql server password
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
	 * After constructing the connection, Servlet can get connection by this method
	 * @return Not Null connection
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
	 * According to given input string, searches the query on database on returns parsed Model data as a list
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
	 * Saves selected data to database with timestamp of the search and query
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
	 * Selects the history from database 
	 * Converts it to a html format that can be written as a string
	 * @return History data as String
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
