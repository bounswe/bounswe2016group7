package com.homework.kubra;

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

import com.homework.yunus.DbYunus;
import com.homework.yunus.ModelYunus;
import com.homework.yunus.SparqlYunus;


@WebServlet("/Kubra")
public class Kubra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseKubra db = new DatabaseKubra("kubra", "root", "group7");
	Vector<Datas> data; 
	static String searchedTerm;
	
        public Kubra() {
        super();
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        out.println("<html>");
        
		Connection connection = db.getConnection();	
    	if (connection != null) {
    		out.println("Connected to the database</br>");
    	} else {
    		out.println("Connection fail</br>");
    	}
        
    	SparqlKubra sparql = new SparqlKubra();
		data = sparql.getData();
		out.println("<form action=\"/homework/Kubra\" method=\"post\">"
				+ "Type Planet Name (Ex: Earth)</br>"
				+ "<input type=\"text\" name=\"inputTerm\"> &nbsp;"
				+ "<input type=\"submit\" name=\"search\" value=\"Search\" /> &nbsp; &nbsp;"
    			+ "<input type=\"submit\" name=\"history\" value=\"Show saved search history\" /></br></br>"
    			+ "<input type=\"submit\" name=\"init\" value=\"Initialize the Database\" />"
    			+ "<input type=\"submit\" name=\"delete\" value=\"Delete the Database\" /></br></br>"
    			+ "</form>");
		out.println("</html>");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter(); 
        Vector<Datas> searchResult = null;
        out.println("<html>");
		if(req.getParameter("init") != null)
		{
			db.init(data);
			out.println("Database initialized.");
		}
		if(req.getParameter("delete") != null)
		{
			db.delete();
			out.println("Database deleted.");
		}
		if(req.getParameter("search") != null)
		{
			searchResult = db.search(req.getParameter("inputTerm"));
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
						+ "<td>"+searchResult.elementAt(i).getPlanet()+"</td>"
						+ "<td>"+searchResult.elementAt(i).getDiscoverer()+"</td>");
				out.println("</tr>");
			}
			out.println("</table>"
			+ "<input type=\"submit\" name=\"save\" value=\"Save Selected Items\" /></br>"
			+ "</form>");
		}
	
		out.println("</html>");
	}

}
