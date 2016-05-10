package com.homework.aydin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.homework.yunus.ModelYunus;

/**
 * Database implementation class for Aydin package.
 * Uses jdbc to acces mysql data.
 * @author The Formal Boogieman
 *
 */
public class DBAydin {
	Connection connection = null;
	/**
	 * Constructor.
	 * @param dbName Database name of the package user.
	 * @param username Default mysql username: "root"
	 * @param password Default mysql password: null
	 */
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
	/**
	 * Returns connection.
	 * @return Connection.
	 */
	public Connection getConnection()
	{
		return connection;
	}
	/**
	 * Initializes the table with the parsed data returned from sparql query.
	 * @param preData Sparql query result parsed as XML format.
	 */
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
	/**
	 * Deletes the table.
	 * Used for resetting the database.
	 */
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
	/**
	 * Searches data on mysql database with the input string from search bar.
	 * @param str President name.
	 * @return Search result list.
	 */
	public Vector<ModelAydin> search(String str) {
		Vector<ModelAydin> returnList = new Vector<ModelAydin>();
		str = str.replaceAll(" ", "%");

		try {
			Statement stm = connection.createStatement();
			String sql = "SELECT * FROM pre_spo WHERE president LIKE '%" + str + "%'";
			ResultSet rs = stm.executeQuery(sql);
		    while(rs.next()){
		    	String president  = rs.getString("president"); 
		    	String spouse = rs.getString("spouse");
		    	returnList.add(new ModelAydin(president,spouse));
		       }
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}
	/**
	 * Inserts the saved data to database according to given string values.
	 * @param timestamp Time that search is made.
	 * @param query Query that is searched.
	 * @param saveditems Number of rows that selected.
	 */
	public void saveSearch(String timestamp,String query,String selecteditems) {
		try {
			Statement stm = connection.createStatement();
			String sql = "INSERT INTO saved(timestamp,query,saveditems) VALUES('"+timestamp+"\',\'"+query+"\',\'"+selecteditems+"\')";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gets the saved history data from database and returns it as html formatted string.
	 * @return String that holds history data as html format.
	 */
	public String getHistory() {
		String returnString = "";
		try {
			Statement stm = connection.createStatement();
			String sql = "SELECT * FROM saved";
			ResultSet rs = stm.executeQuery(sql);
		    while(rs.next()){
		    	returnString+=rs.getString("timestamp")+"&nbsp;"; 
		    	returnString+=rs.getString("query")+"&nbsp;"; 
		    	returnString+=rs.getString("saveditems")+"</br>"; 
		       }
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
