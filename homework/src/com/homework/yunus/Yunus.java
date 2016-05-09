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
        
		DbYunus dao = new DbYunus("test", "root", "group7");
		Connection connection = dao.getConnection(); /**< database connection variable*/
		
    	if (connection != null) {
    		out.println("You made it, take control your database now!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
        
    	SparqlYunus sparql = new SparqlYunus();
		Vector<ModelYunus> data = sparql.getData();
		for(int i=0;i<data.size();i++)
			out.println(data.elementAt(i).getCountry() + " " + data.elementAt(i).getCapital() + "</br>");
    	out.println("</html>");

	}

}
