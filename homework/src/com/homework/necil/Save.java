package com.homework.necil;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * Servlet implementation class Necil
 */
@WebServlet("/save")
public class Save  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    
   /**
    * @see HttpServlet#HttpServlet()
    */
   public Save() {
       super();
       // TODO Auto-generated constructor stub
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("doget");
	}

	/**
	 * @param saveList List of items will be saved to db
	 * @param ip IP address of client
	 * @throws SQLException
	 */
	protected void saveResults(List <Theater> saveList, String ip) throws SQLException{
		DBNecil database = new DBNecil("necil","root","group7");
		Connection connection = database.getConnection();
		for(int i = 0; i < saveList.size(); i++){
			Statement st = connection.createStatement();
			String query = "INSERT INTO searchresults (Ip, placeName, Lat, Lng)"
							+"VALUES (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ip);
			statement.setString(2, saveList.get(i).getName());
			statement.setString(3, saveList.get(i).getLat());
			statement.setString(4, saveList.get(i).getLng());
			
			statement.execute();
		}
		connection.close();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Theater> saveList = new ArrayList<Theater>();
		String IP = request.getRemoteAddr();
		String countStr = request.getParameter("count");
		int count = Integer.parseInt(countStr);
		for(int i = 0; i < count; i++){
			String parameter = "check-"+i;
			if(request.getParameter(parameter)==null)
				continue;
			Theater t1 = new Theater();
			parameter = "lat-"+i;
			t1.Lat = request.getParameter(parameter);
			parameter = "lng-"+i;
			t1.Lng = request.getParameter(parameter);
			parameter = "name-"+i;
			t1.Name = request.getParameter(parameter);
			saveList.add(t1);
		}
		try {
			saveResults(saveList,IP);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
