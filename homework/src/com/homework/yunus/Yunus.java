package com.homework.yunus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Yunus connects to database
 */
@WebServlet("/Yunus")
public class Yunus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbYunus dao = new DbYunus("yunus", "root", "group7");
	Vector<ModelYunus> data; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Yunus() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
				+ "<input type=\"text\" name=\"inputTerm\">"
				+ "<input type=\"submit\" name=\"search\" value=\"Search\" /></br>"
    			+ "<input type=\"submit\" name=\"init\" value=\"Initialize the Database\" /></br>"
    			+ "<input type=\"submit\" name=\"delete\" value=\"Delete the Database\" /></br>"
    			+ "</form>");
		out.println("</html>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter(); /**< Our writer to type html codes */
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
			Vector<ModelYunus> searchResult = dao.search(req.getParameter("inputTerm"));
			for(int i=0;i<searchResult.size();i++)
			{
				out.println(searchResult.elementAt(i).getCountry() + " " + searchResult.elementAt(i).getCapital() + "</br>");
			}
		}
		out.println("</html>");
	}

}
