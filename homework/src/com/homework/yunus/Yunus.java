package com.homework.yunus;

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
        
		DbYunus dao = new DbYunus("test", "root", "");
		Connection connection = dao.getConnection(); /**< database connection variable*/
		
    	if (connection != null) {
    		out.println("You made it, take control your database now!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
    	out.println("</html>");
    	
    	try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT iduser,name,surname from users");
			while (rs.next()) {
				String userid = rs.getString("IDUSER");
				String username = rs.getString("NAME");
				String surname = rs.getString("SURNAME");	
				out.println(userid+ " "+username + " " +surname + "</br>");
			}
		} catch (SQLException e) {
			out.print("Valla ulasamiyoz ki </br>");
			e.printStackTrace();
		}

	}

}
