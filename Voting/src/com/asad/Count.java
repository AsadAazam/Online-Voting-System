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
@WebServlet("/count")
public class Count extends HttpServlet 
{
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException 
{
	PrintWriter pw =response.getWriter();
    HttpSession session=request.getSession();
	try
	{ 
	Integer status = (Integer) session.getAttribute("login");
	String user=(String)session.getAttribute("user");
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote", "root", "Vampire21");
	Statement s = c.createStatement();
	Statement s2 = c.createStatement();
	int choice = Integer.parseInt(request.getParameter("click"));
	ResultSet rs1=s.executeQuery("select *from user where name='"+user+"'");
	ResultSet rs2=s2.executeQuery("select votes from candidates where Sno='"+choice+"'");
	rs1.next();
	rs2.next();
	String given=rs1.getString("Given");
	int votes=rs2.getInt("Votes");
	if((status==1)&&(given.equalsIgnoreCase("N")))
	{
		s.executeUpdate("Update user Set Given='Y'  where Name = '"+user+"'");
		votes++;
		s2.executeUpdate("Update Candidates Set votes='"+votes+"' where Sno='"+choice+"'");
		pw.println("<h1>your response is saved</h1>");
	}
	else if((status==1)&&(given.equalsIgnoreCase("Y")))
	{
		pw.println("<h1>Sorry!!!!!!You have already voted</h1>");
	}
		
	else
	{
		System.out.println("no vote");
		response.sendRedirect("index.html");
	}
	
}
catch (Exception e)
{
	System.out.println("exception="+e);
	response.sendRedirect("index.html");
}
}	

}
