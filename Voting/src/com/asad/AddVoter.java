package com.asad;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddVoter")
public class AddVoter extends HttpServlet
{
 @Override
protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException 
 {
	response.setContentType("text/html");
	String user =request.getParameter("user");
	String pass =request.getParameter("pass");
	PrintWriter pw =response.getWriter();

	try
	{
		HttpSession session=request.getSession();
	    Integer status = (Integer) session.getAttribute("login");
	    if(status==1) 
	    {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote", "root", "Vampire21");
		Statement s = c.createStatement();
		int x=s.executeUpdate("insert into user values('"+user+"','"+pass+"','N')");
		if(x==1)
		{
			pw.println("<h1> User added<h1>");
			pw.println("<a href=\"AdminUse.html\">Go Back to Add More</a>");
		}
	    }
	    else
	    {
	    	response.sendRedirect("Admin.html");
	    }
	}
	catch (Exception e)
	{
		System.out.println("Exception"+e);
		response.sendRedirect("Admin.html");
	}
}
}
