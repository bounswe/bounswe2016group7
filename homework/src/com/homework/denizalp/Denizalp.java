package com.homework.denizalp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Denizalp connects to database
 */
@WebServlet("/Denizalp")
public class Denizalp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Denizalp() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); /**< Our writer to type html codes */
        out.println("<html>");
        
		DbDenizalp dao = new DbDenizalp("test", "root", "");
		Connection connection = dao.getConnection(); /**< database connection variable*/
		
    	if (connection != null) {
    		out.println("You made it, take control your database now!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
    	out.println("</html>");
    	
    	try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT idbla,name from bla");
			while (rs.next()) {
				String userid = rs.getString("idbla");
				String username = rs.getString("name");
				//String surname = rs.getString("SURNAME");	
				out.println(userid+ " "+username + "</br>");
			}
		} catch (SQLException e) {
			out.print("Valla ulasamiyoz ki </br>");
			e.printStackTrace();
		}

	}

}