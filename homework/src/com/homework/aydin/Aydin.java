package com.homework.aydin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.aydin.DBAydin;

/**
 * Servlet implementation class Aydin
 */
@WebServlet("/Aydin")
public class Aydin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aydin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("Search.jsp").forward(request, response);
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        out.println("<html>");
        out.println("Hi I'm Aydin </html>");
        
        DBAydin d = new DBAydin("test", "root", "");
		Connection connection = d.getConnection(); /**< database connection variable*/
		
    	if (connection != null) {
    		out.println("Connection to database successfull!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
    	out.println("</html>");
    	
    	out.println("<form>  Search:<br>  <input type=\"text\" name=\"s_bar\"><br> "
    			+ "<input type = \"submit\" value = \"Submit\">"
    			+ "</form>");
    	out.println("");
    	
    	String searchTerm = request.getParameter("s_bar");
    
    	ResultSet rs;
    	String userid = " ";
    	String username = " ";
    	ArrayList<String> ids = new ArrayList<String>();
    	try {
			Statement st = connection.createStatement();
			rs = st.executeQuery("SELECT iduser,name FROM user WHERE name ='" + searchTerm + "'");
			while (rs.next()) {
				userid = rs.getString("IDUSER");
				username = rs.getString("NAME");
				ids.add(userid);
				String checkBoxName = userid;
				out.println("<input type=\"checkbox\" name=" + checkBoxName + "/>" + username + "<br>");
				
				//out.println(userid+ " "+username + "</br>");
			}
		} catch (SQLException e) {
			out.print("Failure. </br>");
			e.printStackTrace();
			
		}
    	
    	out.println("<form><input type=\"submit\" name=\"save\" value= \"Save\"/></form>");
    	String saveResult = request.getParameter("save");
    	
    	for(String id: ids){
    		if(request.getParameter(id) != null){
    		out.println(id + "<br>");
    		}
    	}
    		
			

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
