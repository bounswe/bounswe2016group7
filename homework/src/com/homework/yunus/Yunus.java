package com.homework.yunus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation of Yunus.
 * @author Yunus
 *
 */
@WebServlet("/Yunus")
public class Yunus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbYunus dao = new DbYunus("yunus", "root", "group7");
	Vector<ModelYunus> data; 
	static String searchedTerm;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Yunus() {
        super();
    }

	/**
	 * Starting page of the application.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); /**< Our writer to type html codes */
        out.println("<html>");
        
		Connection connection = dao.getConnection();	
    	if (connection != null) {
    		out.println("You made it, take control your database now!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
        
    	SparqlYunus sparql = new SparqlYunus();
		data = sparql.getData();
		out.println("<form action=\"/homework/Yunus\" method=\"post\">"
				+ "Please Type a Country Name (Ex: England)</br>"
				+ "<input type=\"text\" name=\"inputTerm\"> &nbsp;"
				+ "<input type=\"submit\" name=\"search\" value=\"Search\" /> &nbsp; &nbsp;"
    			+ "<input type=\"submit\" name=\"history\" value=\"Show saved search history\" /></br></br>"
    			+ "<input type=\"submit\" name=\"init\" value=\"Initialize the Database\" />"
    			+ "<input type=\"submit\" name=\"delete\" value=\"Delete the Database\" /></br></br>"
    			+ "</form>");
		out.println("</html>");
	}
	/**
	 * Looks to the actions of the Servlet.
	 * Make methods work when buttons are pressed.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter(); /**< Our writer to type html codes */
        Vector<ModelYunus> searchResult = null;
        out.println("<html>");
		if(req.getParameter("init") != null)
		{
			dao.init(data);
			out.println("Database initialized.");
		}
		if(req.getParameter("delete") != null)
		{
			dao.delete();
			out.println("Database deleted.");
		}
		if(req.getParameter("search") != null)
		{
			searchResult = dao.search(req.getParameter("inputTerm"));
			searchedTerm = req.getParameter("inputTerm");
			out.println("<form action=\"/homework/Yunus\" method=\"post\">"
					+ "<table style=\"width:100%\">"
					+ "<thead>"
					+ "<tr><th>Save</th><th>Country</th><th>Capital</th></tr>"
					+ "</thead>");
			for(int i=0;i<searchResult.size();i++)
			{
				out.println("<tr >"
						+ "<td><input type=\"checkbox\" name=\"check"+i+"\" /></td>"
						+ "<td>"+searchResult.elementAt(i).getCountry()+"</td>"
						+ "<td>"+searchResult.elementAt(i).getCapital()+"</td>");
				out.println("</tr>");
			}
			out.println("</table>"
			+ "<input type=\"submit\" name=\"save\" value=\"Save Selected Items\" /></br>"
			+ "</form>");
		}
		if(req.getParameter("save") != null)
		{
			String selecteditems = "";
			for(int i=0;i<500;i++)
				if(req.getParameter("check"+i)!=null)
					selecteditems = selecteditems + (i+1)+",";
			java.util.Date date= new java.util.Date();
			dao.saveSearch(new Timestamp(date.getTime())+"",searchedTerm,selecteditems);
			out.println("Succesfully saved to database!");
		}
		if(req.getParameter("history") != null)
		{
			String history = dao.getHistory();
			out.println(history);
		}
		out.println("</html>");
	}

}
