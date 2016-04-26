package com.homework.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Home() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head> <title> Homework Page </title> </head>"
				+ "<body> "
				+ "<a href=\"Home\"> Yunus Seker </a>     <br/>"
				+ "<a href=\"Home\"> Aydin Aksoy </a>     <br/>"
				+ "<a href=\"Home\"> Necil Albayrak </a>  <br/>"
				+ "<a href=\"Home\"> Kubra Esmeli </a>    <br/>"
				+ "<a href=\"Home\"> Yigit Alp Bildik </a><br/>"
				+ "<a href=\"Home\"> Denizalp Kapisiz </a><br/>"
				+ "<a href=\"Home\"> Salih Can Egin </a>  <br/>"
				+ "</body>"
				+ "</html>");
	}

}
