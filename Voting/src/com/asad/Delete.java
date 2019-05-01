package com.asad;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class Delete extends HttpServlet
{
 @Override
protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException 
 {
	response.setContentType("text/html");
	PrintWriter pw =response.getWriter();
	int click =Integer.parseInt(request.getParameter("click"));
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
	
	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote", "root", "Vampire21");
	Statement s = c.createStatement();
	int x = s.executeUpdate("delete from candidates where Sno='"+click+"'");
	pw.println("<h1> Candidate deleted <h1>");
	pw.println("<a href=\"AdminUse.html\">Go Back</a>");
	}
	catch (Exception e)
	{
		response.sendRedirect("Admin.html");
	}
  
 }
}
