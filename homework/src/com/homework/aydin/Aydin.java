package com.homework.aydin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.aydin.DBAydin;

/**
 * Servlet implementation class Aydin
 * @author The Formal Boogieman
 */
@WebServlet("/Aydin")
public class Aydin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBAydin d = new DBAydin("aydin", "root", "");
	Vector<ModelAydin> preData;
	Vector<ModelAydin> searchList;
	static String searched;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aydin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Main page.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("Search.jsp").forward(request, response);
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        out.println("<html>");
        out.println("Hi I'm Aydin! </html>");
        

        
		Connection connection = d.getConnection(); /**< database connection variable*/
		SparqlAydin sp = new SparqlAydin();
		preData = sp.getData();
		
    	if (connection != null) {
    		out.println("Connection to database successfull!</br>");
    	} else {
    		out.println("Failed to make connection!</br>");
    	}
    	out.println("</html>");
//    	for(int i=0; i<preData.size(); i++){
//    		out.println(preData.elementAt(i).getPresident()+ " " +preData.elementAt(i).getSpouse()+ "</br>");
//    	}
    	
		out.println("<form action=\"/homework/Aydin\" method=\"post\">"
				+ "Please Type a President Name (Ex: Washington Portakal)</br>"
				+ "<input type=\"text\" name=\"s_bar\">"
				+ "<input type=\"submit\" name=\"search\" value=\"Search\" /></br>"
    			+ "<input type=\"submit\" name=\"init\" value=\"Initialize the Database\" /></br>"
    			+ "<input type=\"submit\" name=\"delete\" value=\"Delete the Database\" /></br>"
    			+ "<input type=\"submit\" name=\"history\" value=\"Show saved search history\" /></br></br>"
    			+ "</form>");
		out.println("</html>");     
		

    	}			

	/**
	 * Action listener of the servlet.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        out.println("<html>");
		if(request.getParameter("init") != null)
		{
			d.init(preData);
			out.println("Database initialized.");
		}
		if(request.getParameter("delete") != null)
		{
			d.delete();
			out.println("Database deleted.");
		}
		if(request.getParameter("search") != null)
		{
			searchList = d.search(request.getParameter("s_bar"));
			searched = request.getParameter("s_bar");
			out.println("<form action=\"/homework/Aydin\" method=\"post\">"
					+ "<table style=\"width:100%\">"
					+ "<thead>"
					+ "<tr><th>Save</th><th>President</th><th>Spouse</th></tr>"
					+ "</thead>");
			for(int i=0;i<searchList.size();i++)
			{
				out.println("<tr >"
						+ "<td><input type=\"checkbox\" name=\"check"+i+"\" /></td>"
						+ "<td>"+searchList.elementAt(i).getPresident()+"</td>"
						+ "<td>"+searchList.elementAt(i).getSpouse()+"</td>");
				out.println("</tr>");
			}
			out.println("</table>"
			+ "<input type=\"submit\" name=\"save\" value=\"Save Selected Items\" /></br>"
			+ "</form>");
		}
		if(request.getParameter("save") != null)
		{
			String selecteditems = "";
			for(int i=0;i<500;i++)
				if(request.getParameter("check"+i)!=null)
					selecteditems = selecteditems + (i+1)+",";
			java.util.Date date= new java.util.Date();
			d.saveSearch(new Timestamp(date.getTime())+"",searched,selecteditems);
			out.println("Succesfully saved to database!");
		}
		if(request.getParameter("history") != null)
		{
			String history = d.getHistory();
			out.println(history);
		}
		out.println("</html>");	
		
		
	}

}
