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
import javax.servlet.http.HttpSession;



@WebServlet("/adminlogin")
public class Adminlogin extends HttpServlet 
{
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException 
{   HttpSession session;
	try
   {
	response.setContentType("text/html");
	PrintWriter pw =response.getWriter();
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote", "root", "Vampire21");
	Statement s = c.createStatement();
	String user =request.getParameter("username");
	String pass =request.getParameter("pass");
	ResultSet rs =s.executeQuery("Select  * from Admin01 where Name='"+user+"'");
	if(rs.next())
	{
		String p=rs.getString("password");
		if(p.equals(pass))
		{
		session=request.getSession();
		pw.println("login complete");
		session.setAttribute("login",1);
		response.sendRedirect("AdminUse.html");
		}
		else
		{
			pw.println("<h1>Wrong username or password</h1>");
			pw.print("<a href=\"Admin.html\">Go back to Login</a>");
			
		}
	}
	else
	{
		pw.println("<h1>Wrong username or password</h1>");
		pw.print("<a href=\"Admin.html\">Go back to Login</a>");
	}
		
	
   }
  catch(Exception e)
{
	  System.out.println("Exception ="+e);
}
}	
	

}
