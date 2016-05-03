package com.homework.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
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
				+ "<a href=\"Yunus\"> Yunus Seker </a>     <br/>"
				+ "<a href=\"Aydin\"> Aydin Aksoy </a>     <br/>"
				+ "<a href=\"Necil\"> Necil Albayrak </a>  <br/>"
				+ "<a href=\"Kubra\"> Kubra Esmeli </a>    <br/>"
				+ "<a href=\"Yigit\"> Yigit Alp Bildik </a><br/>"
				+ "<a href=\"Denizalp\"> Denizalp Kapisiz </a><br/>"
				+ "<a href=\"Salih\"> Salih Can Egin </a>  <br/>"
				+ "</body>"
				+ "</html>");
	}

}
